package vn.elca.training.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import vn.elca.training.ApplicationLauncher;
import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Project;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationLauncher.class)
@WebIntegrationTest
@TransactionConfiguration
public class IEmployeeTest {
	 @PersistenceContext
	    private EntityManager em;
	    @Autowired
	    private IEmployeeRepository employeeRepository;
	    @Autowired
	    private IProjectRepository projectRepository;
	    @Test
	    public void testCountAllGroup() {
	    	Employee emp1= new Employee("TUV", "Nguyen Viet","Thinh");
	        employeeRepository.save(emp1);
	        Employee emp2= new Employee("AKG", "Tran Van","Thinh");
	        employeeRepository.save(emp2);
	        List<Employee> employeeList = new ArrayList<Employee>() ;
	        employeeList.add(emp1);
	        employeeList.add(emp2);
	        Project prj1 = new Project("AAAA"); 
	        Project prj2 = new Project("ABAA"); 
	        Project prj3 = new Project("AABA"); 
	        prj1.setEmployees(employeeList);
	        System.out.println(prj1);
	        for (Employee employee : prj1.getEmployees()) {
				System.out.println(employee.getId());
			}
	        Assert.assertEquals(4, employeeRepository.count());
	    }

}
