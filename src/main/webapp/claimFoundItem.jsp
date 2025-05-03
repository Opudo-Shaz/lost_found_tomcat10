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
    <h1 class="mt-4">Claim Found Item</h1>

    <!-- Display Success or Error Messages -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">
                ${success}
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">
                ${error}
        </div>
    </c:if>

    <!-- Form to claim the found item -->
    <form action="${pageContext.request.contextPath}/found-items/save-claim/${param.id}" method="post" enctype="multipart/form-data">

        <!-- Hidden field for itemId -->
        <input type="hidden" name="itemId" value="${claim.itemId}" />

        <!-- Claimer Email -->
        <div class="mb-3">
            <label for="claimerEmail" class="form-label">Your Email</label>
            <input type="email" class="form-control" id="claimerEmail" name="claimerEmail" required />
        </div>

        <!-- Claimer Contact -->
        <div class="mb-3">
            <label for="claimerContact" class="form-label">Your Phone Number</label>
            <input type="text" class="form-control" id="claimerContact" name="claimerContact" required />
        </div>

        <!-- Ownership Note -->
        <div class="mb-3">
            <label for="claimerNote" class="form-label">Ownership Note</label>
            <textarea class="form-control" id="claimerNote" name="claimerNote" rows="4" placeholder="Provide a brief note explaining ownership." required>${claim.claimerNote}</textarea>
        </div>

        <!-- Upload Image -->
        <div class="mb-3">
            <label for="image" class="form-label">Upload Image of the Lost Item</label>
            <input type="file" class="form-control" id="image" name="image" accept="image/*" required />
        </div>

        <!-- Upload Receipt -->
        <div class="mb-3">
            <label for="receipt" class="form-label">Upload Receipt (if available)</label>
            <input type="file" class="form-control" id="receipt" name="receipt" accept="application/pdf,image/*" />
        </div>

        <!-- Submit Button -->
        <div class="mb-3">
            <button type="submit" class="btn btn-success">Submit Claim Request</button>
        </div>

        <!-- Back Link -->
        <div>
            <a href="${pageContext.request.contextPath}/found-items" class="btn btn-secondary">Back to Found Items</a>
        </div>
    </form>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>

</body>
</html>
