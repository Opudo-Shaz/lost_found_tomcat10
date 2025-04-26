package com.example.lostandfound.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class StaticResourceBypassFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.startsWith("/resources/")) {
            // Let Tomcat handle static files (CSS, JS, images, etc.)
            chain.doFilter(request, response);
            return;
        }

        // Continue with normal processing
        // (forward to controller, JSP, etc.)
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
