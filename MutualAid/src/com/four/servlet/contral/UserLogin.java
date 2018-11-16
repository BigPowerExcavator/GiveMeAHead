package com.four.servlet.contral;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.javaBean.JsonBean;
import com.four.javaBean.UserLoginBean;
import com.four.service.impl.UserImpl;
import com.four.util.JsonReader;
import com.lxj.TestSend;


import net.sf.json.JSONObject;

/**
 * Servlet implementation class DoMain
 * �����û���¼
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * @author GEI LI JIAN
		 * @description:
		 */
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8" );
		
		/** ������Ӧͷ����ajax������� **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		PrintWriter out = response.getWriter();
		//�����������������Ϣ
		JSONObject json=JsonReader.receivePost(request);
		UserLoginBean user=(UserLoginBean)json.toBean(json, UserLoginBean.class);
		UserImpl checkUser=new UserImpl();
		JsonBean jsonBean=new JsonBean();
		JSONObject jsonObject=new JSONObject();
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		Set<String> userList=(Set<String>)application.getAttribute("userList");
		if(userList==null) {
			userList=new HashSet<String>();
		}
		if(checkUser.checkLogin(user)&&!userList.contains(user.getStuId())) {
			jsonBean.setStatus("1001");
			//jsonBean.setData(new TestSend().test());
			jsonObject=JSONObject.fromObject(jsonBean);
			
			System.out.println(jsonObject);
			//jsonObject.put("status", "1001");
			//�����¼�ɹ����Ͱ��û���ѧ�ź��������session��
			HttpSession session=request.getSession(true);
			session.setAttribute("userInfo", user);
			userList.add(user.getStuId());
			application.setAttribute("userList", userList);
		}else {
			jsonBean.setStatus("1002");
			jsonObject=JSONObject.fromObject(jsonBean);
			System.out.println(jsonObject);
			//jsonObject.put("status", "1002");
		}
		
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
