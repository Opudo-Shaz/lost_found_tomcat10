<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Admin - User Management</h2>

    <!-- Add User Form -->
    <form action="addUser" method="post" class="mb-4">
        <div class="form-row">
            <div class="col">
                <label>
                    <input type="text" name="username" class="form-control" placeholder="Username" required>
                </label>
            </div>
            <div class="col">
                <label>
                    <input type="email" name="email" class="form-control" placeholder="Email" required>
                </label>
            </div>
            <div class="col">
                <label>
                    <select name="role" class="form-control" required>
                        <option value="">Select Role</option>
                        <option value="admin">Admin</option>
                        <option value="user">User</option>
                    </select>
                </label>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-success">Add User</button>
            </div>
        </div>
    </form>

    <!-- Users Table -->
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>
                    <!-- Edit Form -->
                    <form action="editUser" method="post" class="d-inline">
                        <input type="hidden" name="id" value="${user.id}">
                        <label>
                            <input type="text" name="username" value="${user.username}" class="form-control d-inline w-auto" required>
                        </label>
                        <label>
                            <input type="email" name="email" value="${user.email}" class="form-control d-inline w-auto" required>
                        </label>
                        <label>
                            <select name="role" class="form-control d-inline w-auto">
                                <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
                                <option value="user" ${user.role == 'user' ? 'selected' : ''}>User</option>
                            </select>
                        </label>
                        <button type="submit" class="btn btn-sm btn-primary">Update</button>
                    </form>

                    <!-- Delete Form -->
                    <form action="deleteUser" method="post" class="d-inline ml-2">
                        <input type="hidden" name="id" value="${user.id}">
                        <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
