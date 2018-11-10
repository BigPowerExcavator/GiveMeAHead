package com.four.servlet.contral;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.javaBean.UserLoginBean;
import com.four.service.impl.CenterGetCards;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetPersonalCenterCard
 * 用于用户获得自己的所有卡片
 */
@WebServlet("/GetPersonalCenterCard")
public class GetPersonalCenterCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPersonalCenterCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8" );
		PrintWriter out = response.getWriter();
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		HttpSession session=request.getSession(false);
		//从session中获取个人信息
		UserLoginBean user=(UserLoginBean)session.getAttribute("userInfo");
		//获取个人的所有卡片
		Map<String, Object> map=new CenterGetCards().getAllCards(user);
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
