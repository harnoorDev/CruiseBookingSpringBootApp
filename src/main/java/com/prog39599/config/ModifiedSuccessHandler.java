package com.prog39599.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class ModifiedSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication auth) throws IOException, ServletException {
 
        Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());
 
        if (roles.contains("ROLE_STAFF")) {
            httpServletResponse.sendRedirect("/staff");
        } 
        
        else if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user");
        } 
        
        
        else if(roles.contains("ROLE_ADMIN"))
        {
        	 httpServletResponse.sendRedirect("/admin");
        }
        
  
        
        
        else {
            httpServletResponse.sendRedirect("/");
        }
    }
	
}
