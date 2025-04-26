<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/fragments/head.jsp" />
    <title>Forgot Password - Patika L & F System</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap/bootstrap.min.css' />">
</head>

<body class="hold-transition login-page" style="background-color: rgb(186, 193, 199);">
<div class="login-logo">
    <a href="<c:url value='/login.jsp' />"><b>Patika</b> L & F System</a>
</div>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
            <div class="card border border-light-subtle rounded-3 shadow-sm">
                <div class="card-body p-3 p-md-4 p-xl-5">

                    <!-- Add Logo or Banner -->
                    <div class="text-center mb-4">
                        <img src="<c:url value='/resources/images/favicon.jpg' />" alt="Logo" class="img-fluid" style="max-width: 400px; height: auto;">
                    </div>

                    <!-- Display error message if any -->
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger" role="alert">
                            Error occurred. Please try again.
                        </div>
                    </c:if>

                    <!-- Display success message if password reset email is sent -->
                    <c:if test="${param.success != null}">
                        <div class="alert alert-success" role="alert">
                            A password reset link has been sent to your email.
                        </div>
                    </c:if>

                    <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Forgot your password?</h2>

                    <form action="<c:url value='/auth' />" method="post">
                        <input type="hidden" name="action" value="forgot-password" />

                        <div class="row gy-2 overflow-hidden">
                            <!-- Email -->
                            <div class="col-12">
                                <div class="form-floating mb-3">
                                    <input type="email" class="form-control" name="email" id="email" placeholder="Email" required>
                                    <label for="email" class="form-label">Enter your email address</label>
                                </div>
                            </div>

                            <!-- Submit Button -->
                            <div class="col-12">
                                <div class="d-grid my-3">
                                    <button class="btn btn-primary btn-lg" type="submit">Send Password Reset Link</button>
                                </div>
                            </div>

                            <!-- No account? Link -->
                            <div class="col-12">
                                <p class="m-0 text-secondary text-center">
                                    Remembered your password?
                                    <a href="<c:url value='/login' />" class="link-primary text-decoration-none">Login</a>
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
