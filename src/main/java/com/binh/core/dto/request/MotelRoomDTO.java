package com.binh.core.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	
 	@NotEmpty(message = "Tiêu đề bắt buộc nhập")
	private String title;
 	
	private String description;

	@NotNull(message = "Giá bắt buộc nhập")
	private double price;

	@NotNull(message = "Diện tích bắt buộc nhập")
	private float area;

	@NotEmpty(message = "Địa chỉ bắt buộc nhập")
	private String address;

	private String latlng;

	@NotEmpty(message = "Danh mục bắt buộc nhập")
	private String category;

	@NotEmpty(message = "Quận/huyện bắt buộc nhập")
	private String district;

	private String utilities;

	@NotEmpty(message = "SDT bắt buộc nhập")
	private String phoneNumber;

	private String slug;
}
