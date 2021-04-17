package com.binh.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MotelRoomDTO {
	// new MotelRoomDTO("","")
	private String title;
	
	private String description;
	
	private double price;
	
	private float area;
	
	private String address;
	
	private String latlng;
	
    private String userName;
	
	private String category;
	
	private String district;
	
	private String utilities;
	
	private String phoneNumber;
	
	private int amenities;
	
	private String slug;
}
