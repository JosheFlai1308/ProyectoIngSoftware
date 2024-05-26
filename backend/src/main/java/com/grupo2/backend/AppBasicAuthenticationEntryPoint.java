package com.grupo2.backend;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AppBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	  public void commence(HttpServletRequest request,
	                       HttpServletResponse response,
	                       AuthenticationException authEx) throws IOException {

	    response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    PrintWriter writer = response.getWriter();
	    writer.println("HTTP Status 401 - " + authEx.getMessage());
	  }

	  @Override
	  public void afterPropertiesSet() {
	    setRealmName("howtodoinjava");
	    super.afterPropertiesSet();
	  }
}