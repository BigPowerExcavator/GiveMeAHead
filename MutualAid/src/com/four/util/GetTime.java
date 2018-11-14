package com.four.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
	public String getTime() {
		String date = null;
		//new Date()为获取当前系统时间
		Date time = new Date();
		//设置日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = sdf.format(time);
		//System.out.println(sdf.format(new Date()));
		return date;
	}
}
