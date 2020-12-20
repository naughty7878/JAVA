package com.test.sso.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.sso.util.HttpConnectionUtils;

/**
 * Servlet implementation class HomeServlet
 */
public class Home2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 认证中心回调，验证
		boolean checkSSO = checkSSO(request, response);
		boolean checkLogin = checkLogin(request);
		
		if(checkLogin || checkSSO) {
			// 登录-跳转到主页
			request.getRequestDispatcher("home2.jsp").forward(request, response);
		}else {
			// 未登录-跳转的登录界面
			String redirectUrl = "http://demo2.b.com:8080/test-sso2/home2";
			
//			request.getSession().setAttribute("redirectUrl", redirectUrl);
//			request.getRequestDispatcher("login.jsp").forward(request, response);
			response.sendRedirect("http://check.x.com:8080/test-sso-auth/login.jsp?redirectUrl=" + URLEncoder.encode(redirectUrl, "UTF-8"));
		}
	}
	
	private boolean checkLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				
				String method = HttpConnectionUtils.getMethod("http://check.x.com:8080/test-sso-auth/check?" + "username=" +cookie.getName() + "&password=" + cookie.getValue());
				if("true".equals(method)) {
					request.getSession().setAttribute("canme", cookie.getName());
					request.getSession().setAttribute("cval", cookie.getValue());
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkSSO(HttpServletRequest request, HttpServletResponse response) {
		String cname = request.getParameter("cname");
		String cval = request.getParameter("cval");
		if(cname != null && cname.length() > 0 && cval != null && cval.length() > 0) {
			// 验证
			String method = HttpConnectionUtils.getMethod("http://check.x.com:8080/test-sso-auth/check?" + "username=" +cname + "&password=" + cval);
			if("true".equals(method)) {
				request.getSession().setAttribute("canme", cname);
				request.getSession().setAttribute("cval", cval);
				
				Cookie cookie = new Cookie(cname, cval);
				// cookie设置的域的最顶层
				cookie.setPath("/");
				response.addCookie(cookie);
				return true;
			}
		}
		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
