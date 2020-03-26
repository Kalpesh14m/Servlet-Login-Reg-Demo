package com.bridgelabz.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ActiveUserCountListener implements HttpSessionListener {

	private static int activeUsers;

	public ActiveUserCountListener() {
	}

	public static int getActiveUsers() {
		return activeUsers;
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		activeUsers++;
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		activeUsers--;
	}

}
