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
@WebServlet("/UpdatePasswd")
public class UpdatePasswd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePasswd() {
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
		System.out.println("测试");
		/**
		 * 接收原密码和flag
		 */
		//JSONObject json = JsonReader.receivePost(request);
		//String flag = json.getString("flag");
		String flag = request.getParameter("flag");
		int stuNum = Integer.parseInt(user.getStuId());
		JSONObject jsonObject=new JSONObject();
		System.out.println(flag);
		if(flag.equals("true")) {
			//String newPasswd = json.getString("newPasswd");
			String newPasswd= request.getParameter("newPasswd");
			if(new recUsers().updatePasswd(stuNum, newPasswd)) {
				jsonObject.put("newState", "true");
				out.write(jsonObject.toString());
			}else {
				jsonObject.put("newState", "false");
				out.write(jsonObject.toString());
			}
		}else if(flag.equals("false")){
			//String oldPasswd = json.getString("oldPasswd");
			String oldPasswd= request.getParameter("oldPasswd");
			if (new UserImpl().checkPasswd(stuNum, oldPasswd)) {
			
				jsonObject.put("state","true");
				out.write(jsonObject.toString());
			}else {
				jsonObject.put("state", "false");
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
