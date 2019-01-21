package com.example.webdevserverjava.services;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.model.User;

@RestController
public class UserService {
	User alice = new User(123, "alice", "Alice", "Wonderland");
	User bob   = new User(234, "bob", "Bob", "Marley");
	java.util.List<User> userList = new ArrayList<User>();
	UserService(){
		userList.add(alice);
		userList.add(bob);
	}
	//User[] users = {alice, bob};

	@GetMapping("/api/user")
	public User[] findAllUser() {
		User[] userArray = new User[userList.size()];
		int index = 0;
		for (User user : userList) {
			userArray[index] = user;
			index++;
		}
		return userArray;
	}
	@GetMapping("/api/user/{userId}")
	public User findUserById(
			@PathVariable("userId") Integer id) {
		for(User user: userList) {
			if(id == user.getId().intValue())
				return user;
		}
		return null;
	}
	@PostMapping("/api/user")
	@ResponseBody
	public User createUser(@RequestBody User user) {
		user.setId(456);
		userList.add(user);
		return user;
	}
	
}
