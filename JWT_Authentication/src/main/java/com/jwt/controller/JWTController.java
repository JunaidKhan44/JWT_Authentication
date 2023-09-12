/**
 * 
 */
package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.services.CustomUserDetailServices;

/**
 * @author Junaid.Khan
 *
 */

@RestController
public class JWTController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailServices customUserDetailServices;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {
			
			System.out.println(jwtRequest);
			authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),
					jwtRequest.getPassword()));	
			
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Request Exception...");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Request Exception...");
		}

		//fine user...
		UserDetails userDetails=
				this.customUserDetailServices.loadUserByUsername(jwtRequest.getUserName());
		
		String token=this.jwtUtil.generateToken(userDetails);
		System.out.println("token :"+token);
		
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

}
