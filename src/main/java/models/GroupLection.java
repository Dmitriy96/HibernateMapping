package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "group_lection")
public class GroupLection implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    private Group group;
    @Id
    @ManyToOne
    @JoinColumn(name = "LECTION_ID", referencedColumnName = "ID")
    private Lection lection;
    @Id
    @Column(name = "DATE")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID", nullable = false)
    private Teacher teacher;

    public GroupLection() {}

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lection getLection() {
        return lection;
    }

    public void setLection(Lection lection) {
        this.lection = lection;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int result = group != null ? group.hashCode() : 0;
        result = 29 * result + (lection != null ? lection.hashCode() : 0);
        result = 29 * result + (date != null ? date.hashCode() : 0);
        result = 29 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof GroupLection)) return false;
        GroupLection groupLection = (GroupLection) other;
        return (group != null ? group.equals(groupLection.group) : groupLection.group == null)
                && (lection != null ? lection.equals(groupLection.lection) : groupLection.lection == null)
                && (date != null ? date.equals(groupLection.date) : groupLection.date == null)
                && (teacher != null ? teacher.equals(groupLection.teacher) : groupLection.teacher == null);
    }
}
