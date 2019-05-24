package vn.elca.tranning.validator;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.elca.training.dom.Project;
import vn.elca.training.service.IProjectService;

@Component
public class ProjectValidator implements Validator{

  @Autowired
  IProjectService projectService;
  
  @Override
  public boolean supports(Class<?> aClass) {
      return Project.class.equals(aClass);           
  }

@Override
public void validate(Object o, Errors error1) {
  
   Project p = (Project) o;
   ValidationUtils.rejectIfEmptyOrWhitespace(error1, "projectNumber", "NotEmpty");
   ValidationUtils.rejectIfEmptyOrWhitespace(error1, "projectName", "NotEmpty");
   ValidationUtils.rejectIfEmptyOrWhitespace(error1, "customer", "NotEmpty");
   ValidationUtils.rejectIfEmptyOrWhitespace(error1, "group", "NotEmpty");
   ValidationUtils.rejectIfEmptyOrWhitespace(error1, "status", "NotEmpty");
   ValidationUtils.rejectIfEmptyOrWhitespace(error1, "startDate", "NotEmpty");
   if (p.getprojectName().length() < 3 || p.getprojectName().length() > 32) {
       error1.rejectValue("projectName", "Size.projectName");
   }
   if (((Project) o).getStartDate() != null && ((Project) o).getFinishingDate() != null) {
	   if(((Project) o).getFinishingDate().getTime() <= (new Date().getTime())) {
		   error1.rejectValue("finishingDate", "finishingDate.future");
	   }else if (((Project) o).getStartDate().getTime() >= (((Project) o).getFinishingDate().getTime())) {
           error1.rejectValue("startDate", "startdate.after.finishingDate");
       }
   }
}
    
}
