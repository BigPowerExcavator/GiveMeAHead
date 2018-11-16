package com.four.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToTime {
	
	public String GetTime(String time,String format) {
		String trueTime="";
		SimpleDateFormat d = new SimpleDateFormat(format);
        Date date = new Date(Long.parseLong(time));
		trueTime=d.format(date);		
		return trueTime;
	}
}
