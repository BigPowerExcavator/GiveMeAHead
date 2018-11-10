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
		//�����ַ�����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/**
		 * 
		 * 
		 * �����޸�����Ҫ�����·��
		 * 
		 */
		//ͼƬ��ŵ�·��
		String path = "E:/eclipse/javaEE work/FirstJinner/WebContent/Imgs";
		
		//�ж��ļ��治����
		File foder = new File(path);
		if(!foder.exists()) {
			foder.mkdirs();
		}
		
		//ʹ���ļ��ϴ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		
		try {
			//����������Ϣ
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list) {
				//�����ϴ��ļ��ı�������
				String imgName = item.getName();
				System.out.println(imgName);
				imgName = imgName.substring(imgName.lastIndexOf("\\")+1);
				System.out.println(imgName);
				//����������
				InputStream is = item.getInputStream();
				FileOutputStream fos = new FileOutputStream(new File(path+"/"+imgName));
			    
				//д������
				byte[] buffer = new byte[1024];
				int len =0;
				while((len=(is.read(buffer)))> -1) {
					fos.write(buffer, 0, len);
				}
				
				//�ر����������
				fos.close();
				is.close();
				
				System.out.println("ͼƬ�ϴ��ɹ�:"+path+"/"+imgName);
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
