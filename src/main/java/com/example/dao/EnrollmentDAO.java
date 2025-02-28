package com.example.dao;

import com.example.model.Enrollment;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class EnrollmentDAO {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        return md.buildSessionFactory();
    }

    public void enrollStudent(Enrollment enrollment) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(enrollment);
        tx.commit();
        session.close();
    }

    public List<Enrollment> getAllEnrollments() {
        Session session = sessionFactory.openSession();
        List<Enrollment> enrollments = session.createQuery("from Enrollment", Enrollment.class).list();
        session.close();
        return enrollments;
    }

    public void deleteEnrollment(int enrollmentId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Enrollment enrollment = session.get(Enrollment.class, enrollmentId);
        if (enrollment != null) {
            session.delete(enrollment);
            tx.commit();
        }
        session.close();
    }
    public void deleteEnrollmentsByCourse(int courseId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query deleteQuery = session.createQuery("DELETE FROM Enrollment WHERE course.id = :courseId");
            deleteQuery.setParameter("courseId", courseId);
            deleteQuery.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

}
