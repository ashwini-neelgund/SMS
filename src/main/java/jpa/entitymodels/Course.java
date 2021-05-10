package jpa.entitymodels;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Course")
@Entity
public class Course {

    @Id
    @Column(name = "id")
    int cId;

    @Column(name = "name")
    String cName;

    @Column(name = "instructor")
    String cInstructorName;

    @OneToMany(mappedBy = "id.course", fetch = FetchType.LAZY)
    List<StudentCourses> studentCourses = new ArrayList<>();

    /**
     * No args constructor
     */
    Course() {
        this.cId = 0;
        this.cName = null;
        this.cInstructorName = null;
        this.studentCourses = new ArrayList<StudentCourses>();
    }

    /**
     * All args constructor
     * @param cId
     * @param cName
     * @param cInstructorName
     * @param studentCourses
     */
    public Course(int cId, String cName, String cInstructorName, List<StudentCourses> studentCourses) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
        this.studentCourses = studentCourses;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cId;
        result = prime * result + ((cName == null) ? 0 : cName.hashCode());
        result = prime * result + ((cInstructorName == null) ? 0 : cInstructorName.hashCode());
        return result;
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
        Course other = (Course) obj;
        if (getCId() == other.getCId() && getCName().equals(other.getCName()) && getCInstructorName().equals(other.getCInstructorName()))
            return true;
        return false;
    }
}
