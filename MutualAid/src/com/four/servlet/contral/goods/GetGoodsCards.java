package com.four.servlet.contral.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.four.service.impl.GoodsManageService;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetGoodsCards
 */
@WebServlet("/GetGoodsCards")
public class GetGoodsCards extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGoodsCards() {
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
		
		String type=request.getParameter("type");
		String sort=request.getParameter("sort");
		String beginNum="0";
		GoodsManageService goodsManageService=new GoodsManageService();
		Map<String, Object> cards=goodsManageService.getGoodsCard(beginNum, "12", type, sort);
		cards.put("count", goodsManageService.getLimitCardsCount(type, sort));
		
		System.out.println(cards.toString());
		
		PrintWriter out = response.getWriter();
		JSONObject jsonObject=new JSONObject();
		jsonObject=JSONObject.fromObject(cards);
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
