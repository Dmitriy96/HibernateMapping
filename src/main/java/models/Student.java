package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTHDATE")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    private Group group;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<LossStudent> lossStudentList;

    public Student() {}

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Collection<LossStudent> getLossStudentList() {
        return lossStudentList;
    }

    public void setLossStudentList(Collection<LossStudent> lossStudentList) {
        this.lossStudentList = lossStudentList;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 29 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 29 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 29 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 29 * result + (group != null ? group.hashCode() : 0);
        result = 29 * result + (lossStudentList != null ? lossStudentList.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Student)) return false;
        Student student = (Student) other;
        return (id != null ? id.equals(student.id) : student.id == null)
                && (firstName != null ? firstName.equals(student.firstName) : student.firstName == null)
                && (lastName != null ? lastName.equals(student.lastName) : student.lastName == null)
                && (birthday != null ? birthday.equals(student.birthday) : student.birthday == null)
                && (group != null ? group.equals(student.group) : student.group == null)
                && (lossStudentList != null ? lossStudentList.equals(student.lossStudentList) : student.lossStudentList == null);
    }
}
