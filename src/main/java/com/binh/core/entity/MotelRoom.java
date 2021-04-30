package com.binh.core.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.binh.core.enums.RoomDirection;

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
	
	@Column(name = "deposit_amount", nullable = true)
	private double depositAmount;
	
	@Column(name ="area", nullable = false)
	private float area;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id", nullable = true)
	private RoomType roomType;
	
	@Column(name = "num_of_bedrooms", nullable = false)
	private int numOfBedrooms;
	
	@Column(name = "num_of_toilets", nullable = false)
	private int numOfToilets;
	
	@Column(name = "door_direction")
	private RoomDirection doorDirection;
	
	@Column(name = "balcony_direction")
	private RoomDirection balconyDirection;
	
	@Column(name ="count_view")
	private Long countView;
	
	@Column(name ="address", nullable = false)
	private String address;
	
	@Column(name ="latlng")
	private String latlng;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<RoomImage> images;
	
	@Column(name ="user_name", nullable = false)
	private String userName;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "code", nullable = true)
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id", referencedColumnName = "code", nullable = false)
	private Ward ward;
	
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
