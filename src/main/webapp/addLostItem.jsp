<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/fragments/head.jsp" %>
    <title>add lost</title></head>

<body>
<%@ include file="/fragments/header.jsp" %>

<div class="container">
    <h1 class="mt-4">Post-Lost Item</h1>
    <%
        java.time.LocalDate today = java.time.LocalDate.now();
    %>

    <!-- JSP Form for Posting Lost Item -->
    <form action="${pageContext.request.contextPath}/lost-items" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="add" />

        <!-- Item Name -->
        <div class="form-group">
            <label for="name">Item Name</label>
            <input type="text" id="name" name="name" class="form-control" value="${item.name}" placeholder="Enter Item Name" />
        </div>

        <!-- Description -->
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" class="form-control" placeholder="Enter Item Description">${item.description}</textarea>
        </div>

        <!-- Category (Dropdown) -->
        <div class="form-group">
            <label for="category">Category</label>
            <select id="category" name="category" class="form-control">
                <c:forEach var="category" items="${categories}">
                    <option value="${category}" <c:if test="${item.category == category}">selected</c:if>>${category}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Location Lost -->
        <div class="form-group">
            <label for="locationLost">Location Lost</label>
            <input type="text" id="locationLost" name="locationLost" class="form-control" value="${item.locationLost}" placeholder="Enter Location" />
        </div>

        <!-- Owner Contact -->
        <div class="form-group">
            <label for="ownerContact">Owner's Contact</label>
            <input type="text" id="ownerContact" name="ownerContact" class="form-control" value="${item.ownerContact}" placeholder="Enter Contact Information" />
        </div>

        <!-- Owner Email -->
        <div class="form-group">
            <label for="ownerEmail">Owner Email Address</label>
            <input type="text" id="ownerEmail" name="ownerEmail" class="form-control" value="${item.ownerEmail}" placeholder="Enter Your Email Address" />
        </div>

        <!-- Date Lost -->
        <div class="form-group">
            <label for="dateLost">Date Lost</label>
            <input type="date" id="dateLost" name="dateLost" class="form-control"
                   value="${item.dateLost}" max="<%= today %>" required />
        </div>


        <!-- Image Upload -->
        <div class="form-group">
            <label for="image">Upload Image</label>
            <input type="file" id="image" name="image" class="form-control-file" accept="image/*" required />
        </div>

        <!-- Submit Button -->
        <button type="submit" class="btn btn-primary m-2">Post Lost Item</button>
    </form>

    <!-- Back Button -->
    <a href="${pageContext.request.contextPath}/lost-items" class="btn btn-secondary mt-3">Back to Lost Items</a>
</div>

<%@ include file="/fragments/footer.jsp" %>

</body>
</html>
