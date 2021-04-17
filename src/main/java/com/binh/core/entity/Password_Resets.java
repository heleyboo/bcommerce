package com.binh.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Password_Resets {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "email")
	private String email;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "created_at")
	private int createdAt;
}
