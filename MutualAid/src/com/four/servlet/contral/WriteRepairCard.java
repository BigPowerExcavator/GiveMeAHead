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
 * 用于用户新建报修表
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
		
		/** 设置响应头允许ajax跨域访问 **/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/* 星号表示所有的异域请求都可以接受， */
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		PrintWriter out = response.getWriter();
		JSONObject json=JsonReader.receivePost(request);
		//把json里的信息换成javaBean
		RepairBean repair=(RepairBean)json.toBean(json, RepairBean.class);
		System.out.println(json.toString());
		HttpSession session=request.getSession(false);
		//从session获取用户信息
		UserLoginBean user=(UserLoginBean)session.getAttribute("userInfo");
		String stuNum=user.getStuId();
		//从session中获取用户学号，并存入javaBean中
		repair.setStuNum(stuNum);
		//System.out.println("12365   "+stuNum);
		RepairImp repairImp=new RepairImp();
		//创建发送json的格式
		JsonBean jsonBean=new JsonBean();
		JSONObject jsonObject=new JSONObject();
		//调用dao方法，写入数据库
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
