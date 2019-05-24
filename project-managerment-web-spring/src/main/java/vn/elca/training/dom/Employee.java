package vn.elca.training.dom;


import java.util.Date;
import java.util.List;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;


@Entity
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private Long id;
    @Column
    private String visa;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private Date birthday;
    @Column
    @Version
    private int version;
    @ManyToMany(mappedBy="employees")
    private List<Project> projects;
    
    
    //Getter-Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getVisa() {
        return visa;
    }
    public void setVisa(String visa) {
        this.visa = visa;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    
    //Constructor
    public Employee() {
        
    }
    
    public Employee(String visa) {
        this.visa = visa;
    }
    
    public Employee(String visa, String firstname,String lastname) {
    	this.visa = visa;
    	this.firstname = firstname;
    	this.lastname = lastname;
    }
    @Override
    public String toString() {
      return "" + visa + ":" +firstname+" "+lastname+"";
    }
	
}
