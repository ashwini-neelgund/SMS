package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudents();

    Student getStudentByEmail(String email);

    boolean validateStudent(String email, String pwd);

    void registerStudentToCourse(String email, Course course);

    List<Course> getStudentCourses(String email);
}
