package com.bridgelabz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bridgelabz.dto.LoginDTO;
import com.bridgelabz.model.User;
import com.bridgelabz.proxy.ProxyHelper;
import com.bridgelabz.repo.UserService;
import com.bridgelabz.utilz.Utility;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = Utility.encryption(request.getParameter("password"));
		LoginDTO req = new LoginDTO(email, password);
		UserService rp = new ProxyHelper();

		if (email == "" || password == "") {
			out.println("<h2 style=\"color: red;\">*All Fields are required!</h2>");
			request.getRequestDispatcher("login.html").include(request, response);
		}

		else if (rp.findUserByEmailAndPassword(req)) {
			HttpSession session = request.getSession();
			User user = rp.getUserByEmailAndPassword(req);
			session.setAttribute("user", user);
			response.sendRedirect("home");
		}

		else {
			out.print("<h2 style = \"color:red;\">*Email or Password doesn't match!!!!</h3>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
	}

}
