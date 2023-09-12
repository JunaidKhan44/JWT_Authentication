/**
 * 
 */
package com.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Junaid.Khan
 *
 */

@RestController
public class HomeController {

	@RequestMapping("/welcome")
	public String welcome() {
		
		String text="this is prvilage for Jwt authentication";
		return text;
		
	}
	
	
	@RequestMapping("/getUsers")
	public String getUsers() {
		
		
		return "{\"name\":\"Junaid\"}";
		
	}
}
