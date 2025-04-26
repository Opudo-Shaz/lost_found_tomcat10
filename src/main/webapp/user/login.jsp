<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/fragments/head.jsp" />
    <title>Login - Patika L & F System</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap/bootstrap.min.css' />">
</head>

<body class="hold-transition login-page" style="background-color: rgb(186, 193, 199);">
<div class="login-logo">
    <a href="<c:url value='/login' />"><b>Patika</b> L & F System</a>
</div>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
            <div class="card border border-light-subtle rounded-3 shadow-sm">
                <div class="card-body p-3 p-md-4 p-xl-5">

                    <!-- Logo -->
                    <div class="text-center mb-4">
                        <img src="<c:url value='/resources/images/favicon.jpg' />" alt="Logo" class="img-fluid" style="max-width: 400px; height: auto;">
                    </div>

                    <!-- Error Message -->
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">
                                ${errorMessage}
                        </div>
                    </c:if>

                    <!-- Title -->
                    <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Login to your account</h2>

                    <!-- Login Form -->
                    <form action="<c:url value='/auth' />" method="post">
                        <input type="hidden" name="action" value="login" />

                        <div class="row gy-2 overflow-hidden">

                            <!-- Username -->
                            <div class="col-12">
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" name="username" id="username" placeholder="Username" required>
                                    <label for="username">Username</label>
                                </div>
                            </div>

                            <!-- Password -->
                            <div class="col-12">
                                <div class="form-floating mb-3">
                                    <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
                                    <label for="password">Password</label>
                                </div>
                            </div>

                            <!-- Remember Me -->
                            <div class="col-12">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" name="rememberMe" id="rememberMe">
                                    <label class="form-check-label text-secondary" for="rememberMe">Remember me</label>
                                </div>
                            </div>

                            <!-- Submit Button -->
                            <div class="col-12">
                                <div class="d-grid my-3">
                                    <button class="btn btn-primary btn-lg" type="submit">Login</button>
                                </div>
                            </div>

                            <!-- Forgot Password (Optional Placeholder) -->
                            <div class="col-12">
                                <p class="m-0 text-secondary text-center">
                                    <a href="#" class="link-primary text-decoration-none">Forgot password?</a>
                                </p>
                            </div>

                            <!-- Signup Link -->
                            <div class="col-12">
                                <p class="m-0 text-secondary text-center">
                                    Don't have an account yet?
                                    <a href="<c:url value='/signup' />" class="link-primary text-decoration-none">Sign up</a>
                                </p>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value='/resources/js/bootstrap.bundle.js' />"></script>
</body>
</html>
