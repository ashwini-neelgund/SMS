/*
 * Filename: SMSRunner.java
 * Author: Stefanski
 * 02/25/2020
 */
package jpa.mainrunner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * 1
 *
 * @author Harry
 */
public class SMSRunner {

    private Scanner sin;
    private StringBuilder sb;

    private CourseService courseService;
    private StudentService studentService;
    private Student currentStudent;

    public SMSRunner() {
        sin = new Scanner(System.in);
        sb = new StringBuilder();
        courseService = new CourseService();
        studentService = new StudentService();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        SMSRunner sms = new SMSRunner();
        sms.run();
    }

    private void run() {
        int operation;
        // Login or quit
        while ((operation = menu1()) != 2) {
            switch (operation) {
                case 1:
                    if (studentLogin()) {
                        registerMenu();
                    }
                    break;
                default:
                    out.println("Please select a valid option from the menu!");
                    break;
            }
        }
    }

    /**
     * Main menu for student login
     *
     * @return user selected menu option
     */
    private int menu1() {
        sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
        out.print(sb.toString());
        sb.delete(0, sb.length());
        return sin.nextInt();
    }

    private boolean studentLogin() {
        boolean retValue = false;
        out.print("Enter your email address: ");
        String email = sin.next();
        out.print("Enter your password: ");
        String password = sin.next();

        currentStudent = studentService.getStudentByEmail(email);

        //If student has successfully logged in then display the courses that the student has registered.
        if (currentStudent != null && currentStudent.getSPass().equals(password)) {
            List<Course> courses = studentService.getStudentCourses(email);
            out.println("MyClasses");
            out.printf("%5s%35S%25s\n", "ID", "Course", "Instructor");
            for (Course course : courses) {
                out.printf("%5d%35S%25s\n", course.getCId(), course.getCName(), course.getCInstructorName());
            }
            retValue = true;
        } else {
            out.println("User Validation failed. GoodBye!");
        }
        return retValue;
    }

    private void registerMenu() {

        int option;
        while ((option = menu2()) != 2) {
            switch (option) {
                case 1:
                    //get list of all courses that the student has not registered
                    List<Course> allCourses = courseService.getAllCourses();
                    List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getSEmail());
                    allCourses.removeAll(studentCourses);

                    //Display all courses available
                    out.printf("%5s%35S%25s\n", "ID", "Course", "Instructor");
                    for (Course course : allCourses) {
                        out.printf("%5d%35S%25s\n", course.getCId(), course.getCName(), course.getCInstructorName());
                    }
                    out.println();

                    //select a course from the list of courses
                    out.print("Enter Course Number: ");
                    int number = sin.nextInt();

                    //based on course number retrieve the course object
                    Course newCourse = courseService.getCourseById(number);

                    //check if the course exists by that course number
                    if (newCourse != null) {

                        //register student if the course exists
                        studentService.registerStudentToCourse(currentStudent.getSEmail(), newCourse);
                        Student temp = studentService.getStudentByEmail(currentStudent.getSEmail());

                        //display all the courses that the student has registered
                        List<Course> sCourses = studentService.getStudentCourses(temp.getSEmail());
                        out.println("MyClasses");
                        out.printf("%5s%35S%25s\n", "ID", "Course", "Instructor");
                        for (Course course : sCourses) {
                            out.printf("%5d%35S%25s\n", course.getCId(), course.getCName(), course.getCInstructorName());
                        }
                    } else {

                        //If the course number selected by user does not exist then display message
                        out.println("Please choose valid course from the list!");
                    }
                    break;
                default:
                    out.println("Please select valid option from menu!");
                    break;
            }
        }
    }

    /**
     * sub menu for course registration
     *
     * @return user selected menu option
     */
    private int menu2() {
        sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
        out.print(sb.toString());
        sb.delete(0, sb.length());
        return sin.nextInt();
    }
}
