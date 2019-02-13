package com.example.webdevserverjava.model;

public class Faculty extends User{
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public Faculty() {}
	public Faculty(int id, String username, String password, String firstName, String lastName) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
