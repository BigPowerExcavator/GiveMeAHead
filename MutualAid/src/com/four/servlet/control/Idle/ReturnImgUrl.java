package com.four.servlet.control.Idle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ReturnImgUrl
 */
@WebServlet("/ReturnImgUrl")
public class ReturnImgUrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnImgUrl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//配置字符编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		response.setContentType("text/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		//使用文件上传组件
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
		try {
			//解析请求信息
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list) {
				//处理上传文件的保存名称
				String imgUrl = item.getName();
				System.out.println(imgUrl);
				JSONObject jsonObject=new JSONObject();
				if(imgUrl!=null) {
					
					jsonObject.put("url", imgUrl);
					out.write(jsonObject.toString());
				}else {
					jsonObject.put("url","null");
					out.write(jsonObject.toString());
				}
			}
			}catch(Exception e) {
					e.printStackTrace();		
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
