package com.binh.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Reports {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "ip_andress")
	private String ipAndress;
	
	@Column(name = "id_motelroom")
	private int idMotelroom;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "created_at")
	private int createdAt;
	
	@Column(name = "updated_at")
	private int updatedAt;
}
