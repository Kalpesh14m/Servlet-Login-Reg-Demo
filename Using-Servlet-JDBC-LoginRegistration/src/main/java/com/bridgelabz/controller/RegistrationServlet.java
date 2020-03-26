package com.bridgelabz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.model.User;
import com.bridgelabz.proxy.ProxyHelper;
import com.bridgelabz.repo.UserService;
import com.bridgelabz.utilz.Utility;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		Long mobile = Long.parseLong(request.getParameter("mobile"));
		String password = request.getParameter("password");
		password = Utility.encryption(password);
		UserService u = new ProxyHelper();

		User user = new User(name, email, mobile, password);

		if (name == "" || email == "" || password == "") {
			out.println("<h2 style=\"color: red;\">*All Fields are required!</h2>");
			request.getRequestDispatcher("registration.html").include(request, response);
		}

		else if (u.checkEmailExist(email)) {
			out.println("<h2 style=\"color: red;\">*Email already have been registered!</h2>");
			request.getRequestDispatcher("registration.html").include(request, response);
		} else if (u.saveUser(user)) {
			out.print("<h3 style =\"color: green;\">User Detail have been saved successfully.....");
			request.getRequestDispatcher("login.html").include(request, response);
		} else {
			out.print("<h3 style =\"color: red;\">Unable save user details!!!!!");
			request.getRequestDispatcher("registration.html").include(request, response);
		}

	}
}
