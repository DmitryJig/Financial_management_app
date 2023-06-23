package com.finance.app.security.filter;

import com.finance.app.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtDataFilter implements Filter {

    private final JwtService jwtService;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("JwtDataFilter is working");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String requestUri = httpRequest.getRequestURI();

        if (httpRequest.getRequestURI().contains("users") && httpRequest.getHeader(HttpHeaders.AUTHORIZATION) != null) {

            Long idFromRequest = jwtService.getIdFromRequest(requestUri);

            String token = Optional.ofNullable(httpRequest.getHeader(HttpHeaders.AUTHORIZATION))
                    .filter(t -> t.startsWith("Bearer "))
                    .map(t -> t.substring("Bearer ".length())).orElse("");

            Long idFromToken = jwtService.getUserId(token);

            if (!idFromRequest.equals(idFromToken)) {

                log.error("Id user not match");

                httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
                httpResponse.getWriter().println("Id user not match");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
