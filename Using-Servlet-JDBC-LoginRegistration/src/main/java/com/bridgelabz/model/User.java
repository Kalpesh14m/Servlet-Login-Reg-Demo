package com.bridgelabz.model;

public class User {

	private String name;
	private String email;
	private Long mobile;
	private String role;
	private String password;

	public User() {
	}

	public User(String name, String email, Long mobile, String password) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.role = "TBD";
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
