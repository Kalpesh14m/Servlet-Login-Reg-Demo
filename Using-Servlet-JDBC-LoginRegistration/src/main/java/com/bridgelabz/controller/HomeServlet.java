package com.bridgelabz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bridgelabz.model.User;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		if (session != null || session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			out.print("<html>" + "<head>" + "<meta http-equiv=\"refresh\" content=\"10\">"
					+ "<link rel=\"stylesheet\" href=\"style.css\">" + "</head>" + "<body>"
					+ "<table class=\"content-table\">"
					+ "<tr><th colspan=\"2\"><h1>Welcome to User Profile</h1></th></tr>" + "<tr><td>Welcome:</td><td>"
					+ user.getName() + "</td></tr>" + "<tr><td>Email:</td><td>" + user.getEmail() + "</td></tr>"
					+ "<tr><td>Mobile:</td><td>" + user.getMobile() + "</td></tr>"
					+ "<tr><td><a href=\"logout\">Logout</a></td>"
					+ "<td><a href=\"display?page=1\">Display All users</a></td></tr>" + "</table>" + "</body>"
					+ "</html>");
		} else {
			response.sendRedirect("index.html");
		}
	}

}
