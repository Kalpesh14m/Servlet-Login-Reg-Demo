package com.bridgelabz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bridgelabz.model.User;
import com.bridgelabz.proxy.ProxyHelper;
import com.bridgelabz.repo.UserService;

public class DisplayAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		UserService rp = new ProxyHelper();
		User user = (User) session.getAttribute("user");

		if (session == null || user == null) {
			response.sendRedirect("login.html");
		} else {

			String pageNo = request.getParameter("page");
			int pageid = Integer.parseInt(pageNo);
			int total = 2;
			if (pageid == 1) {
			} else {
				pageid = pageid - 1;
				pageid = pageid * total + 1;
			}
			List<User> users = rp.getAllUserDetails(pageid, total, user);

			if (users != null) {
				out.print("<html>" + "<head>" + "<style>" + "</style>" + "<link rel=\"stylesheet\" href=\"style.css\">"
						+ "</head>" + "<body>" + "<table class=\"content-table\">"
						+ "<tr><td colspan=\"4\"><h1>List of All Users</h1></td></tr>"
						+ "<tr><th>Name:</th><th>Email:</th><th>Mobile:</th></tr>");

				for (User usr : users) {
					out.print("<tr>" + "<td>" + usr.getName() + "</td>" + "<td>" + usr.getEmail() + "</td>" + "<td>"
							+ usr.getMobile() + "</td></tr>");
				}
				out.print("<tr><td><a href=\"display?page=1\">1</a></td>");
				out.print("<td><a href=\"display?page=2\">2</a></td>");
				out.print("<td><a href=\"display?page=3\">3</a></td></tr>");
				out.print("<tr><td><a href=\"logout\">Logout</a></td></tr>");
				out.print("</table>");

				out.print("</body>" + "</html>");
			}
		}
	}

}
