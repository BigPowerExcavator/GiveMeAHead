package com.four.servlet.control.Idle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Base64.Decoder;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.four.service.impl.FileUpload;
import com.four.util.JsonReader;

import Decoder.BASE64Decoder;
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
		//�����ַ�����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		response.setContentType("text/json;charset=utf-8");
		//PrintWriter out = response.getWriter();
				
		//�洢·��
		String savePath = this.getServletConfig().getServletContext().getRealPath("/img/goods");
		System.out.println(savePath);
		//�洢ͼƬ
		FileUpload upload=new FileUpload();
		String imgUrl=upload.saveFile(savePath,request);
		System.out.println(imgUrl);
		imgUrl=request.getContextPath() + "/img/goods/" + imgUrl;
		HttpSession session=request.getSession(false);
		String url = (String) session.getAttribute("imgUrl");
		Map<String, String> map=(Map<String, String>)session.getAttribute("urlMap");
		if(url==null || url.equals("")) {
			session.setAttribute("imgUrl",imgUrl);
			map=new HashMap<String,String>();
			map.put(imgUrl, savePath+"\\"+imgUrl);
			session.setAttribute("urlMap", map);
		}else {
			 imgUrl=url+"###"+imgUrl;
			 session.removeAttribute("imgUrl");
			 session.setAttribute("imgUrl",imgUrl);
			 map.put(imgUrl, savePath+"\\"+imgUrl);
			 session.setAttribute("urlMap", map);
			 System.out.println(imgUrl);
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("url", imgUrl);
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
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
