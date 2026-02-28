<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Lost Items</title>
</head>

<body>
<%@ include file="/fragments/header.jsp" %>

<div class="container mt-4">
    <!-- Search Form -->
    <form action="${pageContext.request.contextPath}/lost-items/search" method="get" class="d-flex mb-4">
        <label>
            <input type="text" name="query" class="form-control" placeholder="Search Lost Items" value="${query}" />
        </label>
        <button type="submit" class="btn btn-primary ms-2">Search</button>
    </form>

    <h1>Lost Items</h1>

    <a href="${pageContext.request.contextPath}/report?type=lost" class="btn btn-info mb-3">
        Generate Report
    </a>

    <!-- Add Lost Item button -->
    <div>
        <a href="${pageContext.request.contextPath}/lost-items?action=add" class="btn btn-primary mb-4">Add Lost Item</a>
    </div>

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
                    <div class="btn-group-vertical" role="group">
                        <!-- View -->
                        <a href="${pageContext.request.contextPath}/lost-items?action=view&id=${item.id}" class="btn btn-info btn-sm mb-1">View</a>

                        <!-- Admin-only -->
                        <c:if test="${sessionScope.role == 'ADMIN'}">
                            <a href="${pageContext.request.contextPath}/lost-items?action=edit&id=${item.id}" class="btn btn-warning btn-sm mb-1">Edit</a>
                            <a href="${pageContext.request.contextPath}/lost-items?action=delete&id=${item.id}" class="btn btn-danger btn-sm mb-1"
                               onclick="return confirm('Are you sure you want to delete this item?')">Delete</a>
                        </c:if>

                        <!-- Confirmation Buttons -->
                        <c:if test="${empty item.markedAsFound}">
                            <c:choose>
                                <c:when test="${sessionScope.username == item.ownerUsername}">
                                    <!-- Owner confirming -->
                                    <form action="${pageContext.request.contextPath}/confirmFound" method="post" class="mb-1">
                                        <input type="hidden" name="item_id" value="${item.id}" />
                                        <input type="hidden" name="role" value="owner" />
                                        <button type="submit" class="btn btn-success btn-sm">I Found My Item</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <!-- Finder confirming -->
                                    <form action="${pageContext.request.contextPath}/confirmFound" method="post" class="mb-1">
                                        <input type="hidden" name="item_id" value="${item.id}" />
                                        <input type="hidden" name="role" value="finder" />
                                        <button type="submit" class="btn btn-outline-success btn-sm">I Found This Item</button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </c:if>

                        <!-- Already found -->
                        <c:if test="${item.markedAsFound}">
                            <span class="badge bg-success">Marked as Found</span>
                        </c:if>

                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="form-group mt-3">
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary btn-lg m-2">Back</a>
    </div>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>
</body>
</html>
