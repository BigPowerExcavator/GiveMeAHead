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
		//������Ϣ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8" );
		PrintWriter out = response.getWriter();
		//����session��ѧ�ţ��ж���λѧ��
		HttpSession session=request.getSession(false);
		UserLoginBean person = (UserLoginBean)session.getAttribute("userInfo");
		String stuNum = person.getStuId();
		
		//��ȡͼƬ����url
		String imgUrl = (String)session.getAttribute("imgUrl");
		/**
		 * �����������õ�����
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
		//����
		String goodsType = request.getParameter("type");
		//�۸�
		String goodsPrice = request.getParameter("price");
		//����
		String title = request.getParameter("title");
		System.out.println(title);
		//ʱ��---��ȡ��ǰϵͳʱ��
		String time = new GetTime().getTime();
		//��ϸ��Ϣ
		String goodsIntro = request.getParameter("content");
		//�绰
		String phone =request.getParameter("tel");
		//����ѧ�Ż�ȡ�û���
		String userName = new recUsers().getName(stuNum);
		JSONObject jsonObject=new JSONObject();
		if(new RecGoods().ApplyIdleCard(stuNum, goodsName, imgUrl, goodsType, goodsPrice, title, time, goodsIntro, phone, userName)) {
				jsonObject.put("status",true);
				out.write(jsonObject.toString());
		}else {
				jsonObject.put("status",false);
				out.write(jsonObject.toString());
		}
		//ɾ��session����ʱ��ŵ�ͼƬ��ַ
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
