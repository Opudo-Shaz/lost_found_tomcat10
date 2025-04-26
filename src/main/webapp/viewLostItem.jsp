<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/fragments/head.jsp" %>

<body>
<%@ include file="/fragments/header.jsp" %>

<div class="container mt-5">
    <h1 class="text-center">View Lost Item</h1>

    <div class="table-container table-custom">
        <table class="table table-bordered table-striped">
            <tbody>
            <tr>
                <th>Item Name</th>
                <td>${item.name}</td>
            </tr>
            <tr>
                <th>Description</th>
                <td>${item.description}</td>
            </tr>
            <tr>
                <th>Location Lost</th>
                <td>${item.locationLost}</td>
            </tr>
            <tr>
                <th>Owner Contact</th>
                <td>${item.ownerContact}</td>
            </tr>
            <tr>
                <th>Date Lost</th>
                <td>
                    <fmt:formatDate value="${item.dateLost}" pattern="yyyy-MM-dd HH:mm:ss" />
                </td>
            </tr>
            </tbody>
        </table>

        <div class="d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/lost-items" class="btn btn-primary btn-lg btn-custom">Back to Lost Items</a>
        </div>
    </div>
</div>

<%@ include file="/fragments/landing-footer.jsp" %>
</body>

