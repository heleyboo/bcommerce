package com.binh.core.service;

import java.util.List;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

import com.binh.core.dto.request.MotelRoomDTO;
import com.binh.core.entity.MotelRoom;

import javassist.NotFoundException;

public interface MotelRoomService {
	public List<MotelRoom> getAll();
	public MotelRoom save(MotelRoomDTO room, KeycloakAuthenticationToken authentication) throws NotFoundException;
	public List<MotelRoom> getMotelRooms(String userName);
}
