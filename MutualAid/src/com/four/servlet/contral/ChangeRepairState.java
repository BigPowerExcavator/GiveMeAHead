package com.four.servlet.contral;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.four.dao.impl.RepairFormImp;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChangeRepairState
 */
@WebServlet("/ChangeRepairState")
public class ChangeRepairState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeRepairState() {
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
		
		JSONObject json=JsonReader.receivePost(request);
		PrintWriter out = response.getWriter();
		String state=(String)json.get("formId");
		String formId=(String)json.get("formId");
		String id=(String)json.get("id");
		JSONObject jsonObject=new JSONObject();
		if(new RepairFormImp().changeState(formId, state)) {
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
