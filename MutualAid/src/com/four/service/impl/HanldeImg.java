package com.four.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class HanldeImg {
	public String HandleImgUrl(String[] array) {
		String url=null;
		int size = array.length;
		
		for(int i=0;i<size;i++) {
			if(url!=null || url.equals("")) {
				url="###"+endImgName(array[i]);
			}else {
				url=endImgName(array[i]);
			}
		}
		return url;
	}
	//获取图片名
	public String endImgName(String imgName) {
		String endName="";
		endName = imgName.substring(imgName.lastIndexOf("\\")+1);
		System.out.println(endName);
		return endName;
	}
	//把图片存入本地
	public String completeUrl(String savePath) {

		String completeUrl="";
		String filename=null;
		File file = new File(savePath);
		//判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			
			System.out.println(savePath+"目录不存在，需要创建");
			//创建目录
			file.mkdir();
		}
		
		return completeUrl;
	}
	
	
}
