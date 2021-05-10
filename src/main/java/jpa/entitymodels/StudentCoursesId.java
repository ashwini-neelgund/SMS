/*
 * Filename: StudentCourseId.java
 * Author: Stefanski
 * 02/25/2020
 */
package jpa.entitymodels;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Key class for composite key in Student Courses table
 *
 * @author Harry
 */
@Embeddable
public class StudentCoursesId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Student student;
    private Course course;

    public StudentCoursesId() {
    }

    public StudentCoursesId(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + course.hashCode();
        result = prime * result + ((student == null) ? 0 : student.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentCoursesId other = (StudentCoursesId) obj;
        if (student != null ? !student.equals(other.student) : other.student != null)
            return false;
        if (course != null ? !course.equals(other.course) : other.course != null)
            return false;
        return true;
    }
}