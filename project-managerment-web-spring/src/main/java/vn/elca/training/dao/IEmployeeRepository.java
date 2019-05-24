package vn.elca.training.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import vn.elca.training.dom.Employee;


public interface IEmployeeRepository extends JpaRepository<Employee, Long>,QueryDslPredicateExecutor<Employee> {
	@Query("Select e from Employee e where e.visa like :visa")
    Employee findByName(@Param("visa") String visa);
	
	@Query("Select e from Employee e where lower(e.visa) like lower(concat('%',:member,'%')) or lower(e.firstname) like lower(concat('%',:member,'%')) or lower(e.lastname) like lower(concat('%',:member,'%'))" )
    List<Employee> findByNameOrVisa(@Param("member") String member);

}
