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
 * ���ڸ����û�����
 */
@WebServlet("/UpdateUserPasswd")
public class UpdateUserPasswd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserPasswd() {
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

		/** ������Ӧͷ����ajax������� **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		PrintWriter out = response.getWriter();

		HttpSession session=request.getSession();
		UserLoginBean user = (UserLoginBean)session.getAttribute("userInfo");

		/**
		 * ����ԭ����
		 */
		JSONObject json = JsonReader.receivePost(request);

		
		String flag = json.getString("flag");
		
		int stuNum = Integer.parseInt(user.getStuId());
		JSONObject jsonObject=new JSONObject();
		if(flag.equals("true")) {
			String newPasswd = json.getString("newPasswd");
			if(new recUsers().updatePasswd(stuNum, newPasswd)) {
				jsonObject.put("code", "�޸�����ɹ�");

				out.write(jsonObject.toString());
			}
		}else{
			String oldPasswd = json.getString("oldPasswd");
			if (new UserImpl().checkPasswd(stuNum, oldPasswd)) {
			
				jsonObject.put("flag", "true");
	
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
