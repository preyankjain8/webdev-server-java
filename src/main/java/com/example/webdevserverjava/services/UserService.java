package com.example.webdevserverjava.services;

import java.util.List;
import java.time.Instant;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.models.User;
import com.example.webdevserverjava.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true",
allowedHeaders = "*")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@PostMapping("/api/register")
	public User register (@RequestBody User newUser,
				HttpSession session) {
		
		for (User user : userRepository.findAll()) {
			if (user.getUsername().equals(newUser.getUsername()))
				return null;
		}
		User user = userRepository.save(newUser);
		session.setAttribute("currentUser", user);
		return user;
	}
	
	@PostMapping("/api/profile")
	public User profile (HttpSession session) {
		if (session.getAttribute("currentUser") != null)
			return (User)session.getAttribute("currentUser");
		else
			return null;
	}
	
	@PostMapping("/api/login")
	public User login (@RequestBody User newUser,
				HttpSession session) {
		List<User> userList = (List<User>)userRepository.findAll();
		for (User user : userRepository.findAll()) {
			if (user.getUsername().equals(newUser.getUsername())
					&& user.getPassword().equals(newUser.getPassword())){
				session.setAttribute("currentUser", user);
				return user;
			}
		}
		
		return null;
	}
	
	@PostMapping("/api/logout")
	public void logout (HttpSession session) {
		session.invalidate();
	}
	
	@GetMapping("/api/users")
	public List<User> findAllUsers () {
		return (List<User>)userRepository.findAll();
	}
	
	@GetMapping("/api/users/{id}")
	public User findUserById (@PathVariable("id") Integer id) {
		for(User user: userRepository.findAll()) {
			if(id == user.getId().intValue())
				return user;
		}
		return null;
	}
	
		
	
	@GetMapping("/api/user")
	public User[] findAllUser() {
		User[] userArray = new User[((List<User>)userRepository.findAll()).size()];
		int index = 0;
		for (User user : userRepository.findAll()) {
			userArray[index] = user;
			index++;
		}
		return userArray;
	}
	
	@PostMapping("/api/user")
	@ResponseBody
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PostMapping("/api/user/{userId}")
	@ResponseBody
	public User updateUser(@RequestBody User user, @PathVariable("userId") Integer id) {
		User u = userRepository.findById(id).get();
		u.set(user);
		return userRepository.save(u);
	}
	
	@PostMapping("/api/user/delete/{userId}")
	@ResponseBody
	public void deleteUser(@PathVariable("userId") Integer id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/api/user/search")
	@ResponseBody
	public User[] searchUser(@RequestBody User user) {
		List<User> tempUserList = new ArrayList<User>();
		for(User u : userRepository.findAll()) {
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
