package com.four.servlet.control.Idle;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.service.impl.FileUpload;

/**
 * Servlet implementation class UserDeleteGoodImg
 */
@WebServlet("/UserDeleteGoodImg")
public class UserDeleteGoodImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteGoodImg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		response.setContentType("text/json;charset=utf-8");
		
		String deleteUrl=request.getParameter("src");
		HttpSession session=request.getSession(false);
		String url = (String) session.getAttribute("imgUrl");
		String[] urlSet=url.split("###");
		String tempUrl="";
		//删除imgUrl中的对应URL
		for(int i=0;i<urlSet.length;i++) {
			if((deleteUrl).equals(urlSet[i])) {
				continue;
			}else {
				if(i!=0)tempUrl+="###";
				tempUrl+=urlSet[i];
			}
		}
		Map<String, String> map=(Map<String, String>)session.getAttribute("urlMap");
		String allUrl=map.get(deleteUrl);
		map.remove(deleteUrl);
		session.setAttribute("imgUrl", tempUrl);
		session.setAttribute("urlMap", map);
		new FileUpload().deleteFile(allUrl);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
