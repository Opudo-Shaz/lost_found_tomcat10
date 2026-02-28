<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>405 - Method Not Allowed</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f8f9fa;
      margin: 0;
      padding: 0;
    }
    .container {
      text-align: center;
      padding: 60px 20px;
    }
    .error-code {
      font-size: 100px;
      color: #dc3545;
      margin: 0;
    }
    .message {
      font-size: 24px;
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
  <h1 class="error-code">405</h1>
  <div class="message">Oops! Method Not Allowed</div>
  <div class="description">The request method is not supported for this route.<br>
    If you're trying to report or search for a lost item, please return to the homepage.</div>
  <a class="home-link" href="${pageContext.request.contextPath}/index.jsp">Go Back to Homepage</a>
</div>
</body>
</html>
