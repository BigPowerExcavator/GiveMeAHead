package com.four.servlet.contral;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.javaBean.UserLoginBean;

/**
 * Servlet implementation class UserOutServlet
 */
@WebServlet("/UserOutServlet")
public class UserOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		Set<String> userSet=(Set<String>)application.getAttribute("userList");
		HttpSession session=request.getSession(false);
		UserLoginBean user=(UserLoginBean)session.getAttribute("userInfo");
		userSet.remove(user.getStuId());
		//从session中获取用户信息
		session.removeAttribute("userInfo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
