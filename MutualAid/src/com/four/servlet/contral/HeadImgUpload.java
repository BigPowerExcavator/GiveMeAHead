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
 * 上传用户头像
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
		//存储路径
		String savePath = request.getServletContext().getRealPath("/img/head");
		FileUpload upload=new FileUpload();
		String filename=upload.saveFile(savePath,request);
		if(filename!=null) {
			//把路径输入数据库
			 HttpSession session=request.getSession(false);
			UserLoginBean user = (UserLoginBean)session.getAttribute("userInfo");
			if(new recUsers().changeUserHeadImg(savePath + "\\" + filename, user.getStuId())) {
				System.out.println("文件路径已存入数据库");
				jsonObject.put("", "");
			}else {
				System.out.println("文件路径未能存入数据库");
				jsonObject.put("", "");
			}
		}else {
			//文件写入错误
			System.out.println("文件写入错误");
			jsonObject.put("", "");
			outPrint.write(jsonObject.toString());
		}
		outPrint.write(jsonObject.toString());
		/*File file = new File(savePath);
		//判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
		System.out.println(savePath+"目录不存在，需要创建");
		//创建目录
		file.mkdir();
		}
		try{
			//使用Apache文件上传组件处理文件上传步骤：
			//1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8"); 
			//3、判断提交上来的数据是否是上传表单的数据\
			if(!ServletFileUpload.isMultipartContent(request)){
				//按照传统方式获取数据
				System.out.println("非MultipartContent");
				return;
			}
			//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
			for(FileItem item : list){
				//如果fileitem中封装的是普通输入项的数据
				if(item.isFormField()){
					String name = item.getFieldName();
					//解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					//value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				}else{//如果fileitem中封装的是上传文件
					//得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					if(filename==null || filename.trim().equals("")){
						continue;
					}
					//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					//使用随机生成的UUID作为文件名
					filename=new ProduceFileName().getUUID();
					//获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					//创建一个文件输出流
					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					System.out.println(savePath + "\\" + filename);
					//创建一个缓冲区
					byte buffer[] = new byte[1024];
					//判断输入流中的数据是否已经读完的标识
					int len = 0;
					//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while((len=in.read(buffer))>0){
						//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
						out.write(buffer, 0, len);
					}
					//关闭输入流
					 in.close();
					//关闭输出流
					 out.close();
					 //删除处理文件上传时生成的临时文件
					 item.delete();
					 
					 //把路径输入数据库
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
