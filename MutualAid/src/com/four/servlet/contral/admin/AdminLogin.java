package com.four.servlet.contral.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.dao.impl.RecAdmin;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * @author jinner
		 * 
		 * 配置信息
		 */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8" );
		
		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		PrintWriter out = response.getWriter();
		
		//接受用户名和密码
		//JSONObject json=JsonReader.receivePost(request);
		//String adminNum = json.getString("adminNum");
		//String passwd = json.getString("passwd");
		String adminNum = request.getParameter("adminNum");
		String passwd = request.getParameter("pwd");
		JSONObject jsonObject=new JSONObject();
		if(new RecAdmin().LoginIn(adminNum,passwd)) {
			//验证成功  把adminNum放入session
			HttpSession session = request.getSession(true);
			session.setAttribute("adminInfo",adminNum);
			jsonObject.put("status", 1001);
			out.write(jsonObject.toString());
		}else {
			jsonObject.put("status", 1002);
			out.write(jsonObject.toString());
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
