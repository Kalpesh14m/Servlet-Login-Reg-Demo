package com.bridgelabz.repo;

import java.util.List;

import com.bridgelabz.dto.LoginDTO;
import com.bridgelabz.model.User;

public interface UserService {

	public boolean saveUser(User user);

	public boolean findUserByEmailAndPassword(LoginDTO request);

	public User getUserByEmailAndPassword(LoginDTO request);

	public boolean checkEmailExist(String email);

	public List<User> getAllUserDetails(int start, int total, User user);

}
