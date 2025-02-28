<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
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
        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 8px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #218838;
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
        <h2>➕ Add Student</h2>
        <form action="StudentServlet" method="post">
            <label>Name:</label>
            <input type="text" name="name" required>

            <label>Email:</label>
            <input type="email" name="email" required>

            <label>Department:</label>
            <input type="text" name="department" required>

            <input type="submit" value="✅ Add Student">
        </form>
        <a href="index.jsp" class="back-link">🏠 Back to Home</a>
    </div>
</body>
</html>
