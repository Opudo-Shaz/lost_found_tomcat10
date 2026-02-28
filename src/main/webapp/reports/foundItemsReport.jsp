<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Found Items Report</title>

</head>

<body>
<%@ include file="../fragments/header.jsp" %>

<div class="container">
    <h1 class="mt-4">📄 Found Items Report</h1>
    <%
        java.time.LocalDate today = java.time.LocalDate.now();
    %>


    <!-- Filter Form -->
    <form method="GET" action="${pageContext.request.contextPath}/report">
        <input type="hidden" name="type" value="found">
        <div class="row">
            <!-- Category Filter -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="category">Category:</label>
                    <label>
                        <input type="text" class="form-control" name="category" value="${category}">
                    </label>
                </div>
            </div>

            <!-- From Date Filter -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="fromDate">From Date:</label>
                    <label>
                        <input type="date" class="form-control" name="fromDate" value="${fromDate}" max="<%= today %>" >
                    </label>
                </div>
            </div>

            <!-- To Date Filter -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="toDate">To Date:</label>
                    <label>
                        <input type="date" class="form-control" name="toDate" value="${toDate}" max="<%= today %>" >
                    </label>
                </div>
            </div>

            <!-- Location Filter -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="location">Location Found:</label>
                    <label>
                        <input type="text" class="form-control" name="location" value="${location}">
                    </label>
                </div>
            </div>
        </div>

        <div class="col-md-2">
            <label for="status" class="form-label">Status</label>
            <select name="status" id="status" class="form-select">
                <option value="">-- All --</option>
                <option value="unclaimed" ${status == 'unclaimed' ? 'selected' : ''}>Unclaimed</option>
                <option value="claimed" ${status == 'claimed' ? 'selected' : ''}>Claimed</option>
                <option value="pending" ${status == 'pending' ? 'selected' : ''}>Pending</option>
                <option value="disputed" ${status == 'disputed' ? 'selected' : ''}>Disputed</option>

            </select>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">View Report</button>
        </div>
    </form>

    <c:if test="${empty items}">
        <div class="alert alert-warning mt-3">No found items to display.</div>
    </c:if>

    <c:if test="${not empty items}">
        <table class="table table-bordered table-striped mt-3">
            <thead class="table-secondary">
            <tr>
                <th>Item Name</th>
                <th>Description</th>
                <th>Category</th>
                <th>Location Found</th>
                <th>Finder Contact</th>
                <th>Date Found</th>
                <th>Status</th>
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
                    <td>${item.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="mt-3">
            <a class="btn btn-success"
               href="${pageContext.request.contextPath}/report?type=found&amp;format=pdf" target="_blank">⬇ Download PDF</a>

            <button class="btn btn-outline-primary" onclick="window.print()">🖨 Print Report</button>

            <a href="${pageContext.request.contextPath}/found-items" class="btn btn-secondary">← Back</a>
        </div>
    </c:if>
</div>

<%@ include file="../fragments/landing-footer.jsp" %>
</body>
</html>
