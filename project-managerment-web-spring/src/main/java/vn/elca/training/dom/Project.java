package vn.elca.training.dom;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column
    private int projectNumber;
    @Column(nullable = false)
//    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "UserprojectName must be alphanumeric with no spaces")
    private String projectName;
    @Column
    private String customer;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private Date startDate;
    @Column
    private Date finishingDate;
    @Column
    @Version
    private int version;
    
    @ManyToOne
    @JoinColumn
    private Group group;
    
//    @ManyToMany(mappedBy = "projects",fetch = FetchType.LAZY)
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PROJECT_EMPLOYEE", joinColumns = { @JoinColumn(name = "EMPLOYEE_ID") }, inverseJoinColumns = { @JoinColumn(name = "ID") })
    private List<Employee> employees;
//    @ManyToMany
//    @JoinTable(
//      name = "PROJECT_EMPLOYEE", 
//      joinColumns = @JoinColumn(name = "ID"), 
//      inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
//    private List<Employee> employees;
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


		
	public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

   

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getprojectName() {
        return projectName;
    }

    public void setprojectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(Date finishingDate) {
        this.finishingDate = finishingDate;
    }

    public Project() {
    }

    public Project(String projectName, Date startDate) {
        this.projectName = projectName;
        this.startDate = startDate;
    }
    
    public Project(String projectName, Date startDate, int projectNumber) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.projectNumber = projectNumber;
    }

    public Project( String projectName) {
     
        this.projectName = projectName;
        
    }

    public Project(String projectName, Group group) {
        this.projectName = projectName;
        this.group = group;
    }

    public Long getId() {
        return id;
    }
    @Override
    public String toString() {
      return "Project [id=" + id + ", projectName=" + projectName + ", Group=" + group +", employee="+employees.size()+",customer=" +customer+", finish date=" + finishingDate+ "]";
    }
    
    public enum Status {
        NEW, 
        PLA, 
        INP, 
        FIN;
    }

}