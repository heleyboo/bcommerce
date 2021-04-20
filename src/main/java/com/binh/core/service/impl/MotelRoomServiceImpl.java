package com.binh.core.service.impl;

import java.util.List;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.MotelRoom;
import com.binh.core.repository.MotelRoomRepository;
import com.binh.core.service.CategoryService;
import com.binh.core.service.DistrictService;
import com.binh.core.service.KeyCloakService;
import com.binh.core.service.MotelRoomService;
import com.binh.core.service.ProvinceService;

import javassist.NotFoundException;

@Service
public class MotelRoomServiceImpl implements MotelRoomService {
	@Autowired
	private MotelRoomRepository repo;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private KeyCloakService keyCloakService;

	public List<MotelRoom> getAll() {
		return repo.findAll();
	}

	@Override
	public MotelRoom save(MotelRoomDTO room, KeycloakAuthenticationToken authentication) throws NotFoundException {
		MotelRoom motelRoom = new MotelRoom();
		motelRoom.setTitle(room.getTitle());
		motelRoom.setDescription(room.getDescription());
		motelRoom.setPrice(room.getPrice());
		motelRoom.setArea(room.getArea());
		motelRoom.setAddress(room.getAddress());
		motelRoom.setCategory(categoryService.getCategoryByCode(room.getCategory()));
		motelRoom.setDistrict(districtService.getDistrictById(room.getDistrict()));
		motelRoom.setAddress(room.getAddress());
		motelRoom.setPhoneNumber(room.getPhoneNumber());
		motelRoom.setSlug(room.getSlug());
		motelRoom.setUserName(keyCloakService.getAuthUsername(authentication));
		
		return repo.save(motelRoom);
	}

	@Override
	public List<MotelRoom> getMotelRooms(String userName) {
		return repo.findByUserName(userName);
	}
}
