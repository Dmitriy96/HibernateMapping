package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "loss_student")
public class LossStudent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOSS_STUDENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "LECTION_ID", referencedColumnName = "ID")
    private Lection lection;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "REASON")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    private Group group;

    public LossStudent() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 29 * result + (student != null ? student.hashCode() : 0);
        result = 29 * result + (lection != null ? lection.hashCode() : 0);
        result = 29 * result + (date != null ? date.hashCode() : 0);
        result = 29 * result + (reason != null ? reason.hashCode() : 0);
        result = 29 * result + (group != null ? group.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof LossStudent)) return false;
        LossStudent lossStudent = (LossStudent) other;
        return (id != null ? id.equals(lossStudent.id) : lossStudent.id == null)
                && (student != null ? student.equals(lossStudent.student) : lossStudent.student == null)
                && (lection != null ? lection.equals(lossStudent.lection) : lossStudent.lection == null)
                && (date != null ? date.equals(lossStudent.date) : lossStudent.date == null)
                && (reason != null ? reason.equals(lossStudent.reason) : lossStudent.reason == null)
                && (group != null ? group.equals(lossStudent.group) : lossStudent.group == null);
    }
}
