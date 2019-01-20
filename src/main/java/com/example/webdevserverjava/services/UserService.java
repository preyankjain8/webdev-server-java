package com.example.webdevserverjava.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.model.User;

@RestController
public class UserService {
	User alice = new User(123, "alice", "Alice", "Wonderland");
	User bob   = new User(234, "bob", "Bob", "Marley");
	User[] users = {alice, bob};

	@GetMapping("/api/user")
	public User[] findAllUser() {
		return users;
	}
	@GetMapping("/api/user/{userId}")
	public User findUserById(
			@PathVariable("userId") Integer id) {
		for(User user: users) {
			if(id == user.getId().intValue())
				return user;
		}
		return null;
	}
}
