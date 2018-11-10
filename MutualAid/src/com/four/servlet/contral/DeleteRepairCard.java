package com.four.servlet.contral;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.dao.impl.RepairFormImp;
import com.four.javaBean.UserLoginBean;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteRepairCard
 * ����ɾ���û��ı��ޱ�
 */
@WebServlet("/DeleteRepairCard")
public class DeleteRepairCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRepairCard() {
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
		//��session�л�ȡ�û���Ϣ
		UserLoginBean user=(UserLoginBean)session.getAttribute("userInfo");
		JSONObject json=JsonReader.receivePost(request);
		//�������л�ȡformId����֪Ҫɾ���ĸ���
		String formId=(String)json.get("formId");
		JSONObject jsonObject=new JSONObject();
		//ɾ����
		if(new RepairFormImp().deleteRepairForm(user, formId)) {
			jsonObject.put("", "");
		}else {
			jsonObject.put("", "");
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
