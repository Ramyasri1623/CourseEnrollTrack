<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.model.Enrollment" %>
<html>
<head>
    <title>Enrollment List</title>
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
        h2 {
            margin-bottom: 20px;
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
    </style>
    <script>
        function deleteEnrollment(enrollmentId) {
            if (confirm("Are you sure you want to delete this enrollment?")) {
                fetch('EnrollmentServlet', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: 'action=delete&enrollmentId=' + enrollmentId
                }).then(response => {
                    if (response.ok) {
                        alert("Enrollment deleted successfully!");
                        window.location.reload();
                    } else {
                        alert("Failed to delete enrollment");
                    }
                });
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>📋 Enrollment List</h2>

        <table class="table table-hover table-bordered">
            <thead class="table-light">
                <tr>
                    <th>Enrollment ID</th>
                    <th>Student Name</th>
                    <th>Course Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Enrollment> enrollments = (List<Enrollment>) request.getAttribute("enrollments");
                    if (enrollments != null && !enrollments.isEmpty()) {
                        for (Enrollment enrollment : enrollments) {
                %>
                <tr>
                    <td><%= enrollment.getEnrollId() %></td>
                    <td><%= enrollment.getStudent().getName() %></td>
                    <td><%= enrollment.getCourse().getCourseName() %></td>
                    <td>
                        <button class="btn btn-danger btn-sm" onclick="deleteEnrollment(<%= enrollment.getEnrollId() %>)">🗑 Delete</button>
                    </td>
                </tr>
                <% } } else { %>
                <tr>
                    <td colspan="4" class="text-danger">No enrollments found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <a href="index.jsp" class="btn btn-primary mt-3">🏠 Back to Home</a>
    </div>
</body>
</html>
