package com.four.util;


//生成真实的日期
public class GetTrueDoorTime {
	private String doorNum;
	private String trueTime="";
	
	public String getDoorTime(String doorNum) {
		this.doorNum=doorNum;
		String first=doorNum.substring(0, 1);
		switch (first) {
		case "1":
			trueTime+="星期一";
			break;
		case "2":
			trueTime+="星期二";
			break;
		case "3":
			trueTime+="星期三";
			break;
		case "4":
			trueTime+="星期四";
			break;
		case "5":
			trueTime+="星期五";
			break;
		case "6":
			trueTime+="星期六";
			break;
		case "7":
			trueTime+="星期日";
			break;
		default:
			break;
		}
		String second=doorNum.substring(1);
		switch (second) {
		case "1":
			trueTime+=" 8:00~10:00";
			break;
		case "2":
			trueTime+=" 10:00~12:00";
			break;
		case "3":
			trueTime+=" 12:00~14:00";
			break;
		case "4":
			trueTime+=" 14:00~16:00";
			break;
		case "5":
			trueTime+=" 16:00~18:00";
			break;
		default:
			break;
		}
		return trueTime;
	}
}
