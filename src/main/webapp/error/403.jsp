<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>403 - Forbidden</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #fff6e0;
            margin: 0;
            padding: 0;
        }
        .container {
            text-align: center;
            padding: 60px 20px;
        }
        .error-code {
            font-size: 100px;
            color: #ffc107;
            margin: 0;
        }
        .message {
            font-size: 26px;
            color: #333;
        }
        .description {
            margin-top: 10px;
            color: #666;
        }
        a.home-link {
            display: inline-block;
            margin-top: 30px;
            padding: 12px 24px;
            background-color: #ffc107;
            color: #212529;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        a.home-link:hover {
            background-color: #e0a800;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="error-code">403</h1>
    <div class="message">Access Denied</div>
    <div class="description">
        You don’t have permission to view this page.<br>
        If you believe this is an error, please contact the administrator.
    </div>
    <a class="home-link" href="${pageContext.request.contextPath}/index.jsp">Go to Homepage</a>
</div>
</body>
</html>
