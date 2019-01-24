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
	User alice = new User(123, "alice", "Alice", "Wonderland", "Faculty");
	User bob   = new User(234, "bob", "Bob", "Marley", "Student");
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
		for (User u : userList) {
			if(u.getUsername().equals(user.getUsername())) {
				return new User();
			}
		}
		user.setId(userList.get(userList.size()-1).getId() + 1);
		userList.add(user);
		return user;
	}
	@PostMapping("/api/user/{userId}")
	@ResponseBody
	public User updateUser(@RequestBody User user, @PathVariable("userId") Integer id) {
		User userToUpd = null;
		for(User u : userList) {
			if (u.getId() == id.intValue()) {
				userList.remove(u);
				break;
			}
		}
		userToUpd = user;
		userToUpd.setId(id);
		userList.add(userToUpd);
		return userToUpd;
	}
	
	@PostMapping("/api/user/delete/{userId}")
	@ResponseBody
	public void deleteUser(@PathVariable("userId") Integer id) {
		for(User u : userList) {
			if (u.getId() == id.intValue()) {
				userList.remove(u);
				break;
			}
		}
	}
	
	@PostMapping("/api/user/search")
	@ResponseBody
	public User[] searchUser(@RequestBody User user) {
		java.util.List<User> tempUserList = new ArrayList<User>();
		for(User u : userList) {
			if (user.getUsername() != "" && !u.getUsername().toLowerCase().contains(user.getUsername().toLowerCase())) {
				continue;
			}
			if (user.getFirstName() != "" && !u.getFirstName().toLowerCase().contains(user.getFirstName().toLowerCase())) {
				continue;
			}
			if (user.getLastName() != "" && !u.getLastName().toLowerCase().contains(user.getLastName().toLowerCase())) {
				continue;
			}
			if (user.getRole() != "" && !u.getRole().toLowerCase().contains(user.getRole().toLowerCase())) {
				continue;
			}
			if (user.getPassword() != "" && !u.getPassword().toLowerCase().contains(user.getPassword().toLowerCase())) {
				continue;
			}
			tempUserList.add(u);
		}
		User[] userArr = new User[tempUserList.size()];
		int index = 0;
		for (User us : tempUserList) {
			userArr[index] = us;
			index++;
		}
		return userArr;
	}
	
}
