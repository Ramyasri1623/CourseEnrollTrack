<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.model.Student, com.example.model.Course" %>
<%@ page import="com.example.dao.StudentDAO, com.example.dao.CourseDAO" %>

<%
    StudentDAO studentDAO = new StudentDAO();
    CourseDAO courseDAO = new CourseDAO();
    List<Student> students = studentDAO.getAllStudents();
    List<Course> courses = courseDAO.getAllCourses();
%>

<html>
<head>
    <title>Enroll Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            text-align: center;
            margin-top: 50px;
        }
        .container {
            background: white;
            width: 350px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
            text-align: left;
        }
        h2 {
            margin-bottom: 15px;
        }
        select, input[type="submit"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }
        select {
            background-color: #fff;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .back-link {
            display: inline-block;
            margin-top: 10px;
            text-decoration: none;
            color: #007bff;
            font-size: 14px;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>📌 Enroll Student</h2>
        <form action="EnrollmentServlet" method="post">
            
            <label>👤 Student:</label>
            <select name="studentId" required>
                <% for (Student student : students) { %>
                    <option value="<%= student.getStudentId() %>"><%= student.getName() %></option>
                <% } %>
            </select>

            <label>📚 Course:</label>
            <select name="courseId" required>
                <% for (Course course : courses) { %>
                    <option value="<%= course.getCourseId() %>"><%= course.getCourseName() %></option>
                <% } %>
            </select>

            <input type="submit" value="✅ Enroll">
        </form>
        <a href="index.jsp" class="back-link">🏠 Back to Home</a>
    </div>
</body>
</html>
