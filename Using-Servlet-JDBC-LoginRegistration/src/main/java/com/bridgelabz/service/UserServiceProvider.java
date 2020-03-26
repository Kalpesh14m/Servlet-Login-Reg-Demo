package com.bridgelabz.service;

import java.util.List;

import com.bridgelabz.dto.LoginDTO;
import com.bridgelabz.model.User;
import com.bridgelabz.repo.RepositoryProvider;
import com.bridgelabz.repo.UserService;

public class UserServiceProvider implements UserService {

	private static final RepositoryProvider repo = new RepositoryProvider();

	@Override
	public boolean saveUser(User user) {
		if (repo.saveUser(user) > 0)
			return true;
		return false;
	}

	@Override
	public boolean findUserByEmailAndPassword(LoginDTO request) {
		if (repo.findUserByEmailAndPassword(request))
			return true;
		return false;
	}

	@Override
	public User getUserByEmailAndPassword(LoginDTO request) {
		User user = repo.getUserByEmailAndPassword(request);
		return user;
	}

	@Override
	public boolean checkEmailExist(String email) {
		if (repo.checkEmailExist(email))
			return true;
		return false;
	}

	@Override
	public List<User> getAllUserDetails(int start, int total, User user) {
		List<User> users = repo.getAllUserDetails(start, total);
		return users;
	}

}
