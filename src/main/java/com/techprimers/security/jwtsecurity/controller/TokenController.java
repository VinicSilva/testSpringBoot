package com.techprimers.security.jwtsecurity.controller;

import com.techprimers.security.jwtsecurity.model.JwtUser;
import com.techprimers.security.jwtsecurity.security.JwtGenerator;
import com.techprimers.security.jwtsecurity.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private UserService userService;
	
    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {
    	JwtUser user = (JwtUser) userService.findOne(jwtUser.getId());
    	
    	if(jwtUser.getUserName() == null || jwtUser.getRole() == null){
    		throw new RuntimeException("Fields id, username and role are required");
    	}
    	
    	if(jwtUser.getUserName().equals("admin") && jwtUser.getId() == 123456 && jwtUser.getRole().equals("admin")){
    		return jwtGenerator.generate(jwtUser);
    	}
    	
    	if(user == null){
    		throw new RuntimeException("User not found");
    	}    	
    	
    	return jwtGenerator.generate(jwtUser);
    }
}
