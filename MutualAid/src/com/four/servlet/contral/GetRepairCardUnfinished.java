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

import com.four.javaBean.JsonBean;
import com.four.javaBean.UserLoginBean;
import com.four.service.impl.RepairImp;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetRepairCard
 * 得到所有未完成的报修表
 */
@WebServlet("/GetRepairCardUnfinished")
public class GetRepairCardUnfinished extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRepairCardUnfinished() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* 星号表示所有的异域请求都可以接受， */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8" );
		PrintWriter out = response.getWriter();
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		HttpSession session=request.getSession(false);
		UserLoginBean user=(UserLoginBean)session.getAttribute("userInfo");
		Map<String, Object> map=new  RepairImp().getCardJsonUnfinished(user);
		if(map==null) {
			map.put("status" ,"1402");
		}else {
			map.put("status" ,"1401");
		}
		Map<String, Object> sendMap=new HashMap<>();
		sendMap.put("data", map);
		JSONObject jsonObject=JSONObject.fromObject(sendMap);
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
