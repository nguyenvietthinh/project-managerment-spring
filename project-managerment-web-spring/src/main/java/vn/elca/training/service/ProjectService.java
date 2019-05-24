package vn.elca.training.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.elca.training.dao.IEmployeeRepository;
import vn.elca.training.dao.IGroupRepository;
import vn.elca.training.dao.IProjectRepository;

import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;
import vn.elca.training.dom.Project;
import vn.elca.training.dom.Project.Status;

@Service
@Transactional
public class ProjectService implements IProjectService {
	@Autowired
	private IProjectRepository projectRepository;

	@Autowired
	private IGroupRepository groupRepository;

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Employee emp1 = new Employee("TUV", "Nguyen Viet", "Thinh");
		employeeRepository.save(emp1);
		Employee emp2 = new Employee("AKG", "Tran Van", "Thinh");
		employeeRepository.save(emp2);
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(emp1);
		
		List<Project> dummyData = new ArrayList<Project>();
		Date date = new Date();
		Project prj1 = new Project("John", date);
		prj1.setCustomer("An");
		Status status = Status.NEW;
		prj1.setStatus(status);
		prj1.setEmployees(employeeList);

		Project prj2 = new Project("Tom", date, 123);
		prj2.setCustomer("Khang");
		Status status2 = Status.PLA;
		prj2.setStatus(status2);

		Project prj3 = new Project("Jerry", date, 345);
		prj3.setCustomer("Thinh");
		Status status3 = Status.INP;
		prj3.setStatus(status3);
		
		Project prj4 = new Project("Alen", date, 456);
		prj4.setCustomer("Vuong");
		Status status4 = Status.NEW;
		prj4.setStatus(status4);
		
		Project prj5 = new Project("Peter", date, 789);
		prj5.setCustomer("Chuc Mung");
		Status status5 = Status.FIN;
		prj5.setStatus(status5);
		
		Project prj6 = new Project("Tome", date, 799);
		prj6.setCustomer("Nam Moi");
		Status status6 = Status.PLA;
		prj6.setStatus(status6);
		dummyData.add(prj1);
		dummyData.add(prj2);
		dummyData.add(prj3);
		dummyData.add(prj4);
		dummyData.add(prj5);
		dummyData.add(prj6);
		projectRepository.save(dummyData);
		List<Group> groupList = new ArrayList<Group>();
		groupList.add(new Group());
		groupList.add(new Group());
		groupList.add(new Group());
		groupRepository.save(groupList);

	}

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public List<Project> findProjectsByName(String prjName) {
		return projectRepository.findByName(prjName);
	}

	@Override
	public Project findProjectById(Long prjId) {
		updateProject(null);
		return projectRepository.findOne(prjId);
	}

	@Override
	public void updateProject(Project p) {
		if (p != null && p.getId() != null) {
			projectRepository.save(p);
		}
	}

	@Override
	public void createMaintananceProject(Project p) {
		Calendar cal = Calendar.getInstance();
		p.setFinishingDate(new Date());

		Project a = new Project(p.getprojectName() + "Maint." + cal.get(Calendar.YEAR) + ".");
		projectRepository.save(p);
		projectRepository.save(a);

	}

	@Override
	public void saveNewProject(Project project) {
		projectRepository.save(project);

	}

	@Override
	public void deleteProject(Long id) {
		projectRepository.deleteProject(id);

	}

	@Override
	public Project findProjectsByProjetcNumber(int prjNumber) {
		return projectRepository.findByProjectNumber(prjNumber);

	}

	@Override
	public List<Project> findAllByOrderByProjectNumberAsc() {

		return projectRepository.findAllByOrderByProjectNumberAsc();
	}

	@Override
	public Page<Project> findAllProject(Pageable pageable) {

		return projectRepository.findAllProject(pageable);
	}

	@Override
	public Page<Project> findByName2(Pageable pageable, String projectName) {

		return projectRepository.findByName2(pageable, projectName);
	}

	@Override
	public List<Project> findByNameOrNumOrCusStt(String Name, String stt) {

		return projectRepository.findByNameOrNumOrCusStt(Name, stt);
	}

	@Override
	public List<Project> findByNameOrNumOrCus(String projectName) {

		return projectRepository.findByNameOrNumOrCus(projectName);
	}

	@Override
	public Page<Project> findByNameOrNumOrCusStt(Pageable pageable, String projectName, String status) {
		
		return projectRepository.findByNameOrNumOrCusStt(pageable, projectName, status);
	}

	@Override
	public Page<Project> findByNameOrNumOrCus(Pageable pageable, String Name) {
		
		return projectRepository.findByNameOrNumOrCus(pageable, Name);
	}

}