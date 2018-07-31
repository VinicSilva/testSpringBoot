package com.techprimers.security.jwtsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JwtUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
    private long id;
	@Column
	private String userName;
	@Column
    private String role;
    
    public JwtUser(){
    	
    }
    
    public JwtUser(String username, String role) {
		this.userName = username;
		this.role = role;
	}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
