package com.four.servlet.contral;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.util.CodeInamgeUtil;

/**
 * Servlet implementation class GetRegisterCode
 * 获取验证码图片，并把答案放在session中
 */
@WebServlet("/GetRegisterCode")
public class GetRegisterCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRegisterCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
		
		request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8" );
		
		//Writer out = response.getWriter();
		CodeInamgeUtil codeInamgeUtil=new CodeInamgeUtil();
		//获得答案和图片流
		Map<String,Object> map=codeInamgeUtil.generateCodeAndPic();
		HttpSession session=request.getSession(true);
		//把答案放在session中
		session.setAttribute("codeString", (String)map.get("code"));
		//发送验证码图片
		ImageIO.write((BufferedImage)map.get("codePic"),"JPEG",response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
