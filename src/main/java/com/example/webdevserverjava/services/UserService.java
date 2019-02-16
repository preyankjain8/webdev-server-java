package com.example.webdevserverjava.services;

import java.util.List;
import java.time.Instant;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevserverjava.model.User;

@CrossOrigin(origins = "http://polar-cliffs-65655.herokuapp.com",
//@CrossOrigin(origins = "http://localhost:3000",
allowCredentials= "true")
@RestController
public class UserService {
	private static UserService userService;
	
	User alice = new User(123, "alice", "123", "Alice", "Wonderland", "Faculty");
	User bob   = new User(234, "bob", "345", "Bob", "Marley", "Student");
	public static List<User> usersList = new ArrayList<User>();
	
	public UserService() {
		usersList.add(alice);
		usersList.add(bob);
		userService = this;
	}
	public static UserService getInstance() {
		if(userService == null)
			userService = new UserService();
		return userService;
	}
	
	@PostMapping("/api/register")
	public User register (@RequestBody User newUser,
				HttpSession session) {
		
		for (User user : usersList) {
			if (user.getUsername().equals(newUser.getUsername()))
				return null;
		}
		session.setAttribute("currentUser", newUser);
		newUser.setId(Instant.now().getNano());
		usersList.add(newUser);
		return newUser;
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
		
		for (User user : usersList) {
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
		return usersList;
	}
	
	@GetMapping("/api/users/{id}")
	public User findUserById (@PathVariable("id") Integer id) {
		for(User user: usersList) {
			if(id == user.getId().intValue())
				return user;
		}
		return null;
	}
	
		
	
	@GetMapping("/api/user")
	public User[] findAllUser() {
		User[] userArray = new User[usersList.size()];
		int index = 0;
		for (User user : usersList) {
			userArray[index] = user;
			index++;
		}
		return userArray;
	}
	/*@GetMapping("/api/user/{userId}")
	public User findUserById(
			@PathVariable("userId") Integer id) {
		for(User user: usersList) {
			if(id == user.getId().intValue())
				return user;
		}
		return null;
	}*/
	@PostMapping("/api/user")
	@ResponseBody
	public User createUser(@RequestBody User user) {
		for (User u : usersList) {
			if(u.getUsername().equals(user.getUsername())) {
				return new User();
			}
		}
		if(!usersList.isEmpty()) {
			user.setId(usersList.get(usersList.size()-1).getId() + 1);
		}
		else {
			user.setId(1);
		}
		usersList.add(user);
		return user;
	}
	@PostMapping("/api/user/{userId}")
	@ResponseBody
	public User updateUser(@RequestBody User user, @PathVariable("userId") Integer id) {
		User userToUpd = null;
		for(User u : usersList) {
			if (u.getId() == id.intValue()) {
				usersList.remove(u);
				break;
			}
		}
		userToUpd = user;
		userToUpd.setId(id);
		usersList.add(userToUpd);
		return userToUpd;
	}
	
	@PostMapping("/api/user/delete/{userId}")
	@ResponseBody
	public void deleteUser(@PathVariable("userId") Integer id) {
		for(User u : usersList) {
			if (u.getId() == id.intValue()) {
				usersList.remove(u);
				break;
			}
		}
	}
	
	@PostMapping("/api/user/search")
	@ResponseBody
	public User[] searchUser(@RequestBody User user) {
		List<User> tempUserList = new ArrayList<User>();
		for(User u : usersList) {
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
