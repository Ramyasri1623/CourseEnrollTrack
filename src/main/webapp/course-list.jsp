<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.model.Course" %>
<html>
<head>
    <title>📚 Course List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            text-align: center;
            margin-top: 20px;
        }
        .container {
            width: 80%;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            margin-top: 10px;
        }
        .btn-danger {
            transition: 0.2s ease;
        }
        .btn-danger:hover {
            background-color: #c82333;
        }
        .btn-success {
            transition: 0.2s ease;
        }
        .btn-success:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mb-4">📚 Course List</h2>

        <!-- ✅ Dynamic message display -->
        <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-info"><%= request.getAttribute("message") %></div>
        <% } %>

        <table class="table table-bordered table-hover">
            <thead class="table-light">
                <tr>
                    <th>📌 Course ID</th>
                    <th>📖 Course Name</th>
                    <th>👨‍🏫 Instructor</th>
                    <th>⚙️ Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Course> courses = (List<Course>) request.getAttribute("courses");
                    if (courses != null && !courses.isEmpty()) {
                        for (Course course : courses) {
                %>
                <tr>
                    <td><%= course.getCourseId() %></td>
                    <td><%= course.getCourseName() %></td>
                    <td><%= course.getInstructor() %></td>
                    <td>
                        <!-- Delete Button with Modal -->
                        <button class="btn btn-danger btn-sm" onclick="confirmDelete(<%= course.getCourseId() %>)">🗑️ Delete</button>
                    </td>
                </tr>
                <% } } else { %>
                <tr><td colspan="4" class="text-danger fw-bold">⚠️ No courses found.</td></tr>
                <% } %>
            </tbody>
        </table>

        <!-- ✅ Navigation buttons -->
        <div class="mt-3">
            <a href="index.jsp" class="btn btn-primary">🏠 Home</a>
            <a href="add-course.jsp" class="btn btn-success">➕ Add Course</a>
        </div>
    </div>

    <!-- Delete Confirmation Script -->
    <script>
        function confirmDelete(courseId) {
            if (confirm("Are you sure you want to delete this course?")) {
                window.location.href = 'CourseServlet?action=delete&courseId=' + courseId;
            }
        }
    </script>
</body>
</html>
