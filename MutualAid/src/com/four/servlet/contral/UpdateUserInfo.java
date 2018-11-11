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
import com.four.javaBean.UsersBean;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdateUsers
 */
@WebServlet("/UpdateUserInfo")
public class UpdateUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInfo() {
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
		 * 获取session里面的学号ID
		 */
		
		HttpSession session=request.getSession(true);
		UserLoginBean person = (UserLoginBean)session.getAttribute("userInfo");
		/**
		 * 获取修改信息
		 */
		JSONObject json=JsonReader.receivePost(request);
		System.out.println(json.toString());
		//获取session中的学号;
		int stuId=Integer.parseInt(person.getStuId());
		
		//需要修改的学号
		int stuNum =Integer.parseInt(json.getString("stuNum"));
		JSONObject jsonObject=new JSONObject();
		if(new recUsers().ProveUser(stuId)) {
			jsonObject.put("code", "用户不存在，无法修改");
			out.write(jsonObject.toString());
		}else {
			if(new recUsers().upadteInfo(stuNum,json.getString("userName"),
					json.getString("trueName"),json.getString("sex"),json.getString("dormitory"),json.getString("phone"),stuId)) {
				jsonObject.put("code", "修改信息成功");
				out.write(jsonObject.toString());
			}
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
