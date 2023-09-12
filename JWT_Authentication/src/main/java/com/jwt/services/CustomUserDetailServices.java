package com.jwt.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Junaid.Khan
 *
 */

@Service
public class CustomUserDetailServices implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if(username.equals("Junaid")) {
		
			//this user provide by spring u can also provide your own user class object
			
			return new User( "Junaid" , "Junaid123" ,new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("user not found....");
		}
		
	}

}
