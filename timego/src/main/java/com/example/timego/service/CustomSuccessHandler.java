package com.example.timego.service;
//
//import java.io.IOException;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Service;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Service
//public class CustomSuccessHandler implements AuthenticationSuccessHandler{
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//			
//		var authority = authentication.getAuthorities();
//		var roles = authority.stream().map(related -> related.getAuthority()).findFirst();
//		
//		if(roles.orElse("").equals("ADMIN")) {
//			response.sendRedirect("/admin/**");
//		}
//		else if(roles.orElse("").equals("USER")) {
//			response.sendRedirect("/user/payment");
//		}
//		else {
//			response.sendRedirect("/error");
//		}
//		
//	}
//	
//	
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Retrieve the originally requested URL before authentication
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) requestCache.getRequest(request, response);
        String targetUrl = (savedRequest != null) ? savedRequest.getRedirectUrl() : null;

        // Get the role of the authenticated user
        var authority = authentication.getAuthorities();
        var role = authority.stream().map(auth -> auth.getAuthority()).findFirst().orElse("");

        // If user was trying to access a page before login, redirect there
        if (targetUrl != null) {
            response.sendRedirect(targetUrl);
            return;
        }

        // Default role-based redirection if no specific page was accessed
        if (role.equals("ADMIN")) {
            response.sendRedirect("/admin/dashboard");
        } else if (role.equals("USER")) {
            response.sendRedirect("/user");
        } else {
            response.sendRedirect("/error");
        }
    }
}

	

