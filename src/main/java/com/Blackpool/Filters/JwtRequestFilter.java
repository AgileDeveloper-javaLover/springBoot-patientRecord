package com.Blackpool.Filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Blackpool.Security.JwtService;
import com.Blackpool.Security.MyUserDetailService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader=request.getHeader("Authorization");
		
		String username=null;
		String jwt=null;
		if(authHeader!=null) {

			if(authHeader.startsWith("Bearer ")) {
				jwt=authHeader.substring(7);
				username=jwtService.extractUsername(jwt);
			}
		}
		
		if(username!=null) {
			if(SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails=myUserDetailService.loadUserByUsername(username);
				if(jwtService.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					
					usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
				}
			}
		}
		filterChain.doFilter(request, response);
	}
	
	
}
