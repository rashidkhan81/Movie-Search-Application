package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.login.dto.UserDto;
import com.login.entity.User;
import com.login.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{
	
//	@Autowired
//	private UserRepository userRepository;
//	
//constructor injection
	private UserRepository userRepository;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
	
	this.userRepository = userRepository;
}

	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getUsername()
				,passwordEncoder.encode( userDto.getPassword())
				,userDto.getFullname());
				
		return userRepository.save(user);
	}
	//this method is for removing the message from the registration page
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("success");
	}


}
