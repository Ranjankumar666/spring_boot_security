package com.example.security_spring.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.security_spring.util.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtReqFilter extends OncePerRequestFilter {
    @Autowired
    private Jwt jwtUtil;

    @Autowired
    private UserDetailsService uds;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain next)
            throws ServletException, IOException {

        final String authHeader = req.getHeader("Authorization");
        String jwtToken = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.split(" ")[1];
            username = jwtUtil.extractUsername(jwtToken);

        }

       
        // Add token only when context is empty
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = uds.loadUserByUsername(username);
            
            if (jwtUtil.validateToken(jwtToken, user) == true) {
                UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());

                upToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                ;
                SecurityContextHolder.getContext().setAuthentication(upToken);
            }
        }

        next.doFilter(req, res);

    }

}
