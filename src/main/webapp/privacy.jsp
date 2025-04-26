<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/fragments/head.jsp" />
    <title>Privacy Policy - Patika L & F System</title>
</head>
<body>

<jsp:include page="/fragments/header.jsp" />

<header class="terms-header">
    <div class="container">
        <h1 class="display-4">Privacy Policy</h1>
        <p class="lead">Your privacy is important to us. This page outlines how we collect, use, and protect your data.</p>
    </div>
</header>

<section class="terms-content py-4">
    <div class="container">

        <h2>Information We Collect</h2>
        <p>We may collect personal information such as your name, email address, contact details, and other relevant information when you register or use our services.</p>

        <h2>How We Use Your Information</h2>
        <p>The information we collect is used to provide and improve our services, personalize your experience, respond to customer service requests, and send periodic communications.</p>

        <h2>Information Sharing</h2>
        <p>We do not sell or trade your personal information to third parties. We may share data with trusted partners to help perform services like hosting and customer support, under strict confidentiality agreements.</p>

        <h2>Security of Your Data</h2>
        <p>We implement a variety of security measures to maintain the safety of your personal information, including encrypted storage and restricted access protocols.</p>

        <h2>Your Consent</h2>
        <p>By using our site, you consent to our privacy policy.</p>

        <h2>Changes to this Policy</h2>
        <p>We reserve the right to modify this privacy policy at any time. Changes will take effect immediately upon posting on the website.</p>

        <div class="form-group mt-4">
            <a href="${pageContext.request.contextPath}/" class="btn btn-secondary btn-lg">Back to Homepage</a>
        </div>

    </div>
</section>

<jsp:include page="/fragments/landing-footer.jsp" />

</body>
</html>
