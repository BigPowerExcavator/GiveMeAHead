package com.four.servlet.contral.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.four.javaBean.GoodBean;
import com.four.service.impl.GoodsManageService;
import com.four.util.GetTrueGoodType;
import com.four.util.StringToTime;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetGoodCardInfo
 */
@WebServlet("/GetGoodCardInfo")
public class GetGoodCardInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGoodCardInfo() {
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
		
		String formId=request.getParameter("formId");
		System.out.println(formId);
		GoodsManageService goodsManageService=new GoodsManageService();
		GoodBean goodBean=goodsManageService.getGoodInfo(formId);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("title", goodBean.getTitle());
		map.put("price", goodBean.getGoodsPrice());
		map.put("type", new GetTrueGoodType().getGoodType(goodBean.getGoodsType()));
		map.put("content", goodBean.getGoodsIntro());
		map.put("userName", goodBean.getUserName());
		map.put("time", new StringToTime().GetTime(goodBean.getTime(), "yyyy-MM-dd"));
		map.put("formId", formId);
		map.put("phone", goodBean.getPhone());
		String[] imgSet=goodBean.getGoodsImg().split("###");
		//Map<String, String>imgMap=new HashMap<String,String>();
		for(int i=0;i<imgSet.length;i++) {
			//imgMap.put("img"+(i+1), imgSet[i]);
			map.put("img"+(i+1), imgSet[i]);
		}
		//map.put("img", imgMap);
		map.put("count", imgSet.length);
		JSONObject jsonObject=new JSONObject();
		jsonObject=JSONObject.fromObject(map);
		PrintWriter out = response.getWriter();
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
