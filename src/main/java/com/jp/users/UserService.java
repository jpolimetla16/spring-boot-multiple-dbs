package com.jp.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity saveUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

}
