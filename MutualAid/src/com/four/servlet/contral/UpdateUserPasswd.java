package com.four.servlet.contral;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.dao.impl.recUsers;
import com.four.javaBean.UserLoginBean;
import com.four.service.impl.UserImpl;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdatePasswd
 * 用于更新用户密码
 */
@WebServlet("/UpdateUserPasswd")
public class UpdateUserPasswd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserPasswd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		PrintWriter out = response.getWriter();

		HttpSession session=request.getSession();
		UserLoginBean user = (UserLoginBean)session.getAttribute("userInfo");

		/**
		 * 接收原密码
		 */
		JSONObject json = JsonReader.receivePost(request);

		
		String flag = json.getString("flag");
		
		int stuNum = Integer.parseInt(user.getStuId());
		JSONObject jsonObject=new JSONObject();
		if(flag.equals("true")) {
			String newPasswd = json.getString("newPasswd");
			if(new recUsers().updatePasswd(stuNum, newPasswd)) {
				jsonObject.put("code", "修改密码成功");

				out.write(jsonObject.toString());
			}
		}else{
			String oldPasswd = json.getString("oldPasswd");
			if (new UserImpl().checkPasswd(stuNum, oldPasswd)) {
			
				jsonObject.put("flag", "true");
	
				out.write(jsonObject.toString());
		}
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
