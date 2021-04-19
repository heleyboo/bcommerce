package com.binh.core.service;

import java.util.List;

import com.binh.core.entity.District;
import com.binh.core.entity.Province;

public interface ProvinceService {
	public List<Province> getAll();
	public List<District> getDistricts(String provinceId);
	public List<Province> getProvinces();
}
