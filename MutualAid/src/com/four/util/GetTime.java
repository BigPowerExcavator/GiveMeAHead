package com.four.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
	public String getTime() {
		String date = null;
		//new Date()Ϊ��ȡ��ǰϵͳʱ��
		Date time = new Date();
		//�������ڸ�ʽ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = sdf.format(time);
		//System.out.println(sdf.format(new Date()));
		return date;
	}
}
