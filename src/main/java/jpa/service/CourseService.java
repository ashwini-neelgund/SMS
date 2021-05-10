package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class CourseService implements CourseDAO {

    private EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
    private EntityManager em = emf.createEntityManager();

    /**
     * This method takes no parameter and returns every Course in the table.
     *
     * @return list of Course objects
     */
    @Override
    public List<Course> getAllCourses() {
        Query query = null;
        try {
            query = em.createQuery("Select c from Course c");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

    /**
     * retrieves Course object based on the courseNum attribute provided.
     *
     * @param courseNum integer attribute
     * @return Course object
     */
    @Override
    public Course getCourseById(int courseNum) {
        Course course = null;
        try {
            course = em.find(Course.class, courseNum);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return course;
    }
}
