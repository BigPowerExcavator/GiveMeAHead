package com.four.servlet.contral.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.four.service.impl.AdminGetCardService;
import com.four.util.JsonReader;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminCetRepairCards
 */
@WebServlet("/AdminCetRepairCards")
public class AdminCetRepairCards extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCetRepairCards() {
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
		AdminGetCardService adminGetRepairCard=new AdminGetCardService();
		ArrayList<Map<String, String>> cards=new ArrayList<Map<String, String>>();
		/*JSONObject json=JsonReader.receivePost(request);
		String state=json.getString("state");*/
		String state=request.getParameter("state");
		cards.addAll(adminGetRepairCard.getRepairCards(state));
		Map<String,Object> map=adminGetRepairCard.sortAllCards(cards);
		JSONObject jsonObject=new JSONObject();
		jsonObject=JSONObject.fromObject(map);
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
