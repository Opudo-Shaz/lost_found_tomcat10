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
    <h1 class="mt-4">Post Found Item</h1>
    <%
        java.time.LocalDate today = java.time.LocalDate.now();
    %>

    <!-- JSP Form -->
    <form action="${pageContext.request.contextPath}/found-items" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="add" />
        <!-- Item Name -->
        <div class="form-group">
            <label for="name">Item Name</label>
            <input type="text" id="name" name="name" class="form-control" value="${foundItem.name}" placeholder="Enter Item Name" />
        </div>

        <!-- Description -->
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" class="form-control" placeholder="Enter Description">${foundItem.description}</textarea>
        </div>

        <!-- Category -->
        <div class="form-group">
            <label for="category">Category</label>
            <select id="category" name="category" class="form-control">
                <c:forEach var="category" items="${categories}">
                    <option value="${category}" <c:if test="${foundItem. category == category}">selected</c:if>>${category}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Location Found -->
        <div class="form-group">
            <label for="locationFound">Location Found</label>
            <input type="text" id="locationFound" name="locationFound" class="form-control" value="${foundItem.locationFound}" placeholder="Enter Location" />
        </div>

        <!-- Finder Contact -->
        <div class="form-group">
            <label for="finderContact">Finder's Contact</label>
            <input type="text" id="finderContact" name="finderContact" class="form-control" value="${foundItem.finderContact}" placeholder="Enter Finder's Contact" />
        </div>

        <!-- Finder Email -->
        <div class="form-group">
            <label for="finderEmail">Finder's Email Address</label>
            <input type="text" id="finderEmail" name="finderEmail" class="form-control" value="${foundItem.finderEmail}" placeholder="Enter Your Email Address" />
        </div>

        <!-- Date Found -->
        <div class="form-group">
            <label for="dateFound">Date Found</label>
            <input type="date" id="dateFound" name="dateFound" class="form-control"
                   value="${foundItem.dateFound}" max="<%= today %>" />
        </div>

        <!-- Image Upload -->
        <div class="form-group">
            <label for="image">Upload Image</label>
            <input type="file" id="image" name="image" class="form-control-file" accept="image/*" />
        </div>

        <!-- Submit -->
        <button type="submit" class="btn btn-primary">Post Item</button>
    </form>

    <!-- Back Button -->
    <a href="${pageContext.request.contextPath}/found-items" class="btn btn-secondary mt-3">Back to Found Items</a>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>

</body>
</html>
