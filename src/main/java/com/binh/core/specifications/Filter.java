package com.binh.core.specifications;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Filter {
	private double minPrice;
	private double maxPrice;
	private double minArea;
	private double maxArea;
	private int numOfBedrooms;
	private int numOfToilets;
	private int pageNum;
	private int pageSize;
	private String wardCode;
	private String districtCode;
	private String provinceCode;
}
