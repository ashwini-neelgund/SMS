package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;
import jpa.entitymodels.StudentCoursesId;
import jpa.util.JpaUtil;

import javax.persistence.*;
import java.util.List;

public class StudentService implements StudentDAO {

    private EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
    private EntityManager em = emf.createEntityManager();

    /**
     * This method reads the student table in your database and returns the data as a List<Student>
     *
     * @return list of Student objects
     */
    @Override
    public List<Student> getAllStudents() {
        Query query = null;
        try {
            query = em.createQuery("Select s from Student s");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

    /**
     * This method takes a Student’s email as a String and parses the student list for a Student with that email and returns a Student Object.
     *
     * @param email
     * @return Student object
     */
    @Override
    public Student getStudentByEmail(String email) {
        Student student = null;
        try {
            student = em.find(Student.class, email);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return student;
    }

    /**
     * This method takes two parameters: the first one is the user email and the second one is the password from the user input.
     *
     * @param email - student email id
     * @param pwd   - student password
     * @return Return whether or not a student was found.
     */
    @Override
    public boolean validateStudent(String email, String pwd) {
        Student student = null;
        try {
            student = em.find(Student.class, email);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (null != student && student.getSPass().equals(pwd))
            return true;
        return false;
    }

    /**
     * After a successful student validation, this method takes a Student’s email and a Course ID.
     * It checks in the join table (i.e. Student_Course) generated by JPA to find if a Student with
     * that Email is currently attending a Course with that ID.
     * If the Student is not attending that Course, register the student to that course; otherwise not.
     *
     * @param email  - student email id
     * @param course - Course object
     */
    @Override
    public void registerStudentToCourse(String email, Course course) {
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, email);
            if (validateStudent(email, student.getSPass())) {
                StudentCourses studentCourse = em.find(StudentCourses.class, new StudentCoursesId(student, course));
                //checks if student is attending this course
                if (studentCourse == null) {
                    //If not attending then register student to this course
                    studentCourse = new StudentCourses(new StudentCoursesId(student, course));
                    student.getStudentCourses().add(studentCourse);
                    em.persist(student);
                }
            }
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } catch (EntityExistsException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } catch (TransactionRequiredException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * This method takes a Student’s Email as a parameter and would find all the courses a student is registered.
     *
     * @param email - student email id
     * @return list of Course objects
     */
    @Override
    public List<Course> getStudentCourses(String email) {
        Query query = null;
        try {
            query = em.createQuery(
                    "SELECT c FROM Course c JOIN c.studentCourses sc WHERE sc.id.student.sEmail = :email");
            query.setParameter("email", email);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }
}
