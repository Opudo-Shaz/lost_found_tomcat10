<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="/fragments/head.jsp" %>


<body class="index-page">
<%@ include file="/fragments/header.jsp" %>

<main class="main">

    <!-- Hero Section -->
    <section id="hero" class="hero section bg-dark text-white position-relative">
        <img src="https://via.placeholder.com/1200x400" alt="" data-aos="fade-in">

        <div class="container text-center" data-aos="fade-up" data-aos-delay="100">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <h2>Welcome to Patika Lost and Found</h2>
                    <p>Your go-to platform for lost and found items.</p>
                    <a href="${pageContext.request.contextPath}/lost-items" class="btn btn-primary btn-lg">Browse Lost Items</a>
                </div>
            </div>
        </div>
    </section>

    <!-- What We Do Section -->
    <section class="what-we-do section py-5">
        <div class="container section-title" data-aos="fade-up">
            <h2>What We Do</h2>
            <p>Our platform helps individuals reconnect with lost belongings...</p>
        </div>

        <div class="container">
            <div class="row gy-4">
                <div class="col-lg-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="why-box">
                        <h3>Why Choose Our Lost & Found System?</h3>
                        <p>Report lost items and search for your belongings easily and securely.</p>
                        <div class="text-center">
                            <a href="#about" class="btn btn-outline-primary">Learn More</a>
                        </div>
                    </div>
                </div>

                <div class="col-lg-8 d-flex align-items-stretch">
                    <div class="row gy-4 w-100" data-aos="fade-up" data-aos-delay="200">
                        <div class="col-xl-4 text-center">
                            <i class="bi bi-clipboard-data"></i>
                            <h4>Easy Reporting</h4>
                            <p>Quickly describe and post your lost item.</p>
                        </div>
                        <div class="col-xl-4 text-center">
                            <i class="bi bi-chat-dots"></i>
                            <h4>Secure Chat</h4>
                            <p>Communicate with finders safely and privately.</p>
                        </div>
                        <div class="col-xl-4 text-center">
                            <i class="bi bi-check-circle"></i>
                            <h4>Fast Claims</h4>
                            <p>Reclaim your item quickly after verification.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- About Section -->
    <section id="about" class="about section py-5 bg-light">
        <div class="container section-title" data-aos="fade-up">
            <h2>About Our System</h2>
            <p>Helping people reconnect with their belongings quickly and safely.</p>
        </div>
        <div class="container">
            <div class="row gy-3">
                <div class="col-lg-6" data-aos="fade-up" data-aos-delay="100">
                    <img src="https://via.placeholder.com/600x400" alt="About Lost and Found" class="img-fluid">
                </div>
                <div class="col-lg-6 d-flex flex-column justify-content-center" data-aos="fade-up" data-aos-delay="200">
                    <div class="about-content ps-0 ps-lg-3">
                        <h3>Why Our System Matters</h3>
                        <p class="fst-italic">Our platform is a bridge between those who’ve lost and those who’ve found.</p>
                        <ul>
                            <li><i class="bi bi-clipboard-data"></i> Easy Reporting</li>
                            <li><i class="bi bi-gem"></i> Fast Reconnection</li>
                            <li><i class="bi bi-lock"></i> Private Communication</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Services Section -->
    <section id="services" class="services section py-5">
        <div class="container section-title" data-aos="fade-up">
            <h2>Our Services</h2>
            <p>Report, browse, and claim lost and found items easily.</p>
        </div>
        <div class="container">
            <div class="row gy-4">
                <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="100">
                    <div class="service-item position-relative">
                        <i class="bi bi-flag"></i>
                        <a href="${pageContext.request.contextPath}/post-lost-item"><h3>Report a Lost Item</h3></a>
                        <p>Let others know what you've lost.</p>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="200">
                    <div class="service-item position-relative">
                        <i class="bi bi-search"></i>
                        <a href="${pageContext.request.contextPath}/found-items"><h3>Browse Found Items</h3></a>
                        <p>Look through the found items listed by others.</p>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6" data-aos="fade-up" data-aos-delay="300">
                    <div class="service-item position-relative">
                        <i class="bi bi-check-circle"></i>
                        <a href="${pageContext.request.contextPath}/found-items/claim-found-item"><h3>Claim a Found Item</h3></a>
                        <p>If something looks like yours, claim it!</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Stats Section -->
    <section class="stats section py-5 bg-light">
        <div class="container" data-aos="fade-up">
            <div class="row gy-4">
                <div class="col-lg-3 col-md-6">
                    <div class="stats-item text-center">
                        <h3>232</h3>
                        <p>Clients</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="stats-item text-center">
                        <h3>1453</h3>
                        <p>Hours Of Support</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Testimonials Section -->
    <section id="testimonials" class="testimonials section py-5">
        <div class="container section-title" data-aos="fade-up">
            <h2>What Our Users Say</h2>
            <p>Real feedback from those we've helped.</p>
        </div>
        <div class="container" data-aos="fade-up" data-aos-delay="100">
            <div class="swiper swiper-initialized">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <div class="testimonial-item text-center">
                            <img src="https://via.placeholder.com/100" class="testimonial-img" alt="User 1">
                            <h3>Kelvin Nyokabi</h3>
                            <h4>Lost Item Recoverer</h4>
                            <p>"I got my wallet back! So thankful."</p>
                        </div>
                    </div>

                    <div class="swiper-slide">
                        <div class="testimonial-item text-center">
                            <img src="https://via.placeholder.com/100" class="testimonial-img" alt="User 2">
                            <h3>Sarah Nyambura</h3>
                            <h4>Student</h4>
                            <p>"Lost phone found through this system. Amazing!"</p>
                        </div>
                    </div>
                </div>
                <div class="swiper-pagination"></div>
            </div>
        </div>
    </section>

    <!-- Contact Section -->
    <section id="contact" class="contact section py-5 bg-light">
        <div class="container section-title" data-aos="fade-up">
            <h2>Contact Us</h2>
            <p>Reach out if you have questions or feedback.</p>
        </div>
        <div class="container">
            <div class="row gy-4">
                <div class="col-lg-5">
                    <div class="info-item d-flex">
                        <i class="bi bi-geo-alt"></i>
                        <div>
                            <h3>Location</h3>
                            <p>Lost and Found Ave, Nairobi</p>
                        </div>
                    </div>
                    <div class="info-item d-flex">
                        <i class="bi bi-telephone"></i>
                        <div>
                            <h3>Call Us</h3>
                            <p>+254114981122</p>
                        </div>
                    </div>
                    <div class="info-item d-flex">
                        <i class="bi bi-envelope"></i>
                        <div>
                            <h3>Email</h3>
                            <p>support@lostandfound@gmail.com</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7">
                    <form action="${pageContext.request.contextPath}/contact" method="post" class="php-email-form">
                        <div class="row gy-4">
                            <div class="col-md-6"><label>
                                <input type="text" name="name" class="form-control" placeholder="Your Name" required>
                            </label></div>
                            <div class="col-md-6"><label>
                                <input type="email" name="email" class="form-control" placeholder="Your Email" required>
                            </label></div>
                            <div class="col-md-12"><label>
                                <input type="text" name="subject" class="form-control" placeholder="Subject" required>
                            </label></div>
                            <div class="col-md-12"><label>
                                <textarea name="message" class="form-control" rows="6" placeholder="Message" required></textarea>
                            </label></div>
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary">Send Message</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

</main>

<%@ include file="/fragments/landing-footer.jsp" %>

</body>

</html>
