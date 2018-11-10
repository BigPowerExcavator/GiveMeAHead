package com.lxj;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class TestSend {
	
	public Map<String, Object> test() throws IOException{
		TestLogin tlLogin=new TestLogin();
		 tlLogin.safeCookie();
		 tlLogin.initLogin("171543428", "L1209008");
		
		Map<String, Object> map= tlLogin.getClassForm();
		return map;
	}

	 public static void main(String[] args) throws IOException {
	        //发送 GET 请求
	        
	        /*System.out.println(list.toString());
	        System.out.println(list.get(0));*/
	        
	        /*//发送 POST 请求
	        String sr=HttpRequestSend.sendPost("http://jwxt.gduf.edu.cn/jsxsd/", null);
	        System.out.println(sr);*/
		 
		 
		 //String sr=HttpRequestSend.sendPost("http://jwxt.gduf.edu.cn/jsxsd/xk/LoginToXk", null);
		 
	   /*  System.out.println(sr);
	     String aString2=HttpRequestSend.sendGet("http://jwxt.gduf.edu.cn/jsxsd/xskb/xskb_list.do", null);
	     System.out.println(aString2);*/
		 
		 TestLogin tlLogin=new TestLogin();
		 tlLogin.safeCookie();
		 tlLogin.initLogin("171543428", "L1209008");
		 tlLogin.getClassForm();
	    }

}
