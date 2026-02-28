<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 - Page Not Found</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f4f8;
            margin: 0;
            padding: 0;
        }
        .container {
            text-align: center;
            padding: 60px 20px;
        }
        .error-code {
            font-size: 100px;
            color: #007bff;
            margin: 0;
        }
        .message {
            font-size: 26px;
            color: #333;
        }
        .description {
            margin-top: 10px;
            color: #555;
        }
        a.home-link {
            display: inline-block;
            margin-top: 30px;
            padding: 12px 24px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        a.home-link:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="error-code">404</h1>
    <div class="message">Page Not Found</div>
    <div class="description">
        The page you are looking for doesn't exist or may have been moved.<br>
        Try heading back to the homepage or using the search feature.
    </div>
    <a class="home-link" href="${pageContext.request.contextPath}/index.jsp">Back to Homepage</a>
</div>
</body>
</html>
