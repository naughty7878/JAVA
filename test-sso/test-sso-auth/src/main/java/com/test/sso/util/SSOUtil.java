package com.test.sso.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SSOUtil {

	public static String USERNAME = "admin";
	public static String PASSWORD = "123456";

	public static boolean doLogin(String username, String password) {
		if (USERNAME.equals(username) && PASSWORD.equals(password)) {
			return true;
		}
		return false;
	}

	public static boolean checkLogin(String username, String password) {

		if ("sso".equals(username) && "123456".equals(password)) {
			return true;
		}
		return false;
	}
}
