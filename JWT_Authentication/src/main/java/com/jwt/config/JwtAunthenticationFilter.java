/**
 * 
 */
package com.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.helper.JwtUtil;
import com.jwt.services.CustomUserDetailServices;

/**
 * @author Junaid.Khan
 *
 */

@Component
public class JwtAunthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	

	@Autowired
	private CustomUserDetailServices customUserDetailServices;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//get jwt
		//Bearer
		//validate
		String requestTokenHeader = request.getHeader("Authentication");
		String userName = null;
		String jwtToken = null;
		//null and format
		if(requestTokenHeader != null
				&& requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				
			userName = jwtUtil.extractUsername(jwtToken);
				
			}
			catch(Exception e) {
				
			}
			
			UserDetails userDetails=this.customUserDetailServices.loadUserByUsername(userName);
			
			if(userName!=null &&
					SecurityContextHolder.getContext().getAuthentication()==null) {
				UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}else {
			System.out.println("Not valid token....");	
			}
			
		
		}
		
		filterChain.doFilter(request, response);
		
	}

}
