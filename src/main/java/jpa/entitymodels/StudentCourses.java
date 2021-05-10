/*
 * Filename: StudentCourses.java
 * Author: Stefanski
 * 02/25/2020
 */
package jpa.entitymodels;

import javax.persistence.*;

/**
 * @author Harry
 */
@Entity
@Table(name = "student_courses")
@AssociationOverrides({
        @AssociationOverride(name = "student", joinColumns = @JoinColumn(name = "sEmail")),
        @AssociationOverride(name = "course", joinColumns = @JoinColumn(name = "cId"))
})
public class StudentCourses {

    private StudentCoursesId id = new StudentCoursesId();

    public StudentCourses() {
    }

    public StudentCourses(StudentCoursesId id) {
        this.id = id;
    }

    @EmbeddedId
    public StudentCoursesId getId() {
        return id;
    }

    public void setId(StudentCoursesId id) {
        this.id = id;
    }

    @Transient
    public Student getStudent() {
        return getId().getStudent();
    }

    public void setStudent(Student student) {
        getId().setStudent(student);
    }

    @Transient
    public Course getCourse() {
        return getId().getCourse();
    }

    public void setCourse(Course course) {
        getId().setCourse(course);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (getId() != null ? getId().hashCode() : 0);
    }

    /* (non-Javadoc)
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
        StudentCourses other = (StudentCourses) obj;
        if (getId() != null ? !getId().equals(other.getId()) : other.getId() != null)
            return false;
        return true;
    }

}
