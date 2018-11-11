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
		
		/** ������Ӧͷ����ajax������� **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		
		PrintWriter out = response.getWriter();
		/**
		 * ��ȡsession�����ѧ��ID
		 */
		
		HttpSession session=request.getSession(true);
		UserLoginBean person = (UserLoginBean)session.getAttribute("userInfo");
		/**
		 * ��ȡ�޸���Ϣ
		 */
		JSONObject json=JsonReader.receivePost(request);
		System.out.println(json.toString());
		//��ȡsession�е�ѧ��;
		int stuId=Integer.parseInt(person.getStuId());
		
		//��Ҫ�޸ĵ�ѧ��
		int stuNum =Integer.parseInt(json.getString("stuNum"));
		JSONObject jsonObject=new JSONObject();
		if(new recUsers().ProveUser(stuId)) {
			jsonObject.put("code", "�û������ڣ��޷��޸�");
			out.write(jsonObject.toString());
		}else {
			if(new recUsers().upadteInfo(stuNum,json.getString("userName"),
					json.getString("trueName"),json.getString("sex"),json.getString("dormitory"),json.getString("phone"),stuId)) {
				jsonObject.put("code", "�޸���Ϣ�ɹ�");
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
