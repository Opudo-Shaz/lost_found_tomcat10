<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/fragments/head.jsp" %>
    <title>edit lost</title></head>

<body>
<%@ include file="/fragments/header.jsp" %>

<div class="container edit-item-form mt-5">
    <h1 class="text-center mb-4">Edit Lost Item</h1>

    <form action="${pageContext.request.contextPath}/lost-items" method="post" enctype="multipart/form-data" class="form-horizontal">
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="id" value="${item.id}" />

        <div class="form-group">
            <label class="col-form-label">Item Name:</label>
            <label>
                <input type="text" name="name" class="form-control" value="${item.name}" />
            </label>
        </div>

        <div class="form-group">
            <label class="col-form-label">Description:</label>
            <label>
                <textarea name="description" class="form-control">${item.description}</textarea>
            </label>
        </div>
        <!-- Category -->
        <div class="form-group">
            <label for="category" class="col-form-label">Category:</label>
            <input type="text" id="category" name="category" class="form-control" value="${item.category}" />
        </div>


        <div class="form-group">
            <label class="col-form-label">Location Lost:</label>
            <label>
                <input type="text" name="locationLost" class="form-control" value="${item.locationLost}" />
            </label>
        </div>

        <div class="form-group">
            <label class="col-form-label">Owner Contact:</label>
            <label>
                <input type="text" name="ownerContact" class="form-control" value="${item.ownerContact}" />
            </label>
        </div>

        <div class="form-group">
            <label class="col-form-label">Date Lost:</label>
            <label>
                <input type="date" name="dateLost" class="form-control" value="${lostItem.dateLost}" />
            </label>
        </div>

        <!-- Image Upload Field -->
        <div class="form-group">
            <label class="col-form-label">Upload Image:</label>
            <input type="file" name="image" class="form-control" />
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-lg w-100">Save</button>
        </div>
    </form>

    <div class="form-group mt-3">
        <a href="${pageContext.request.contextPath}/lost-items" class="btn btn-secondary btn-lg w-100 m-2">Back to Lost Items</a>
    </div>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>

</body>
</html>
