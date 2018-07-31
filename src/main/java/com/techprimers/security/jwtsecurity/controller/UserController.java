package com.techprimers.security.jwtsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.techprimers.security.jwtsecurity.model.JwtUser;
import com.techprimers.security.jwtsecurity.service.UserService;

@RestController
@RequestMapping("/rest/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	@ResponseBody
	public Iterable<JwtUser> list(){
		return userService.findAll();
	}
	
	@PostMapping
	@ResponseBody
	public JwtUser register(@RequestParam String username, @RequestParam String role) {
		JwtUser user = new JwtUser(username, role);
		
		if(username != null && username.length() > 0 && role != null && role.length() > 0){
			userService.save(user);
		}
		
		return user;
	}
}
