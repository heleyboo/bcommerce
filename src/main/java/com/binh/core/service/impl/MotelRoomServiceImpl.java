package com.binh.core.service.impl;

import java.util.List;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.MotelRoom;
import com.binh.core.entity.Ward;
import com.binh.core.repository.MotelRoomRepository;
import com.binh.core.service.CategoryService;
import com.binh.core.service.DistrictService;
import com.binh.core.service.KeyCloakService;
import com.binh.core.service.MotelRoomService;
import com.binh.core.service.ProvinceService;
import com.binh.core.service.WardService;

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
	
	@Autowired
	private WardService wardService;

	public Page<MotelRoom> getAll(Integer pageNum, Integer pageSize) {
		
		Pageable pageAble = PageRequest.of(pageNum, pageSize);
		
		return repo.findAll(pageAble);
	}

	@Override
	public MotelRoom save(MotelRoomDTO room, KeycloakAuthenticationToken authentication) throws NotFoundException {
		MotelRoom motelRoom = new MotelRoom();
		motelRoom.setTitle(room.getTitle());
		motelRoom.setDescription(room.getDescription());
		motelRoom.setPrice(room.getPrice());
		motelRoom.setArea(room.getArea());
		motelRoom.setDepositAmount(room.getDepositAmount());
		motelRoom.setBalconyDirection(room.getBalconyDirection());
		motelRoom.setDoorDirection(room.getDoorDirection());
		motelRoom.setNumOfBedrooms(room.getNumOfBedrooms());
		motelRoom.setNumOfToilets(room.getNumOfToilets());
		
		String wardCode = room.getWard();
		Ward ward = wardService.getWardByCode(wardCode).orElseThrow(() -> new NotFoundException("Ward not found"));
		
		motelRoom.setWard(ward);
		motelRoom.setAddress(room.getAddress());
		motelRoom.setCategory(categoryService.getCategoryByCode(room.getCategory()));
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
