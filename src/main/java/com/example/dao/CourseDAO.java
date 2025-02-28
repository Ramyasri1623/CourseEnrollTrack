package com.example.dao;

import com.example.model.Course;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAO {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        return md.buildSessionFactory();
    }

    public Course getCourseById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Course.class, id);
        }
    }

    public List<Course> getAllCourses() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Course", Course.class).list();
        }
    }

    // ✅ Hibernate method to check if a course exists
    public boolean courseExists(String courseName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(
                "SELECT COUNT(c) FROM Course c WHERE c.courseName = :courseName", Long.class
            );
            query.setParameter("courseName", courseName);
            Long count = query.uniqueResult();
            return count != null && count > 0; // Returns true if a course with the same name exists
        }
    }

    // ✅ Updated addCourse() to prevent duplicate courses
    public boolean addCourse(Course course) {
        if (!courseExists(course.getCourseName())) { // Prevent duplicate entry
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.save(course);
                transaction.commit();
                return true; // Course added successfully
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
        }
        return false; // Course already exists
    }
    public void deleteCourse(int courseId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query<?> deleteEnrollmentsQuery = session.createQuery("DELETE FROM Enrollment e WHERE e.course.id = :courseId");
            deleteEnrollmentsQuery.setParameter("courseId", courseId);
            deleteEnrollmentsQuery.executeUpdate();

            Query<?> deleteCourseQuery = session.createQuery("DELETE FROM Course c WHERE c.id = :courseId");
            deleteCourseQuery.setParameter("courseId", courseId);
            deleteCourseQuery.executeUpdate();

            session.flush();  // Clear Hibernate cache
            session.clear();

            transaction.commit();
            System.out.println("Course deleted successfully: " + courseId);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public Course getCourseByName(String courseName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> query = session.createQuery("FROM Course WHERE courseName = :name", Course.class);
            query.setParameter("name", courseName);
            return query.uniqueResult(); // Returns null if course doesn't exist
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
