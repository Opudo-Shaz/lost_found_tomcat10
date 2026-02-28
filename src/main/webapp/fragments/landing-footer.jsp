<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>

    <footer class="footer light-background">

        <div class="container footer-top">
            <div class="row gy-4">

                <!-- About Section -->
                <div class="col-lg-5 col-md-12 footer-about">
                    <a href="${pageContext.request.contextPath}/" class="logo d-flex align-items-center">
                        <span class="sitename">Patika Lost & Found</span>
                    </a>
                    <p>Welcome to Patika's Lost & Found System. We help you track, report, and claim lost items. Whether you lost something important or found an item, we are here to assist.</p>
                    <div class="social-links d-flex mt-4">
                        <a href="#" target="_blank"><i class="bi bi-twitter"></i></a>
                        <a href="#" target="_blank"><i class="bi bi-facebook"></i></a>
                        <a href="#" target="_blank"><i class="bi bi-instagram"></i></a>
                        <a href="#" target="_blank"><i class="bi bi-linkedin"></i></a>
                    </div>
                </div>

                <!-- Useful Links Section -->
                <div class="col-lg-2 col-6 footer-links">
                    <h4>Useful Links</h4>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
                        <li><a href="${pageContext.request.contextPath}/services">Services</a></li>
                        <li><a href="${pageContext.request.contextPath}/terms">Terms of Service</a></li>
                        <li><a href="${pageContext.request.contextPath}/privacy">Privacy Policy</a></li>
                    </ul>
                </div>

                <!-- Our Services Section -->
                <div class="col-lg-2 col-6 footer-links">
                    <h4>Our Services</h4>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/post-lost-item">Report a Lost Item</a></li>
                        <li><a href="${pageContext.request.contextPath}/found-items">Browse Found Items</a></li>
                        <li><a href="${pageContext.request.contextPath}/claim-found-item">Claim a Found Item</a></li>
                        <li><a href="${pageContext.request.contextPath}/track-report">Track Your Report</a></li>
                    </ul>
                </div>

                <!-- Contact Section -->
                <div class="col-lg-3 col-md-12 footer-contact text-center text-md-start">
                    <h4>Contact Us</h4>
                    <p><strong>Address:</strong> A108 Adams Street, Nairobi City, Kenya</p>
                    <p><strong>Phone:</strong> <span>+254 114 981 122</span></p>
                    <p><strong>Email:</strong> <span>support@patikalostfound.com</span></p>
                </div>

                <!-- Subscribe Section -->
                <div class="col-lg-3 col-md-12 footer-subscribe">
                    <h4>Subscribe to Our Newsletter</h4>
                    <p>Get the latest updates on lost and found items, news, and more.</p>
                    <form action="${pageContext.request.contextPath}/newsletter" method="POST" class="subscribe-form">
                        <div class="input-group">
                            <label>
                                <input type="email" class="form-control" placeholder="Enter your email" required>
                            </label>
                            <button type="submit" class="btn btn-primary">Subscribe</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>

        <!-- Copyright -->
        <div class="container copyright text-center mt-4">
            <strong><a href="${pageContext.request.contextPath}/index">Lost and Found System</a></strong> &copy; All rights reserved 2024.
            <div class="credits">
                Designed by <a href="mailto:opudosharon6@gmail.com">Opudo Shaz</a>
            </div>
        </div>

    </footer>

    <!-- Scroll Top Button -->
    <a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

    <!-- Preloader -->
    <div id="preloader"></div>

    <!-- Vendor JS Files -->
    <c:if test="${isRootUri}">
        <script src="${pageContext.request.contextPath}/resources/vendor/php-email-form/validate.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/aos/aos.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/waypoints/noframework.waypoints.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/swiper/swiper-bundle.min.js"></script>
    </c:if>

    <!-- Main JS File -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>

    <c:if test="${isRootUri}">
        <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    </c:if>

</div>
