package com.example.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.CourseDAO;
import com.example.dao.EnrollmentDAO;
import com.example.model.Course;

@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseDAO courseDAO = new CourseDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "list":
                    listCourses(request, response);
                    break;
                case "view":
                    viewCourse(request, response);
                    break;
                case "delete": // ✅ Improved deletion handling
                    deleteCourse(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addCourse(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        }
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("course-list.jsp").forward(request, response);
    }

    private void viewCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Course course = courseDAO.getCourseById(courseId);

            if (course != null) {
                request.setAttribute("course", course);
                request.getRequestDispatcher("course-details.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp");
        }
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseName = request.getParameter("courseName");
        String instructor = request.getParameter("instructor");

        if (courseName != null && instructor != null && !courseName.isEmpty() && !instructor.isEmpty()) {
            if (courseDAO.courseExists(courseName)) { // ✅ Check for duplicates
                request.setAttribute("message", "❌ Course already exists!");
                request.getRequestDispatcher("add-course.jsp").forward(request, response);
            } else {
                Course course = new Course();
                course.setCourseName(courseName);
                course.setInstructor(instructor);
                courseDAO.addCourse(course);
                response.sendRedirect("CourseServlet?action=list");
            }
        } else {
            request.setAttribute("message", "❌ Course name and instructor cannot be empty!");
            request.getRequestDispatcher("add-course.jsp").forward(request, response);
        }
    }

    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO(); // Instance of EnrollmentDAO

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int courseId = Integer.parseInt(request.getParameter("courseId"));

            // ✅ Use the instance to call the method
            enrollmentDAO.deleteEnrollmentsByCourse(courseId); 
            courseDAO.deleteCourse(courseId);

            response.sendRedirect("course-list.jsp?message=Course Deleted Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error Deleting Course");
        }
    }


}
