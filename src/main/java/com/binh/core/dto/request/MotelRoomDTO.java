package com.binh.core.dto.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.binh.core.entity.RoomType;
import com.binh.core.enums.RoomDirection;

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
	
//	@NotEmpty
//	private RoomType roomType;
	
 	@NotEmpty(message = "Tiêu đề bắt buộc nhập")
	private String title;
 	
	private String description;

	@NotNull(message = "Giá bắt buộc nhập")
	private double price;
	
	private double depositAmount;

	@NotNull(message = "Diện tích bắt buộc nhập")
	private float area;
	
	@NotNull(message = "Vui lòng nhập số phòng ngủ")
	private int numOfBedrooms;
	
	@NotNull(message = "Vui lòng nhập số toilets")
	private int numOfToilets;
	
	private RoomDirection doorDirection;
	
	private RoomDirection balconyDirection;

	@NotEmpty(message = "Địa chỉ bắt buộc nhập")
	private String address;

	private String latlng;

	@NotEmpty(message = "Danh mục bắt buộc nhập")
	private String category;

	@NotEmpty(message = "Xã/phường bắt buộc nhập")
	private String ward;

	private String utilities;

	@NotEmpty(message = "SDT bắt buộc nhập")
	private String phoneNumber;

	private String slug;
	
	private List<String> images;
}
