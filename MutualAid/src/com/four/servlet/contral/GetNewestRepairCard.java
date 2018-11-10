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
 * Servlet implementation class GetNewestRepairCard
 * �������µı��޿�Ƭ
 */
@WebServlet("/GetNewestRepairCard")
public class GetNewestRepairCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNewestRepairCard() {
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
		HttpSession session=request.getSession(false);
		//��session��ȡ�û���Ϣ
		UserLoginBean user=(UserLoginBean)session.getAttribute("userInfo");
		String stuNum=user.getStuId();
		//�õ����µ�δ��ɵı��޿�
		Map<String, String> map=new HashMap<String ,String>();
		map=new CenterGetCards().getNewestRepairCard(stuNum);
		JSONObject jsonObject=new JSONObject();
		jsonObject=JSONObject.fromObject(map);
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
