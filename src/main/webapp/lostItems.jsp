<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/fragments/head.jsp" %>
</head>

<body>
<%@ include file="/fragments/header.jsp" %>

<div class="container">
    <!-- Search Form -->
    <form action="${pageContext.request.contextPath}/lost-items/search" method="get" class="d-flex mb-4">
        <label>
            <input type="text" name="query" class="form-control" placeholder="Search Lost Items" value="${query}" />
        </label>
        <button type="submit" class="btn btn-primary ms-2">Search</button>
    </form>

    <h1 class="mt-4">Lost Items</h1>

    <!-- Add Lost Item button (shown to everyone for now) -->
    <a href="${pageContext.request.contextPath}/lost-items?action=add" class="btn btn-primary m-2">Add Lost Item</a>

    <table class="table table-responsive table-striped">
        <thead class="table-info">
        <tr>
            <th>Item Name</th>
            <th>Description</th>
            <th>Category</th>
            <th>Location Lost</th>
            <th>Owner Contact</th>
            <th>Date Lost</th>
            <th>Actions</th>
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

                <td>
                    <div class="btn-group" role="group">
                        <div class="d-flex">
                            <!-- View button -->
                            <a href="${pageContext.request.contextPath}/lost-items?action=view&id=${item.id}" class="btn btn-info btn-sm me-3">View</a>

                            <!-- Admin-only actions -->
                            <sec:authorize access="hasRole('ADMIN')">
                                <!-- Edit button -->
                                <a href="${pageContext.request.contextPath}/lost-items?action=edit&id=${item.id}" class="btn btn-warning btn-sm me-3">Edit</a>

                                <!-- Delete button -->
                                <a href="${pageContext.request.contextPath}/lost-items?action=delete&id=${item.id}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('Are you sure you want to delete this item?')">Delete</a>
                            </sec:authorize>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="form-group mt-3">
    <a href="${pageContext.request.contextPath}/" class="btn btn-secondary btn-lg size-100 m-2">Back</a>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>
</body>
</html>
