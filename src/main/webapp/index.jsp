<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Enrollment System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body { 
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .card {
            width: 350px;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            background: white;
            text-align: center;
        }
        .btn-custom {
            width: 100%;
            margin: 8px 0;
            font-size: 16px;
            border-radius: 8px;
            transition: 0.3s;
        }
        .btn-custom:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
    <div class="card">
        <h3 class="mb-3">📚 Course Enrollment System</h3>
        <a href="add-student.jsp" class="btn btn-primary btn-custom">➕ Add Student</a>
        <a href="add-course.jsp" class="btn btn-success btn-custom">📖 Add Course</a>
        <a href="enroll-student.jsp" class="btn btn-warning btn-custom">🎓 Enroll Student</a>
        <a href="EnrollmentServlet" class="btn btn-info btn-custom">📜 View Enrollments</a>
        <a href="CourseServlet?action=list" class="btn btn-secondary btn-custom">📋 View Courses</a>
    </div>
</body>
</html>
