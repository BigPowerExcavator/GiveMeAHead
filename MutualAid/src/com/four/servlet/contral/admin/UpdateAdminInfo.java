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
import com.four.dao.impl.recUsers;
import com.four.javaBean.UserLoginBean;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdateAdminInfo
 */
@WebServlet("/UpdateAdminInfo")
public class UpdateAdminInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAdminInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		PrintWriter out = response.getWriter();
		/**
		 * 获取session里面的adminNum
		 */

		HttpSession session = request.getSession(true);
		// 获取session中的学号;
		String adminId = (String) session.getAttribute("adminInfo");
		/**
		 * 获取修改信息
		 */
		JSONObject json = JsonReader.receivePost(request);
		// 需要修改的学号
		String adminNum = json.getString("adminNum");
		String adminName = json.getString("adminName");
		String trueName=json.getString("trueName");
		String sex = json.getString("sex");
		String grade = json.getString("grade");
		String phone= json.getString("phone");
		JSONObject jsonObject = new JSONObject();
		if (new RecAdmin().ProveAdmin(adminNum)) {
			jsonObject.put("changeAdmin", "false");
			out.write(jsonObject.toString());
		} else {
			if (new RecAdmin().upadteAdminInfo(adminNum, adminName, trueName, sex, grade, phone, adminId)) {
				jsonObject.put("changeAdmin", "true");
				out.write(jsonObject.toString());
			} else {
				jsonObject.put("changeAdmin", "false");
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
