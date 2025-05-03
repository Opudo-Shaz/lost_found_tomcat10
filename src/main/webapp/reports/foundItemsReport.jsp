<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/fragments/head.jsp" %>
    <title>Found Items Report</title>
</head>

<body>
<%@ include file="/fragments/header.jsp" %>

<div class="container">
    <h1 class="mt-4">📄 Found Items Report</h1>

    <table class="table table-bordered table-striped">
        <thead class="table-secondary">
        <tr>
            <th>Item Name</th>
            <th>Description</th>
            <th>Category</th>
            <th>Location Found</th>
            <th>Finder Contact</th>
            <th>Date Found</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.category}</td>
                <td>${item.locationFound}</td>
                <td>${item.finderContact}</td>
                <td>${item.foundDate}</td>
                <td>${item.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="mt-3">
        <button class="btn btn-outline-primary" onclick="window.print()">🖨 Print Report</button>
        <a href="${pageContext.request.contextPath}/found-items" class="btn btn-secondary">← Back</a>
    </div>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>
</body>
</html>
