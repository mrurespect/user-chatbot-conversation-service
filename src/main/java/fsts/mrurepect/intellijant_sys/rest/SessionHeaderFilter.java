package fsts.mrurepect.intellijant_sys.rest;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SessionHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Get the session ID from the request
        String sessionId = req.getSession().getId();

        // Add the session ID to the response headers
        res.setHeader("Session-Id", sessionId);

        chain.doFilter(request, response);
    }
}