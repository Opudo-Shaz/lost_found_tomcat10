<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/fragments/head.jsp" %>
</head>
<body>

<%@ include file="/fragments/header.jsp" %>

<div class="container edit-item-form mt-5">
    <h1 class="text-center mb-4">Edit Found Item</h1>

    <form action="${pageContext.request.contextPath}/found-items" method="post" enctype="multipart/form-data" class="form-horizontal">

        <!-- Hidden fields -->
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="id" value="${foundItem.id}" />

        <!-- Item Name -->
        <div class="form-group">
            <label for="name" class="col-form-label">Item Name:</label>
            <input type="text" id="name" name="name" class="form-control" value="${foundItem.name}" required />
        </div>

        <!-- Description -->
        <div class="form-group">
            <label for="description" class="col-form-label">Description:</label>
            <textarea id="description" name="description" class="form-control">${foundItem.description}</textarea>
        </div>

        <!-- Category -->
        <div class="form-group">
            <label for="category" class="col-form-label">Category:</label>
            <input type="text" id="category" name="category" class="form-control" value="${foundItem.category}" />
        </div>

        <!-- Location Found -->
        <div class="form-group">
            <label for="locationFound" class="col-form-label">Location Found:</label>
            <input type="text" id="locationFound" name="locationFound" class="form-control" value="${foundItem.locationFound}" />
        </div>

        <!-- Finder's Contact -->
        <div class="form-group">
            <label for="finderContact" class="col-form-label">Finder's Contact:</label>
            <input type="text" id="finderContact" name="finderContact" class="form-control" value="${foundItem.finderContact}" />
        </div>

        <!-- Finder Email -->
        <div class="form-group">
            <label for="finderEmail" class="col-form-label">Finder's Email:</label>
            <input type="email" id="finderEmail" name="finderEmail" class="form-control" value="${foundItem.finderEmail}" />
        </div>

        <!-- Date Found -->
        <div class="form-group">
            <label for="dateFound" class="col-form-label">Date Found:</label>
            <input type="date" id="dateFound" name="dateFound" class="form-control" value="${foundItem.foundDate}" />
        </div>

        <!-- Status -->
        <div class="form-group">
            <label for="status" class="col-form-label">Status:</label>
            <select id="status" name="status" class="form-control">
                <option value="UNCLAIMED" <c:if test="${foundItem.status.name() == 'UNCLAIMED'}">selected</c:if>>Unclaimed</option>
                <option value="CLAIMED" <c:if test="${foundItem.status.name() == 'CLAIMED'}">selected</c:if>>Claimed</option>
                <option value="DISPUTED" <c:if test="${foundItem.status.name() == 'DISPUTED'}">selected</c:if>>Disputed</option>
            </select>
        </div>

        <!-- Dispute Reason -->
        <div class="form-group">
            <label for="disputeReason" class="col-form-label">Dispute Reason (if any):</label>
            <textarea id="disputeReason" name="disputeReason" class="form-control">${foundItem.disputeReason}</textarea>
        </div>

        <!-- Current Image Preview -->
        <c:if test="${not empty foundItem.imageUrl}">
            <div class="form-group">
                <label>Current Image:</label><br>
                <img src="${pageContext.request.contextPath}/${foundItem.imageUrl}" alt="Found Item Image" style="max-width: 200px;" />
            </div>
        </c:if>

        <!-- Image Upload -->
        <div class="form-group">
            <label for="image" class="col-form-label">Update Image:</label>
            <input type="file" id="image" name="image" class="form-control" />
        </div>

        <!-- Submit Button -->
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-lg w-100 m-2">Update</button>
        </div>
    </form>

    <!-- Back Button -->
    <div class="form-group mt-3">
        <a href="${pageContext.request.contextPath}/found-items" class="btn btn-secondary btn-lg w-100 m-2">Back to Found Items</a>
    </div>
</div>

<%@ include file="/fragments/footer.jsp" %>

</body>
</html>
