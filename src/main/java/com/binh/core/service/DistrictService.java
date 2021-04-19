package com.binh.core.service;

import com.binh.core.entity.District;

import javassist.NotFoundException;

public interface DistrictService {
	public District getDistrictById(String districtId) throws NotFoundException;
}
