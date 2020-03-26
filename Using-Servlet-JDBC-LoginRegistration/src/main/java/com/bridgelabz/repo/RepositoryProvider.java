package com.bridgelabz.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.config.ConnectionConfig;
import com.bridgelabz.dto.LoginDTO;
import com.bridgelabz.model.User;

public class RepositoryProvider {

	private Connection con = ConnectionConfig.getInstance().getConnection();

	public int saveUser(User user) {
		int i = 0;
		try {
			String sql = "insert into user(user_name,email,mobile,role,password) values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setLong(3, user.getMobile());
			pstmt.setString(4, user.getRole());
			pstmt.setString(5, user.getPassword());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public boolean findUserByEmailAndPassword(LoginDTO request) {
		String sql = "select * from user where email = ? and password = ?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, request.getEmail());
			preparedStatement.setString(2, request.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}

	public User getUserByEmailAndPassword(LoginDTO request) {
		String sql = "select * from user where email = ? and password = ?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, request.getEmail());
			preparedStatement.setString(2, request.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setName(rs.getString(1));
				user.setEmail(rs.getString(2));
				user.setMobile(rs.getLong(3));
				user.setRole(rs.getString(4));
				user.setPassword(rs.getString(5));
				return user;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}

	public boolean checkEmailExist(String email) {
		String sql = "select * from user where email = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public List<User> getAllUserDetails(int start, int total) {
		String sql = "select * from user limit " + (start - 1) + "," + total;
		List<User> users = new ArrayList<User>();
		try (ResultSet rs = con.prepareStatement(sql).executeQuery()) {
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString(1));
				user.setEmail(rs.getString(2));
				user.setMobile(rs.getLong(3));
				user.setRole(rs.getString(4));
				user.setPassword(rs.getString(5));
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}
}
