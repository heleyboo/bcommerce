package com.binh.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "id_user")
	private int idUser;
	
	@Column(name = "id_motel")
	private int idMotel;
	
	@Column(name = "rate")
	private int rate;
	
	@Column(name = "comment")
	private int comment;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "created_at")
	private int createdAt;
	
	@Column(name = "updated_at")
	private int updatedAt;
}
