package vn.elca.training.dom;

import java.util.List;

public class ProjectList {
 
	private List<Project> projectlist;
	private int pageLength;
	public List<Project> getProjectlist() {
		return projectlist;
	}
	public void setProjectlist(List<Project> projectlist) {
		this.projectlist = projectlist;
	}
	public int getPageLength() {
		return pageLength;
	}
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}
	public ProjectList(List<Project> projectlist, int pageLength) {
		super();
		this.projectlist = projectlist;
		this.pageLength = pageLength;
	}
	@Override
	public String toString() {
		return "ProjectList [projectlist=" + projectlist + ", pageLength=" + pageLength + "]";
	}
	public ProjectList() {
		super();
		
	}
	
}
