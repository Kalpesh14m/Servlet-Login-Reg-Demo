package com.bridgelabz.proxy;

import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.dto.LoginDTO;
import com.bridgelabz.model.User;
import com.bridgelabz.repo.UserService;
import com.bridgelabz.service.UserServiceProvider;

public class ProxyHelper implements UserService {

	private static final UserService us = new UserServiceProvider();

	@Override
	public boolean saveUser(User user) {
		return us.saveUser(user);
	}

	@Override
	public boolean findUserByEmailAndPassword(LoginDTO request) {
		return us.findUserByEmailAndPassword(request);
	}

	@Override
	public User getUserByEmailAndPassword(LoginDTO request) {
		User user = us.getUserByEmailAndPassword(request);
		return user;
	}

	@Override
	public boolean checkEmailExist(String email) {
		return us.checkEmailExist(email);
	}

	@Override
	public List<User> getAllUserDetails(int start, int total, User user) {

		List<User> users = new ArrayList<User>();
		if (user.getRole().equalsIgnoreCase("admin"))
			users = us.getAllUserDetails(start, total, user);
		else
			users.add(user);
		return users;
	}

}
