package com.four.servlet.contral.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.dao.impl.RecAdmin;
import com.four.javaBean.UserLoginBean;
import com.four.service.impl.UserImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminCenter
 */
@WebServlet("/AdminCenter")
public class AdminCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCenter() {
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
		 * ��ȡsession,�Ӷ���ø����û���Ϣ
		 */
		HttpSession session=request.getSession(false);
		/**
		 * ��ʽ��
		 * �ǵðѡ�1008����ΪadminNm
		 * 
		 */
		//String adminNum = (String) session.getAttribute("adminInfo");
		
		Map<String, Object> map =new RecAdmin().getAdminInfo("1008");
		
		//������Ϣ�������
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
