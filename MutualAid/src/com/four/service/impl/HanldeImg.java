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
	//��ȡͼƬ��
	public String endImgName(String imgName) {
		String endName="";
		endName = imgName.substring(imgName.lastIndexOf("\\")+1);
		System.out.println(endName);
		return endName;
	}
	//��ͼƬ���뱾��
	public String completeUrl(String savePath) {

		String completeUrl="";
		String filename=null;
		File file = new File(savePath);
		//�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
		if (!file.exists() && !file.isDirectory()) {
			
			System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
			//����Ŀ¼
			file.mkdir();
		}
		
		return completeUrl;
	}
	
	
}
