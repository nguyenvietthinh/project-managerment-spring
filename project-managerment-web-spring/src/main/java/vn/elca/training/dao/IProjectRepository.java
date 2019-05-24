package vn.elca.training.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.elca.training.dom.Project;

@Transactional
@Repository
public interface IProjectRepository extends JpaRepository<Project, Long>, QueryDslPredicateExecutor<Project> {
	@Query("Select p from Project  p where lower(p.projectName) like lower(concat('%',:projectName,'%') ) ")
	List<Project> findByName(@Param("projectName") String projectName);

	@Query("Select p from Project  p where p.projectNumber = :projectNumber")
	Project findByProjectNumber(@Param("projectNumber") int projectNumber);

	@Modifying
	@Query("delete from Project p where lower(p.projectName) like lower(:projectName)")
	void deleteByName(@Param("projectName") String projectName);

	@Modifying
	@Query("delete from Project p where p.id = :id and p.status = 'NEW' ")
	void deleteProject(@Param("id") Long id);

	@Query("Select p from Project  p where p.id = :id)")
	Project findById(@Param("id") Long id);

	public List<Project> findAllByOrderByProjectNumberAsc();

	@Query("Select p from Project p ")
	Page<Project> findAllProject(Pageable pageable);

	@Query("Select p from Project  p where lower(p.projectName) like lower(concat('%',:projectName,'%')) ")
	Page<Project> findByName2(Pageable pageable, @Param("projectName") String projectName);

	@Query("Select p from Project  p where (lower(p.projectName) like lower(concat('%',:projectName,'%')) or lower(p.customer) like lower(concat('%',:projectName,'%')) or "
			+ "LTRIM(STR(p.projectNumber,10)) =:projectName)   ")
	List<Project> findByNameOrNumOrCus(@Param("projectName") String projectName);
	
	@Query("Select p from Project  p where (lower(p.projectName) like lower(concat('%',:projectName,'%')) or lower(p.customer) like lower(concat('%',:projectName,'%')) or "
			+ "LTRIM(STR(p.projectNumber,10)) =:projectName) and LTRIM(STR(p.status,3))  LIKE CONCAT('%',:status,'%')  ")
	List<Project> findByNameOrNumOrCusStt(@Param("projectName") String projectName,@Param("status") String status);
	
	@Query("Select p from Project  p where (lower(p.projectName) like lower(concat('%',:projectName,'%')) or lower(p.customer) like lower(concat('%',:projectName,'%')) or "
			+ "LTRIM(STR(p.projectNumber,10)) =:projectName)   ")
	Page<Project> findByNameOrNumOrCus(Pageable pageable,@Param("projectName") String projectName);
	
	@Query("Select p from Project  p where (lower(p.projectName) like lower(concat('%',:projectName,'%')) or lower(p.customer) like lower(concat('%',:projectName,'%')) or "
			+ "LTRIM(STR(p.projectNumber,10)) =:projectName) and LTRIM(STR(p.status,3))  LIKE CONCAT('%',:status,'%')  ")
	Page<Project> findByNameOrNumOrCusStt(Pageable pageable,@Param("projectName") String projectName,@Param("status") String status);

}