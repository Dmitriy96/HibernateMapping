import dao.*;
import models.*;

import java.util.Date;


public class Main {

    private static TeacherDao teacherDao = new TeacherDao();
    private static GroupDao groupDao = new GroupDao();
    private static StudentDao studentDao = new StudentDao();
    private static LectionDao lectionDao = new LectionDao();
    private static GroupLectionDao groupLectionDao = new GroupLectionDao();
    private static LossStudentDao lossStudentDao = new LossStudentDao();

    public static void main(final String[] args) throws Exception {
        //fillDatabase();                                                   // TODO uncomment to fill database
        testDatabase();
    }

    private static void fillDatabase() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Ivan");
        teacher.setLastName("Ivanov");
        teacherDao.save(teacher);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Petr");
        teacher2.setLastName("Petrov");
        teacherDao.save(teacher2);

        Group group = new Group();
        group.setCreated(new Date());
        group.setCurator(teacher);
        group.setName("1");
        groupDao.save(group);

        Student student = new Student();
        student.setBirthday(new Date());
        student.setFirstName("stud1");
        student.setLastName("stud1");
        Group group1 = (Group) groupDao.list("1").toArray()[0];
        student.setGroup(group1);
        studentDao.save(student);

        Student student2 = new Student();
        student2.setBirthday(new Date());
        student2.setFirstName("stud2");
        student2.setLastName("stud2");
        student2.setGroup(group1);
        studentDao.save(student2);

        Student student3 = new Student();
        student3.setBirthday(new Date());
        student3.setFirstName("stud3");
        student3.setLastName("stud3");
        student3.setGroup(group1);
        studentDao.save(student3);

        Lection lection = new Lection();
        lection.setName("Math");
        lectionDao.save(lection);

        Lection lection1 = new Lection();
        lection1.setName("Phisics");
        lectionDao.save(lection1);

        Lection lection2 = (Lection) lectionDao.list("Math").toArray()[0];
        Teacher teacher3 = (Teacher) teacherDao.list("Ivan").toArray()[0];
        GroupLection groupLection = new GroupLection();
        groupLection.setDate(new Date());
        groupLection.setGroup(group1);
        groupLection.setLection(lection2);
        groupLection.setTeacher(teacher3);
        groupLectionDao.save(groupLection);

        LossStudent lossStudent = new LossStudent();
        lossStudent.setDate(new Date());
        lossStudent.setGroup(group1);
        lossStudent.setLection(lection2);
        lossStudent.setStudent(student3);
        lossStudent.setReason("sleeping");
        lossStudentDao.save(lossStudent);
    }

    private static void testDatabase() {
        Student student = (Student) studentDao.list("stud1").toArray()[0];
        System.out.println("last name: " + student.getLastName());
        System.out.println("group name: " + student.getGroup().getName());
        for (Student student1 : student.getGroup().getStudentList()) {
            System.out.println("students in group: " + student1.getFirstName());
        }
        System.out.println("curator last name: " + student.getGroup().getCurator().getLastName());
        for (GroupLection groupLection : student.getGroup().getCurator().getGroupLectionList()) {
            System.out.println("group lection time: " + groupLection.getDate());
        }
        LossStudent lossStudent = (LossStudent) lossStudentDao.list().toArray()[0];
        System.out.println(lossStudent.getDate() + " " + lossStudent.getStudent().getLastName() + " " + lossStudent.getLection().getName());
    }
}
