package jpa.entitymodels;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "Student")
public class Student {

    @Id
    @Column(name = "email")
    String sEmail;

    @Column(name = "name")
    String sName;

    @Column(name = "password")
    String sPass;

    @OneToMany(mappedBy = "id.student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<StudentCourses> studentCourses = new ArrayList<>();

    /**
     * No args constructor
     */
    Student() {
        this.sEmail = null;
        this.sName = null;
        this.sPass = null;
        this.studentCourses = new ArrayList<StudentCourses>();
    }

    /**
     * All args constructor
     * @param sEmail
     * @param sName
     * @param sPass
     * @param studentCourses
     */
    public Student(String sEmail, String sName, String sPass, List<StudentCourses> studentCourses) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.studentCourses = studentCourses;
    }

}
