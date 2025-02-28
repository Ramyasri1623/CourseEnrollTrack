package com.example.dao;

import com.example.model.Student;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class StudentDAO {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        return md.buildSessionFactory();
    }

    public void saveStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(student);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Student getStudentById(int id) {
        Session session = sessionFactory.openSession();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }

    public List<Student> getAllStudents() {
        Session session = sessionFactory.openSession();
        List<Student> students = session.createQuery("from Student", Student.class).list();
        session.close();
        return students;
    }
}
