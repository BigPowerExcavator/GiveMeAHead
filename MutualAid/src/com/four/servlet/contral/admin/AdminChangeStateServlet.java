package com.four.servlet.contral.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.four.dao.impl.RepairFormImp;
import com.four.javaBean.JsonBean;
import com.four.service.impl.RepairImp;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminChangeStateServlet
 */
@WebServlet("/AdminChangeStateServlet")
public class AdminChangeStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChangeStateServlet() {
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
		//接受浏览器发来的信息
		//JSONObject json=JsonReader.receivePost(request);
		String formId=(String)request.getParameter("formId");
		String state=(String)request.getParameter("state");
		String afterState=(String)request.getParameter("afterState");
		//检查操作规范并修改数据库
		int result=new RepairImp().checkRepairState(state, afterState, formId);
		System.out.println(result);
		/*switch (result) {
		case 0:
			jsonObject.put("status", "1502");
			jsonObject.put("result", "数据库连接错误");
			break;
		case 1:
			jsonObject.put("status", "1501");
			jsonObject.put("result", "修改成功");
			break;
		case 2:
			jsonObject.put("status", "1503");
			jsonObject.put("result", "操作不规范");
			break;
		default:
			break;
		}
		out.write(jsonObject.toString());*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
