package com.binh.core.service;

import java.util.List;
import java.util.Optional;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.data.domain.Page;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.MotelRoom;

import javassist.NotFoundException;

public interface MotelRoomService {
	public Page<MotelRoom> getAll(Integer pageNum, Integer pageSize);
	public MotelRoom save(MotelRoomDTO room, KeycloakAuthenticationToken authentication) throws NotFoundException;
	public List<MotelRoom> getMotelRooms(String userName);
	public MotelRoom getMotelRoomById(Integer id) throws NotFoundException;
}
