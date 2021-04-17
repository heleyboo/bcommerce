package com.binh.core.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "motel_room")
public class MotelRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name ="title", nullable = false)
	private String title;
	
	@Column(name ="description", nullable = false)
	private String description;
	
	@Column(name ="price", nullable = false)
	private double price;
	
	@Column(name ="area", nullable = false)
	private float area;
	
	@Column(name ="count_view")
	private Long countView;
	
	@Column(name ="address", nullable = false)
	private String address;
	
	@Column(name ="latlng")
	private String latlng;
	
	@Column(name ="images")
	private String images;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "code", nullable = true)
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
	private District district;
	
	@Column(name ="utilities")
	private String utilities;
	
	@Column(name ="approve")
	private int approve;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name ="amenities")
	private int amenities;
	
	@Column(name ="created_at")
	private Date createdAt;
	
	@Column(name ="updated_at")
	private Date updatedAt;
	
	@Column(name ="slug")
	private String slug;
}
