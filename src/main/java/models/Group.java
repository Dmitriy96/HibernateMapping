package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@Table(name = "\"group\"")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATED")
    private Date created;

    @ManyToOne
    @JoinColumn(name = "CURATOR_ID", referencedColumnName = "ID", nullable = false)
    private Teacher curator;

    @OneToMany(mappedBy="group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Student> studentList;

    @OneToMany(mappedBy="group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<GroupLection> groupLections;

    public Group() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Teacher getCurator() {
        return curator;
    }

    public void setCurator(Teacher curator) {
        this.curator = curator;
    }

    public Collection<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Collection<Student> studentList) {
        this.studentList = studentList;
    }

    public Collection<GroupLection> getGroupLections() {
        return groupLections;
    }

    public void setGroupLections(Collection<GroupLection> groupLections) {
        this.groupLections = groupLections;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 29 * result + (name != null ? name.hashCode() : 0);
        result = 29 * result + (created != null ? created.hashCode() : 0);
        result = 29 * result + (curator != null ? curator.hashCode() : 0);
        result = 29 * result + (groupLections != null ? groupLections.hashCode() : 0);
        result = 29 * result + (studentList != null ? studentList.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Group)) return false;
        Group group = (Group) other;
        return (id != null ? id.equals(group.id) : group.id == null)
                && (name != null ? name.equals(group.name) : group.name == null)
                && (created != null ? created.equals(group.created) : group.created == null)
                && (curator != null ? curator.equals(group.curator) : group.curator == null)
                && (groupLections != null ? groupLections.equals(group.groupLections) : group.groupLections == null)
                && (studentList != null ? studentList.equals(group.studentList) : group.studentList == null);
    }
}
