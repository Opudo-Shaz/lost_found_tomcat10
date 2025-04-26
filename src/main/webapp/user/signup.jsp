<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/fragments/head.jsp" />
    <title>Sign Up - Patika L & F System</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap/bootstrap.min.css' />">
</head>

<body class="hold-transition login-page" style="background-color: rgb(186, 193, 199);">
<div class="login-logo">
    <a href="<c:url value='/signup' />"><b>Patika</b> L & F System</a>
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

                    <!-- Success Message -->
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success" role="alert">
                                ${successMessage}
                        </div>
                    </c:if>

                    <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Create a new account</h2>

                    <!-- Sign Up Form -->
                    <form action="<c:url value='/auth' />" method="post">
                        <input type="hidden" name="action" value="signup" />

                        <div class="row gy-3 overflow-hidden">

                            <!-- First Name -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="first_name" id="first_name" placeholder="First Name" required>
                                    <label for="first_name">First Name</label>
                                </div>
                            </div>

                            <!-- Last Name -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="last_name" id="last_name" placeholder="Last Name" required>
                                    <label for="last_name">Last Name</label>
                                </div>
                            </div>

                            <!-- Username -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="username" id="username" placeholder="Username" required>
                                    <label for="username">Username</label>
                                </div>
                            </div>

                            <!-- Email -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="email" class="form-control" name="email" id="email" placeholder="Email" required>
                                    <label for="email">Email</label>
                                </div>
                            </div>

                            <!-- Password -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
                                    <label for="password">Password</label>
                                </div>
                            </div>

                            <!-- Contact -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="contact" id="contact" placeholder="Contact Number" required>
                                    <label for="contact">Contact Number</label>
                                </div>
                            </div>

                            <!-- Address -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="address" id="address" placeholder="Address" required>
                                    <label for="address">Address</label>
                                </div>
                            </div>

                            <!-- Submit Button -->
                            <div class="col-12">
                                <div class="d-grid my-3">
                                    <button class="btn btn-primary btn-lg" type="submit">Sign Up</button>
                                </div>
                            </div>

                            <!-- Login Link -->
                            <div class="col-12">
                                <p class="m-0 text-secondary text-center">
                                    Already have an account?
                                    <a href="<c:url value='/login' />" class="link-primary text-decoration-none">Login here</a>
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
