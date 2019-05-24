package vn.elca.training.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import vn.elca.training.dao.IEmployeeRepository;
import vn.elca.training.dom.Employee;

public class EmployeeService implements IEmployeeService{
	
	@Autowired
	IEmployeeRepository employeeRepository;
	@Override
	public List<Employee> findAll() {
		
		return employeeRepository.findAll();
	}
	@Override
	public Employee findByName(String visa) {
		
		return employeeRepository.findByName(visa);
	}

}
