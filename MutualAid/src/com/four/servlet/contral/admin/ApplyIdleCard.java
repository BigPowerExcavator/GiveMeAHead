package com.four.servlet.contral.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.dao.impl.RecGoods;
import com.four.dao.impl.recUsers;
import com.four.javaBean.UserLoginBean;
import com.four.util.GetTime;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ApplyIdleCard
 */
@WebServlet("/ApplyIdleCard")
public class ApplyIdleCard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplyIdleCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//配置信息
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8" );
		PrintWriter out = response.getWriter();
		//接收session的学号，判断哪位学生
		HttpSession session=request.getSession(false);
		UserLoginBean person = (UserLoginBean)session.getAttribute("userInfo");
		String stuNum = person.getStuId();
		
		//获取图片的总url
		String imgUrl = (String)session.getAttribute("imgUrl");
		/**
		 * 接收申请闲置的内容
		 * 
		 * @parm  goodsName
		 * 
		 * @parm  goodsType
		 * @parm  goodsPrice
		 * @parm  title
		 * @parm  time
		 * @parm  goodsIntro
		 * @parm  userName
		 */
		
		String goodsName = request.getParameter("title");
		System.out.println(goodsName);
		//类型
		String goodsType = request.getParameter("type");
		//价格
		String goodsPrice = request.getParameter("price");
		//标题
		String title = request.getParameter("title");
		System.out.println(title);
		//时间---获取当前系统时间
		String time = new GetTime().getTime();
		//详细信息
		String goodsIntro = request.getParameter("content");
		//电话
		String phone =request.getParameter("tel");
		//根据学号获取用户名
		String userName = new recUsers().getName(stuNum);
		JSONObject jsonObject=new JSONObject();
		System.out.println("这里被调用了");
		if(new RecGoods().ApplyIdleCard(stuNum, goodsName, imgUrl, goodsType, goodsPrice, title, time, goodsIntro, phone, userName)) {
				jsonObject.put("status",true);
				out.write(jsonObject.toString());
		}else {
				jsonObject.put("status",false);
				out.write(jsonObject.toString());
		}
		//删除session中临时存放的图片地址
		session.removeAttribute("imgUrl");
		session.removeAttribute("urlMap");
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
