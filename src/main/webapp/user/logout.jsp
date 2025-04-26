<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Logout - Lost and Found System</title>

    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/css/adminlite/adminlte.min.css' />">
</head>
<body>
<div class="wrapper">
    <nav><!-- Navbar content --></nav>
    <aside><!-- Sidebar content --></aside>

    <div class="content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
                <h1 class="m-0">Logout</h1>
            </div>
        </div>
        <section class="content">
            <div class="container-fluid">
                <h3>You have been logged out successfully.</h3>
                <a href="<c:url value='/login' />" class="btn btn-primary">Login Again</a>
            </div>
        </section>
    </div>
    <footer><!-- Footer content --></footer>
</div>

<script src="<c:url value='/js/bootstrap.bundle.js' />"></script>
<script src="<c:url value='/css/adminlite/adminlte.min.js' />"></script>

</body>
</html>
