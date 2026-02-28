<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Thank You</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .thank-you-box {
            margin-top: 100px;
            padding: 40px;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
            text-align: center;
        }
    </style>
</head>
<body>

<%@ include file="/fragments/header.jsp" %>

<div class="container d-flex justify-content-center">
    <div class="thank-you-box col-md-8">
        <h1 class="text-success">🎉 Thank You!</h1>
        <p class="lead mt-3">${message != null ? message : "We appreciate your confirmation. The status has been updated successfully."}</p>
        <p class="text-muted">You're always welcome to use our system again.</p>

        <a href="${pageContext.request.contextPath}/" class="btn btn-primary mt-4">Return to Homepage</a>
        <a href="${pageContext.request.contextPath}/found-items" class="btn btn-outline-secondary mt-2">View More Found Items</a>
    </div>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>
</body>
</html>
