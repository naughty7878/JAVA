package com.test.sso.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.sso.util.SSOUtil;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String redirectUrl = request.getParameter("redirectUrl");
		if(SSOUtil.doLogin(username, password)) {
//			Cookie cookie = new Cookie("sso", "123456");
//			// 设置到父域中，所有子域可以使用cookie
//			cookie.setDomain("x.com");
//			// cookie设置的域的最顶层
//			cookie.setPath("/");
//			response.addCookie(cookie);
//			response.sendRedirect(redirectUrl);
			
			response.sendRedirect(redirectUrl + "?cname=sso&cval=123456");
		}else {

			//这句话的意思，是让浏览器用utf8来解析返回的数据  
			response.setHeader("Content-type", "text/html;charset=UTF-8");  
			//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("登录失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
