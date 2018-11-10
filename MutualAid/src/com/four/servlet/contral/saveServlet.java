package com.four.servlet.contral;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jdk.incubator.http.HttpRequest;

/**
 * Servlet implementation class saveServlet
 * @param <HttpFileCollection>
 */
@WebServlet("/saveServlet")
public class saveServlet<HttpFileCollection> extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public saveServlet() {
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
		/**
		 * 
		 * 
		 * 这里修改你需要保存的路径
		 * 
		 */
		//图片存放的路径
		String path = "E:/eclipse/javaEE work/FirstJinner/WebContent/Imgs";
		
		//判断文件存不存在
		File foder = new File(path);
		if(!foder.exists()) {
			foder.mkdirs();
		}
		
		//使用文件上传组件
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		
		try {
			//解析请求信息
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list) {
				//处理上传文件的保存名称
				String imgName = item.getName();
				System.out.println(imgName);
				imgName = imgName.substring(imgName.lastIndexOf("\\")+1);
				System.out.println(imgName);
				//输入和输出流
				InputStream is = item.getInputStream();
				FileOutputStream fos = new FileOutputStream(new File(path+"/"+imgName));
			    
				//写入数据
				byte[] buffer = new byte[1024];
				int len =0;
				while((len=(is.read(buffer)))> -1) {
					fos.write(buffer, 0, len);
				}
				
				//关闭输入输出流
				fos.close();
				is.close();
				
				System.out.println("图片上传成功:"+path+"/"+imgName);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
