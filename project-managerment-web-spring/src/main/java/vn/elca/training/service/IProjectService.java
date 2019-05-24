package vn.elca.training.service;

import java.util.List;



import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import vn.elca.training.dom.Project;

public interface IProjectService extends ApplicationListener<ContextRefreshedEvent> {
	List<Project> findByNameOrNumOrCusStt( String projectName, String status);
	List<Project> findByNameOrNumOrCus( String Name);
	Page<Project> findByNameOrNumOrCusStt(Pageable pageable, String projectName, String status);
	Page<Project> findByNameOrNumOrCus(Pageable pageable, String Name);
	Page<Project> findByName2(Pageable pageable, String projectName);
	List<Project> findAll();
    List<Project> findProjectsByName(String prjName);
    public List<Project> findAllByOrderByProjectNumberAsc();
    Project findProjectsByProjetcNumber(int prjNumber);
	Project findProjectById(Long prjId);
	void updateProject(Project p);
    void onApplicationEvent(ContextRefreshedEvent event);
    void createMaintananceProject(Project p);
    void saveNewProject(Project p);
    void deleteProject(Long id);
    Page<Project> findAllProject(Pageable pageable);
}