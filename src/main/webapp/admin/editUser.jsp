<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.lostandfound.model.User" %>

<%
    User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <!-- Bootstrap 5.3 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Edit User</h2>

    <!-- Edit User Form -->
    <form action="${pageContext.request.contextPath}/admin/users" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= user != null ? user.getId() : "" %>">

        <div class="row mb-3">
            <div class="col-md-4">
                <input type="text" name="username" class="form-control" placeholder="Username" value="<%= user != null ? user.getUsername() : "" %>" required>
            </div>
            <div class="col-md-4">
                <input type="email" name="email" class="form-control" placeholder="Email" value="<%= user != null ? user.getEmail() : "" %>" required>
            </div>
            <div class="col-md-4">
                <select name="role" class="form-select" required>
                    <option value="">Select Role</option>
                    <option value="admin" <%= (user != null && "admin".equals(user.getRole())) ? "selected" : "" %>>Admin</option>
                    <option value="user" <%= (user != null && "user".equals(user.getRole())) ? "selected" : "" %>>User</option>
                </select>
            </div>
        </div>

        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-warning">Update User</button>
            <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<!-- Bootstrap 5.3 JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>

</body>
</html>
