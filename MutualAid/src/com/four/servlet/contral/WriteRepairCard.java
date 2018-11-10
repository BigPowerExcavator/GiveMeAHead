package com.four.servlet.contral;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.javaBean.JsonBean;
import com.four.javaBean.UserLoginBean;
import com.four.javaBean.RepairBean;
import com.four.service.impl.RepairImp;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class WriteRepairCard
 * �����û��½����ޱ�
 */
@WebServlet("/WriteRepairCard")
public class WriteRepairCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteRepairCard() {
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
		
		/** ������Ӧͷ����ajax������� **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		PrintWriter out = response.getWriter();
		JSONObject json=JsonReader.receivePost(request);
		//��json�����Ϣ����javaBean
		RepairBean repair=(RepairBean)json.toBean(json, RepairBean.class);
		System.out.println(json.toString());
		HttpSession session=request.getSession(false);
		//��session��ȡ�û���Ϣ
		UserLoginBean user=(UserLoginBean)session.getAttribute("userInfo");
		String stuNum=user.getStuId();
		//��session�л�ȡ�û�ѧ�ţ�������javaBean��
		repair.setStuNum(stuNum);
		//System.out.println("12365   "+stuNum);
		RepairImp repairImp=new RepairImp();
		//��������json�ĸ�ʽ
		JsonBean jsonBean=new JsonBean();
		JSONObject jsonObject=new JSONObject();
		//����dao������д�����ݿ�
		if(repairImp.writeRepairCard(repair)) {
			jsonBean.setStatus("1301");
			jsonObject=JSONObject.fromObject(jsonBean);
			System.out.println(jsonObject);
		}else {
			jsonBean.setStatus("1302");
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
