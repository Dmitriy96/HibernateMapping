package models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToMany(mappedBy="curator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Group> groupList;

    @OneToMany(mappedBy="teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<GroupLection> groupLectionList;

    public Teacher() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(Collection<Group> groupList) {
        this.groupList = groupList;
    }

    public Collection<GroupLection> getGroupLectionList() {
        return groupLectionList;
    }

    public void setGroupLectionList(Collection<GroupLection> groupLectionList) {
        this.groupLectionList = groupLectionList;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 29 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 29 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 29 * result + (groupList != null ? groupList.hashCode() : 0);
        result = 29 * result + (groupLectionList != null ? groupLectionList.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Teacher)) return false;
        Teacher teacher = (Teacher) other;
        return (id != null ? id.equals(teacher.id) : teacher.id == null)
                && (firstName != null ? firstName.equals(teacher.firstName) : teacher.firstName == null)
                && (lastName != null ? lastName.equals(teacher.lastName) : teacher.lastName == null)
                && (groupList != null ? groupList.equals(teacher.groupList) : teacher.groupList == null)
                && (groupLectionList != null ? groupLectionList.equals(teacher.groupLectionList) : teacher.groupLectionList == null);
    }
}
