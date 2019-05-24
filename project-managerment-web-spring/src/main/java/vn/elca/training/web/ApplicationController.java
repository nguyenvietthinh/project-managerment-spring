package vn.elca.training.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;

import vn.elca.training.dao.IEmployeeRepository;
import vn.elca.training.dao.IGroupRepository;
import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;
import vn.elca.training.dom.Project;
import vn.elca.training.dom.ProjectList;
import vn.elca.training.service.IProjectService;

import vn.elca.tranning.validator.ProjectValidator;

@Controller
@SessionAttributes({ "criterion", "message","sttcriterion" })
public class ApplicationController {
	@Autowired
	private IProjectService projectService;
	@Autowired
	private ProjectValidator projectvalidator;
	@Autowired
	private IGroupRepository groupRepository;
	@Autowired
	private IEmployeeRepository employeeRepository;

	/**
	 * Initialize @ModelAttribute("criterion") = ""
	 * 
	 * @return @ModelAttribute("criterion")
	 */
	@ModelAttribute("criterion")
	public String addStuffToRequestScope() {
		return new String();
	}
	@ModelAttribute("sttcriterion")
	public String addStuffToRequestScope2() {
		return new String();
	}
	/**
	 * Home page handler
	 * 
	 * @param Model model
	 * @return ModelAndView("search")
	 */
	@RequestMapping( "/home")
	ModelAndView main(Model model) {
		model.addAttribute("message", projectService.findAll().size());
		return new ModelAndView("search");
	}

	/**
	 * Get search result by project's name
	 * 
	 * @param String name
	 * @param Model  model
	 * @return List<Project> result projects
	 */
	@RequestMapping("/query")
	@ResponseBody
	ProjectList query(String name, Model model,String stt,int page) {
		List<Project> projects = null;
		List<Project> projectss = null;
		ProjectList projectList = null;
		Sort sortable = new Sort(Sort.Direction.ASC, "projectNumber");
		Pageable pageable = new PageRequest(page, 2, sortable);
		// If query with empty criterion
		if (StringUtils.isEmpty(name) && StringUtils.isEmpty(stt)) {

//			projects = projectService.findAllByOrderByProjectNumberAsc();
					
			projects = projectService.findAllProject(pageable).getContent();
			List<Project> liProjects = projectService.findAll();
			Double pag = ((double) liProjects.size()) / 2.0;
			int pageLength = (int) Math.ceil(pag);
			projectList = new ProjectList(projects, pageLength);
			// For checking in client
			model.addAttribute("criterion", "#Queried");
			model.addAttribute("sttcriterion", "#QueriedStt");
		}else if(StringUtils.isEmpty(stt)) { 
			projects = projectService.findByNameOrNumOrCus(pageable,name).getContent();
			projectss = projectService.findByNameOrNumOrCus(name);
			Double pag = ((double) projectss.size()) / 2.0;
			int pageLength = (int) Math.ceil(pag);
			projectList = new ProjectList(projects, pageLength);
			if (!CollectionUtils.isEmpty(projects) && projects.get(0) != null) {
				model.addAttribute("criterion", name);
				model.addAttribute("sttcriterion", "#QueriedStt");
			}
		}else {
//			Sort sortable = new Sort(Sort.Direction.ASC, "projectNumber");
//			Pageable pageable = new PageRequest(0, 2, sortable);
//			projects = projectService.findByName2(pageable, name).getContent();
//			projects = projectService.findProjectsByName(name);
			
			projects = projectService.findByNameOrNumOrCusStt(pageable,name,stt).getContent();
			projectss = projectService.findByNameOrNumOrCusStt(name,stt);
			Double pag = ((double) projectss.size()) / 2.0;
			int pageLength = (int) Math.ceil(pag);
			projectList = new ProjectList(projects, pageLength);
			// Update criterion
			if (!CollectionUtils.isEmpty(projects) && projects.get(0) != null) {
				model.addAttribute("criterion", name);
				model.addAttribute("sttcriterion", stt);
			}
		}
		System.out.println(projectList);
		return  projectList;
	}

	/**
	 * List project
	 * 
	 * @return ModelAndView("projectlist")
	 */
	@RequestMapping("/projectlistOnInit")
	@ResponseBody
	public List<Project> listProjectPage(Model model, int page) {
		List<Project> projectlist = null;
		
		Sort sortable = new Sort(Sort.Direction.ASC, "projectNumber");
		Pageable pageable = new PageRequest(page, 2, sortable);
		projectlist = projectService.findAllProject(pageable).getContent();
		return projectlist;
	}

	@RequestMapping("/resetsearch")
	String resetSearch(Model model) {

		model.addAttribute("criterion", "#Queried");
		model.addAttribute("sttcriterion", "#QueriedStt");
		return "redirect:/projectlist";
	}

	/**
	 * Project detail page handler
	 * 
	 * @param Long  prjId
	 * @param Model model
	 * @return ModelAndView("detail")
	 */
	@RequestMapping("/detail/{prjId}")
	@ResponseBody
	ModelAndView detail(@PathVariable final Long prjId, Model model) {
		final Project project = projectService.findProjectById(prjId);
		model.addAttribute("message", prjId);
		model.addAttribute("project", project);
		return new ModelAndView("detail");
	}

	/**
	 * New project
	 * 
	 * @return ModelAndView("newproject")
	 */
	@RequestMapping(value = "/newproject", method = RequestMethod.GET)
	String newProject(Model model) {
		List<Group> groups = groupRepository.findAll();

		model.addAttribute("newprojectForm", new Project());
		model.addAttribute("groups", groups);

		return "newproject";
	}

	@RequestMapping(value = "/newproject", method = RequestMethod.POST)
	String newProject(@ModelAttribute("newprojectForm") Project newprojectForm,
			@RequestParam("projectNumber") int projectNumber, BindingResult bindingResult, Model model,
			@RequestParam(value = "members") List<String> employees) {
		List<Employee> employeelist = new ArrayList<Employee>();
		for (String employee : employees) {
			String[] visalist = employee.split(", ");
			for (String li : visalist) {
				if (!StringUtils.isEmpty(li)) {
					String visa = li.substring(0, 3);
					Employee emp = employeeRepository.findByName(visa);
					employeelist.add(emp);
				}

			}

		}
		
		projectvalidator.validate(newprojectForm, bindingResult);
		if(projectNumber == -1) {
			bindingResult.rejectValue("projectNumber", "error.newprojectForm");
			model.addAttribute("errorNEmpty", "This field is mandatory");
			List<Group> groups = groupRepository.findAll();
			model.addAttribute("groups", groups);
			return "newproject";
		}
		else if (projectService.findProjectsByProjetcNumber(projectNumber) != null) {
			bindingResult.rejectValue("projectNumber", "error.newprojectForm");
			model.addAttribute("errorDupl", "The project number " + projectNumber
					+ " already existed. Please select a different project number");
			List<Group> groups = groupRepository.findAll();
			model.addAttribute("groups", groups);
			return "newproject";
		} else if (bindingResult.hasErrors()) {

			List<Group> groups = groupRepository.findAll();
			model.addAttribute("groups", groups);
			return "newproject";
		} else {
			newprojectForm.setEmployees(employeelist);
			projectService.saveNewProject(newprojectForm);
			model.addAttribute("prj", newprojectForm);
			System.out.println(newprojectForm);
			return "redirect:/projectlist";
		}
	}

	@RequestMapping(value = "/suggestion")
	@ResponseBody
	public List<String> autocompleteSuggestions(
			@RequestParam(value = "term", required = false, defaultValue = "") String term, Model model,String member) {
		List<String> ss = new ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> employees = employeeRepository.findByNameOrVisa(member);
		
		for (Employee empl : employees) {
			try {
				String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(empl.toString());
				String jsonInStringF = jsonInString2.replace('"', ' ');
				ss.add(jsonInStringF);
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
			}

			
		}
		return ss;
	}

	@RequestMapping({"/","/projectlist"})
	public String listProject(Model model) {
		return "projectlist";

	}



	/**
	 * Submit project handler
	 * 
	 * @param Project       project
	 * @param BindingResult bindingResult
	 * @param String        criterion
	 * @param Model         model
	 * @return String view
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	String detail(@Valid Project project, BindingResult bindingResult, @ModelAttribute("criterion") String criterion,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "detail";
		}
		projectService.updateProject(project);
		// Update criterion = new project's name
		if (!criterion.equals("#Queried")) {
			model.addAttribute("criterion", project.getprojectName());

		}
		return "redirect:/";
	}

	/**
	 * delete project
	 * 
	 * @param WebDataBinder binder
	 */
	@RequestMapping(value = "/deleteproject", method = RequestMethod.GET)
	public String deleteApplicant(Model model, @RequestParam("id") Long id) {
		if (id != null) {
			projectService.deleteProject(id);
		}
		return "redirect:/projectlist";
	}

	@RequestMapping(value = "/deleteproject", method = RequestMethod.POST)
	public String deleteApplicant(Model model, @RequestParam("listIdDelete") String strid) {
		System.out.println(strid);
		List<String> myList = new ArrayList<String>(Arrays.asList(strid.split(",")));
		List<Long> longs = Lists.newArrayList(Lists.transform(myList, new Function<String, Long>() {
		    public Long apply(final String in) {
		        return in == null ? null : Longs.tryParse(in);
		    }
		}));
		if (longs != null) {
			for (Long iddel : longs) {
				
				projectService.deleteProject(iddel);
				
			}
		}
		return "redirect:/projectlist";
	}
	

	/**
	 * Update project
	 * 
	 * @param WebDataBinder binder
	 */
	@RequestMapping(value = "/updateproject", method = RequestMethod.GET)
	public String updateProject(Model model, @RequestParam("id") Long id) {
		List<String> str = new ArrayList<String>();
		List<Group> groups = groupRepository.findAll();
		model.addAttribute("groups", groups);
		Project prj = projectService.findProjectById(id);
		System.out.println(prj);
		for (Employee employee : prj.getEmployees()) {
			str.add(employee.toString());
		}
		
		String strMembers = str.stream().collect(Collectors.joining(String.valueOf(", ")));
		
		model.addAttribute("member", strMembers);
		model.addAttribute("id", id);
		model.addAttribute("updateprojectForm", prj);
		return "updateproject";
	}
	

	@RequestMapping(value = "/updateproject", method = RequestMethod.POST)
	public String updateProject(Model model, @ModelAttribute("updateprojectForm") Project updateprojectForm,
			@RequestParam(value = "members") List<String> employees, BindingResult bindingResult) {

		List<Employee> employeelist = new ArrayList<Employee>();
		for (String employee : employees) {
			String[] visalist = employee.split(", ");
			for (String li : visalist) {
				if (!StringUtils.isEmpty(li)) {
					String visa = li.substring(0, 3);
					Employee emp = employeeRepository.findByName(visa);
					employeelist.add(emp);
				}

			}

		}
		projectvalidator.validate(updateprojectForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Please enter all the mandatory fields (*)");
			List<Group> groups = groupRepository.findAll();
			model.addAttribute("groups", groups);
			return "updateproject";
		} else {
			updateprojectForm.setEmployees(employeelist);
			projectService.updateProject(updateprojectForm);
			return "redirect:/projectlist";
		}

	}
	
	/**
	 * 
	 * login 
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}

	/**
	 * Format date to dd-MM-yyyy
	 * 
	 * @param WebDataBinder binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
		// public void setAsText(String value) {
		// try {
		// setValue(new SimpleDateFormat("dd-MM-yyyy").parse(value));
		// } catch (ParseException e) {
		// setValue(new Date());
		// }
		// }
		//
		// public String getAsText() {
		// return new SimpleDateFormat("dd-MM-yyyy").format((Date) getValue());
		// }
		// });
		// binder.registerCustomEditor(Date.class, new CustomDateEditor(
		// new SimpleDateFormat("dd/MM/yyyy"), true));
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}