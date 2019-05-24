package vn.elca.training.dom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "group_table")
public class Group {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long groupLeadId;
    @Column
    @Version
    private int version;
    
    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projects = new ArrayList<Project>();

    public Group() {
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Group(Long id, Long groupLeadId) {
        this.id = id;
        this.groupLeadId = groupLeadId;
    }

    public Group(Long i) {
        this.id = i;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupLeadId() {
        return groupLeadId;
    }

    public void setGroupLeadId(Long groupLeadId) {
        this.groupLeadId = groupLeadId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    @Override
    public String toString() {
      return "Group [id=" + id + "]";
    }
}
