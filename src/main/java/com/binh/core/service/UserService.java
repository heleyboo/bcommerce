package com.binh.core.service;

import com.binh.core.entity.User;

public interface UserService {
	public User findUserByEmail(String email);
	public User findUserByUserName(String userName);
	public User saveUser(User user);
}
