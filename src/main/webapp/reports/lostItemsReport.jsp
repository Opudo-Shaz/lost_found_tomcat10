<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Lost Items Report</title>

</head>

<body>
<%@ include file="../fragments/header.jsp" %>

<div class="container">
    <h1 class="mt-4">📄 Lost Items Report</h1>
    <%
        java.time.LocalDate today = java.time.LocalDate.now();
    %>


    <!-- Filter Form -->
    <form method="GET" action="${pageContext.request.contextPath}/report">
        <input type="hidden" name="type" value="lost">
        <div class="row">
            <!-- Category Filter -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="category">Category:</label>
                    <input type="text" class="form-control" name="category" value="${category}">
                </div>
            </div>

            <!-- From Date Filter -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="fromDate">From Date:</label>
                    <input type="date" class="form-control" name="fromDate" value="${fromDate}" max="<%= today %>" >
                </div>
            </div>

            <!-- To Date Filter -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="toDate">To Date:</label>
                    <input type="date" class="form-control" name="toDate" value="${toDate}" max="<%= today %>" >
                </div>
            </div>

            <!-- Location Filter -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="location">Location Lost:</label>
                    <input type="text" class="form-control" name="location" value="${location}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">View Report</button>
        </div>
    </form>

    <c:if test="${empty items}">
        <div class="alert alert-warning mt-3">No lost items to display.</div>
    </c:if>

    <c:if test="${not empty items}">
        <table class="table table-bordered table-striped mt-3">
            <thead class="table-secondary">
            <tr>
                <th>Item Name</th>
                <th>Description</th>
                <th>Category</th>
                <th>Location Lost</th>
                <th>Owner Contact</th>
                <th>Date Lost</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.description}</td>
                    <td>${item.category}</td>
                    <td>${item.locationLost}</td>
                    <td>${item.ownerContact}</td>
                    <td>${item.dateLost}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="mt-3">
            <a class="btn btn-success"
               href="${pageContext.request.contextPath}/report?type=lost&amp;format=pdf"
               target="_blank">⬇ Download PDF</a>

            <button class="btn btn-outline-primary" onclick="window.print()">🖨 Print Report</button>

            <a href="${pageContext.request.contextPath}/lost-items" class="btn btn-secondary">← Back</a>
        </div>
    </c:if>
</div>

<%@ include file="../fragments/landing-footer.jsp" %>
</body>
</html>
