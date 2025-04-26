<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500 - Internal Server Error</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #fff3f3;
            margin: 0;
            padding: 0;
        }
        .container {
            text-align: center;
            padding: 60px 20px;
        }
        .error-code {
            font-size: 100px;
            color: #e63946;
            margin: 0;
        }
        .message {
            font-size: 26px;
            color: #333;
        }
        .description {
            margin-top: 10px;
            color: #777;
        }
        a.home-link {
            display: inline-block;
            margin-top: 30px;
            padding: 12px 24px;
            background-color: #343a40;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        a.home-link:hover {
            background-color: #212529;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="error-code">500</h1>
    <div class="message">Something went wrong on our end</div>
    <div class="description">
        We're experiencing an issue processing your request.<br>
        If you're trying to report or search for a lost item, please try again later or go back to the homepage.
    </div>
    <a class="home-link" href="${pageContext.request.contextPath}/index.jsp">Return to Homepage</a>
</div>
</body>
</html>
