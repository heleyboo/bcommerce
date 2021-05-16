package com.binh.core.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.MotelRoom;
import com.binh.core.specifications.Filter;
import com.binh.core.specifications.SearchCriteria;

import javassist.NotFoundException;

public interface MotelRoomService {
	public Page<MotelRoom> getAll(Integer pageNum, Integer pageSize);
	public Page<MotelRoom> searchRooms(Filter filter);
	public MotelRoom save(MotelRoomDTO room, KeycloakAuthenticationToken authentication) throws NotFoundException, FileNotFoundException, IOException;
	public List<MotelRoom> getMotelRooms(String userName);
	public MotelRoom getMotelRoomById(Integer id) throws NotFoundException, Exception;
	
}
