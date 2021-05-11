package com.binh.core.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.binh.core.entity.MotelRoom;

public final class MotelRoomSpecification {
	
	public static Specification<MotelRoom> wardCodeEqual(String wardCode) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.get("wardCode"), wardCode);
	}
	
	public static Specification<MotelRoom> priceGreaterOrEqual(double price) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
	}
	
	public static Specification<MotelRoom> priceLessThanOrEqual(double price) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
	}
	
	public static Specification<MotelRoom> areaGreaterOrEqual(double area) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.greaterThanOrEqualTo(root.get("area"), area);
	}
	
	public static Specification<MotelRoom> areaLessThanOrEqual(double area) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.lessThanOrEqualTo(root.get("area"), area);
	}
	
	

}
