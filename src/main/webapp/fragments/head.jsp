<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Optional: Set this only if not already set in controller/servlet --%>
<c:set var="isRootUri" value="true" />

<head>
    <!-- Favicon -->
    <link rel="icon" type="image/jpg" href="${pageContext.request.contextPath}/resources/images/logo.jpg">

    <!-- Title -->
    <title>Patika Lost and Found</title>

    <!-- Core Stylesheets -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminlite/adminlte.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">

    <!-- Conditional Stylesheets -->
    <c:if test="${isRootUri}">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-icons/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/aos/aos.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/glightbox/css/glightbox.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/swiper/swiper-bundle.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    </c:if>
</head>
