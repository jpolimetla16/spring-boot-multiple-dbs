package com.jp.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity){
		UserEntity _userEntity = userService.saveUser(userEntity);
		return  ResponseEntity.status(HttpStatus.CREATED).body(_userEntity);
	}
	
	@GetMapping
	public List<UserEntity> getAllUsers(){
		return userService.getAllUsers();
	}

}
