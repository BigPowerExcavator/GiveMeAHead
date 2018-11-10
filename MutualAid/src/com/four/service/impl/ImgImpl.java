package com.four.service.impl;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.Part;

import com.four.dao.impl.RepairFormImp;
import com.four.service.ImgService;

public class ImgImpl  implements ImgService{

	@Override
	public boolean writeImg(String part,Part file) {		
		try {
			file.write(part);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
