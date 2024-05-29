package com.login.service;

import com.login.dto.UserDto;
import com.login.entity.User;

public interface UserService {

	public User findByUsername(String username);
	User save(UserDto userDto);
	
}
