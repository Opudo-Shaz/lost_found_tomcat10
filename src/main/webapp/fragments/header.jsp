<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header id="header" class="header d-flex align-items-center sticky-top bg-light shadow-sm">
    <div class="container-fluid position-relative d-flex align-items-center justify-content-between">
        <a href="${pageContext.request.contextPath}/" class="logo d-flex align-items-center me-auto me-xl-0">
            <img src="${pageContext.request.contextPath}/resources/images/logo.jpg" alt="Logo" class="logo-img" width="1120" height="1120"/>
        </a>

        <nav id="navmenu" class="navmenu">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/" class="active" aria-current="page">Home</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/lost-items">Lost Items</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/found-items">Found Items</a>
                </li>
                <li>
                    <a href="#about">About</a>
                </li>
                <li>
                    <a href="#contact">Contact</a>
                </li>

                <!-- Conditional login/logout -->
                <c:choose>
                    <c:when test="${not empty sessionScope.username}">
                        <li>
                            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${pageContext.request.contextPath}/login">Login</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/signup">Sign Up</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
        </nav>
    </div>

    <!-- User Profile Section -->
    <c:if test="${not empty sessionScope.username}">
        <div class="user-profile d-flex align-items-center">
            <!-- Optional: Replace with real user avatar path if stored -->
            <img src="${pageContext.request.contextPath}/resources/images/avatar.png"
                 alt=""
                 class="user-avatar rounded-circle" width="40" height="40"/>

            <span class="ms-2">
                    ${sessionScope.username}
            </span>

            <c:if test="${not empty sessionScope.role}">
                <span class="ms-1">
                    (${sessionScope.role})
                </span>
            </c:if>
        </div>
    </c:if>

</header>
