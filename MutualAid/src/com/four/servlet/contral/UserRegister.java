package com.four.servlet.contral;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.four.javaBean.UserLoginBean;
import com.four.javaBean.UserRegisterBean;
import com.four.service.impl.UserImpl;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserRegister
 * �����û�ע������
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8" );
		
		/** ������Ӧͷ����ajax������� **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		PrintWriter out = response.getWriter();
		JSONObject json=JsonReader.receivePost(request);
		UserRegisterBean user=(UserRegisterBean)json.toBean(json, UserRegisterBean.class);
		
		//��ȡsession�д��ŵ���֤��
		HttpSession session=request.getSession(true);
		String codeString=(String)session.getAttribute("codeString");
		//����json
		JSONObject jsonObject=new JSONObject() ;
		if(codeString!=null&&user.getCode().equals(codeString)) {
			UserImpl userRegister=new UserImpl();
			if(userRegister.cheakRegisterRename(user.getName())) {
				jsonObject.put("status", "1102");
			}else {
				if(userRegister.userRegister(user)) {
					jsonObject.put("status", "1101");
				}else {
					jsonObject.put("status", "1103");
				}
			}
		}else {
			jsonObject.put("status", "1104");
		}
		System.out.println(jsonObject.get("status"));
		out.write(jsonObject.toString());
	}
	
}
