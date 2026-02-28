<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/fragments/head.jsp" %>
    <title>view</title></head>
<body>

<%@ include file="/fragments/header.jsp" %>

<div class="container">
    <h1 class="text-center mb-4">Found Item Details</h1>

    <!-- Check if item exists -->
    <c:if test="${not empty foundItem}">
        <div class="item-details">
            <h3>${foundItem.name}</h3>
            <p><strong>Description:</strong> ${foundItem.description}</p>
            <p><strong>Category:</strong> ${foundItem.category}</p>
            <p><strong>Location Found:</strong> ${foundItem.locationFound}</p>
            <p><strong>Finder's Contact:</strong> ${foundItem.finderContact}</p>
            <p><strong>Date Found:</strong> ${foundItem.foundDate}</p>
            <p><strong>Status:</strong>
                <c:choose>
                    <c:when test="${foundItem.status.name() == 'UNCLAIMED'}">
                        <span class="badge bg-info bg-lg rounded-pill">${foundItem.status}</span>
                    </c:when>
                    <c:when test="${foundItem.status.name() == 'PENDING'}">
                        <span class="badge bg-warning rounded-pill">${foundItem.status}</span>
                    </c:when>
                    <c:when test="${foundItem.status.name() == 'CLAIMED'}">
                        <span class="badge bg-success rounded-pill">${foundItem.status}</span>
                    </c:when>
                    <c:when test="${foundItem.status.name() == 'DISPUTED'}">
                        <span class="badge bg-danger rounded-pill">${foundItem.status}</span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge bg-secondary rounded-pill">${foundItem.status}</span>
                    </c:otherwise>
                </c:choose>
            </p>

            <!-- Show 'Claim Item' button if item is UNCLAIMED -->
            <c:if test="${foundItem.status.name() == 'UNCLAIMED'}">
                <a href="${pageContext.request.contextPath}/claimFoundItem.jsp?id=${foundItem.id}" class="btn btn-primary btn-lg btn-claim">Claim Item</a>
            </c:if>

        </div>
    </c:if>

    <!-- If item is null -->
    <c:if test="${empty foundItem}">
        <div class="alert alert-warning">
            <p>Item not found. Please check the ID and try again.</p>
        </div>
    </c:if>
</div>

<!-- Back to the list -->
<div class="container mt-4">
    <a href="${pageContext.request.contextPath}/found-items" class="btn btn-secondary btn-lg btn-back">Back to List</a>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>
</body>
</html>
