 package com.four.servlet.contral;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.four.dao.impl.recUsers;
import com.four.javaBean.UserLoginBean;
import com.four.service.impl.FileUpload;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class HeadImgUpload
 * �ϴ��û�ͷ��
 */
@WebServlet("/HeadImgUpload")
@MultipartConfig
public class HeadImgUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeadImgUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObject=new JSONObject();
		PrintWriter outPrint = response.getWriter();
		//�洢·��
		String savePath = request.getServletContext().getRealPath("/img/head");
		FileUpload upload=new FileUpload();
		String filename=upload.saveFile(savePath,request);
		if(filename!=null) {
			//��·���������ݿ�
			 HttpSession session=request.getSession(false);
			UserLoginBean user = (UserLoginBean)session.getAttribute("userInfo");
			if(new recUsers().changeUserHeadImg(savePath + "\\" + filename, user.getStuId())) {
				System.out.println("�ļ�·���Ѵ������ݿ�");
				jsonObject.put("", "");
			}else {
				System.out.println("�ļ�·��δ�ܴ������ݿ�");
				jsonObject.put("", "");
			}
		}else {
			//�ļ�д�����
			System.out.println("�ļ�д�����");
			jsonObject.put("", "");
			outPrint.write(jsonObject.toString());
		}
		outPrint.write(jsonObject.toString());
		/*File file = new File(savePath);
		//�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
		if (!file.exists() && !file.isDirectory()) {
		System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
		//����Ŀ¼
		file.mkdir();
		}
		try{
			//ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
			//1������һ��DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2������һ���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			//����ϴ��ļ�������������
			upload.setHeaderEncoding("UTF-8"); 
			//3���ж��ύ�����������Ƿ����ϴ���������\
			if(!ServletFileUpload.isMultipartContent(request)){
				//���մ�ͳ��ʽ��ȡ����
				System.out.println("��MultipartContent");
				return;
			}
			//4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
			List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
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
					//ʹ��������ɵ�UUID��Ϊ�ļ���
					filename=new ProduceFileName().getUUID();
					//��ȡitem�е��ϴ��ļ���������
					InputStream in = item.getInputStream();
					//����һ���ļ������
					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					System.out.println(savePath + "\\" + filename);
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
					 
					 //��·���������ݿ�
					 HttpSession session=request.getSession(false);
					UserLoginBean user = (UserLoginBean)session.getAttribute("userInfo");
					if(new recUsers().changeUserHeadImg(savePath + "\\" + filename, user.getStuId())) {
						jsonObject.put("", "");
					}else {
						jsonObject.put("", "");
					}
					outPrint.write(jsonObject.toString());
				}
			}
		}catch (FileUploadException e2) {
			// TODO: handle exception
			e2.printStackTrace();
			jsonObject.put("", "");
			outPrint.write(jsonObject.toString());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
