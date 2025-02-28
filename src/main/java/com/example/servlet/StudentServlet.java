package com.example.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.StudentDAO;
import com.example.model.Student;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = new StudentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get student details from form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");

        // Create a Student object
        Student student = new Student(name, email, department);

        // Save the student record in the database
        studentDAO.saveStudent(student);

        // Redirect to success page
        response.sendRedirect("student-success.jsp");
    }
}
