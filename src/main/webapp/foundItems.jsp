<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/fragments/head.jsp" %>
    <title>Found Items</title></head>

<body>
<%@ include file="/fragments/header.jsp" %>

<div class="container">
    <!-- Search Form -->
    <form action="${pageContext.request.contextPath}/found-items/search" method="get" class="d-flex mb-4">
        <label>
            <input type="text" name="query" class="form-control" placeholder="Search Found Items"
                   value="${query}" />
        </label>
        <button type="submit" class="btn btn-primary ms-2">Search</button>
    </form>

    <h1 class="mt-4">Found Items</h1>
    <div>
        <a href="${pageContext.request.contextPath}/found-items?action=post-found-item" class="btn btn-primary m-2">Add Found Item</a>
    </div>

    <table class="table table-responsive table-striped">
        <thead class="table-info">
        <tr>
            <th>Item Name</th>
            <th>Description</th>
            <th>Category</th>
            <th>Location Found</th>
            <th>Finder Contact</th>
            <th>Date Found</th>
            <th>Status</th>
            <th>Actions</th>
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
                <td>
                    <c:choose>
                        <c:when test="${item.status.name() == 'UNCLAIMED'}">
                            <span class="badge bg-info bg-lg rounded-pill">Unclaimed</span>
                        </c:when>
                        <c:when test="${item.status.name() == 'PENDING'}">
                            <span class="badge bg-warning rounded-pill">Pending</span>
                        </c:when>
                        <c:when test="${item.status.name() == 'CLAIMED'}">
                            <span class="badge bg-success rounded-pill">Claimed</span>
                        </c:when>
                        <c:when test="${item.status.name() == 'DISPUTED'}">
                            <span class="badge bg-danger rounded-pill">Disputed</span>
                        </c:when>
                    </c:choose>
                </td>


                <td>
                    <div class="btn-group" role="group">
                        <div class="d-flex">
                            <!-- View button is visible to all users -->
                            <a href="${pageContext.request.contextPath}/found-items?action=view&id=${item.id}" class="btn btn-info">View</a>

                            <!-- Edit and Delete buttons only visible for admin -->
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a href="${pageContext.request.contextPath}/found-items?action=edit&id=${item.id}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="${pageContext.request.contextPath}/found-items?action=delete&id=${item.id}" class="btn btn-danger btn-sm"
                                   onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>

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
    <a href="${pageContext.request.contextPath}/" class="btn btn-secondary btn-lg w-80 m-2">Back</a>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>
</body>
</html>
