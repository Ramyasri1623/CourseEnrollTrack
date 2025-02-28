<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Course</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            text-align: center;
            margin-top: 50px;
        }
        .container {
            background: white;
            width: 320px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
            text-align: left;
        }
        h2 {
            margin-bottom: 15px;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 8px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
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
        <h2>📚 Add Course</h2>
        <form action="CourseServlet" method="post">
            <input type="hidden" name="action" value="add"> <!-- ✅ Ensures action is sent -->
            
            <label>Course Name:</label>
            <input type="text" name="courseName" required>

            <label>Instructor:</label>
            <input type="text" name="instructor" required>

            <input type="submit" value="✅ Add Course">
        </form>
        <a href="index.jsp" class="back-link">🏠 Back to Home</a>
    </div>
</body>
</html>
