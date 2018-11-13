package com.four.servlet.contral.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.dao.impl.RecAdmin;
import com.four.javaBean.UserLoginBean;
import com.four.service.impl.UserImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminCenter
 */
@WebServlet("/AdminCenter")
public class AdminCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCenter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8" );
		
		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		PrintWriter out = response.getWriter();
		/**
		 * 获取session,从而获得个人用户信息
		 */
		HttpSession session=request.getSession(false);
		/**
		 * 调式用
		 * 记得把“1008”改为adminNm
		 * 
		 */
		//String adminNum = (String) session.getAttribute("adminInfo");
		
		Map<String, Object> map =new RecAdmin().getAdminInfo("1008");
		
		//发送信息给浏览器
		JSONObject jsonObject=new JSONObject();
		jsonObject.putAll(map);
		out.write(jsonObject.toString());
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
