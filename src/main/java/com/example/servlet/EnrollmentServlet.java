package com.example.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.example.dao.EnrollmentDAO;
import com.example.dao.StudentDAO;
import com.example.dao.CourseDAO;
import com.example.model.Enrollment;
import com.example.model.Student;
import com.example.model.Course;

@WebServlet("/EnrollmentServlet")
public class EnrollmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private CourseDAO courseDAO = new CourseDAO();

    // ✅ Handle Enrollment and Deletion
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("delete".equals(action)) {
                // ✅ Handle Delete Enrollment
                String enrollmentIdStr = request.getParameter("enrollmentId");
                if (enrollmentIdStr == null || enrollmentIdStr.isEmpty()) {
                    response.sendRedirect("error.jsp?message=Enrollment ID is required");
                    return;
                }

                int enrollmentId = Integer.parseInt(enrollmentIdStr);
                enrollmentDAO.deleteEnrollment(enrollmentId);
                response.sendRedirect("view-enrollments.jsp?message=Enrollment Deleted Successfully");

            } else {
                // ✅ Handle New Enrollment
                String studentIdStr = request.getParameter("studentId");
                String courseIdStr = request.getParameter("courseId");

                // Validate parameters
                if (studentIdStr == null || studentIdStr.isEmpty() || courseIdStr == null || courseIdStr.isEmpty()) {
                    response.sendRedirect("error.jsp?message=Student ID and Course ID are required");
                    return;
                }

                int studentId = Integer.parseInt(studentIdStr);
                int courseId = Integer.parseInt(courseIdStr);

                Student student = studentDAO.getStudentById(studentId);
                Course course = courseDAO.getCourseById(courseId);

                if (student != null && course != null) {
                    Enrollment enrollment = new Enrollment(student, course);
                    enrollmentDAO.enrollStudent(enrollment);
                    response.sendRedirect("enrollment-success.jsp");
                } else {
                    response.sendRedirect("error.jsp?message=Invalid Student or Course");
                }
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?message=Invalid input format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An error occurred during enrollment");
        }
    }

    // ✅ Handle Viewing Enrollments (GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
            request.setAttribute("enrollments", enrollments);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view-enrollments.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Unable to fetch enrollments");
        }
    }
}
