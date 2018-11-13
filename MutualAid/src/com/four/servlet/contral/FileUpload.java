package com.four.servlet.contral;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;

import com.four.javaBean.UserLoginBean;
import com.four.service.ImgService;
import com.four.service.impl.ImgImpl;
import com.four.util.ProduceFileName;

/**
 * Servlet implementation class UserHeadImgUpload
 */
@WebServlet("/FileUpload")
@MultipartConfig
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�洢·��
		String savePath = request.getServletContext().getRealPath("/img/head");
		File file = new File(savePath);
		//�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
		if (!file.exists() && !file.isDirectory()) {
		System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
		//����Ŀ¼
		file.mkdir();
		}
		//��Ϣ��ʾ
		String message = "";
		try{
			//ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
			//1������һ��DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2������һ���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			//����ϴ��ļ�������������
			upload.setHeaderEncoding("UTF-8"); 
			//3���ж��ύ�����������Ƿ����ϴ���������
			if(!ServletFileUpload.isMultipartContent(request)){
				//���մ�ͳ��ʽ��ȡ����
				return;
			}
			//4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				//���fileitem�з�װ������ͨ�����������
				if(item.isFormField()){
					String name = item.getFieldName();
					//�����ͨ����������ݵ�������������
					String value = item.getString("UTF-8");
					//value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				}else{//���fileitem�з�װ�����ϴ��ļ�
					//�õ��ϴ����ļ����ƣ�
					String filename = item.getName();
					System.out.println(filename);
					if(filename==null || filename.trim().equals("")){
						continue;
					}
					//ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
					//�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					//��ȡitem�е��ϴ��ļ���������
					InputStream in = item.getInputStream();
					//����һ���ļ������
					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					//����һ��������
					byte buffer[] = new byte[1024];
					//�ж��������е������Ƿ��Ѿ�����ı�ʶ
					int len = 0;
					//ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
					while((len=in.read(buffer))>0){
						//ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
						out.write(buffer, 0, len);
					}
					//�ر�������
					 in.close();
					//�ر������
					 out.close();
					 //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
					 item.delete();
					 message = "�ļ��ϴ��ɹ���";
				}
			}
		}catch (Exception e) {
			message= "�ļ��ϴ�ʧ�ܣ�";
			e.printStackTrace();

		}
		 
		 
		Part part = request.getPart("file");
		System.out.println("part="+part);
		/*//Servlet3û���ṩֱ�ӻ�ȡ�ļ����ķ���,��Ҫ������ͷ�н�������
		//��ȡ����ͷ������ͷ�ĸ�ʽ��form-data; name="file"; filename="snmp4j--api.zip"
		String header = part.getHeader("content-disposition");
		//��ȡ�ļ���
		String[] tempArr1 = header.split(";");
		String[] tempArr2 = tempArr1[2].split("=");
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");*/
		
		String fileName=new ProduceFileName().getUUID();
		
		//�½�img������
		ImgImpl writeImg=new ImgImpl();
		if(writeImg.writeImg(savePath+File.separator+fileName, part)) {
			HttpSession session=request.getSession(false);
			UserLoginBean user=(UserLoginBean)session.getAttribute("userInfo");			
		}else {
			System.out.println();
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
