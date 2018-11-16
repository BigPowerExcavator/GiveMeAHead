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
 * Servlet implementation class UserCleanGoodImg
 */
@WebServlet("/UserCleanGoodImg")
public class UserCleanGoodImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCleanGoodImg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		String url = (String) session.getAttribute("imgUrl");
		Map<String, String> map=(Map<String, String>)session.getAttribute("urlMap");
		String[] urlSet=url.split("###");
		//删除刚上传的图片
		FileUpload upload=new FileUpload();
		for(int i=0;i<urlSet.length;i++) {
			String tempUrl=map.get(urlSet[i]);
			upload.deleteFile(tempUrl);
		}
		//删除session中临时存放的图片地址
				session.removeAttribute("imgUrl");
				session.removeAttribute("urlMap");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
