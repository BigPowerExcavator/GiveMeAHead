package com.lxj;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestLogin {
	
	private String indexUrl="http://jwxt.gduf.edu.cn/jsxsd/";
	private String loginUrl="http://jwxt.gduf.edu.cn/jsxsd/xk/LoginToXk";
	private String classFormUrl="http://jwxt.gduf.edu.cn/jsxsd/xskb/xskb_list.do";
	private String username = "";
    private String password = "";
    private String path = TestLogin.class.getResource("/").getPath().replaceAll("%20", " ") + "safecode.png";
    private Map<String, String> cookie;
    
    /*
     * 保存Cookie
     */
    public void safeCookie() throws IOException {
    	Response response = Jsoup.connect(indexUrl).method(Method.GET).execute();
    	cookie = response.cookies();
    }
    
    /*
     * 登录教务系统
     */
    public void initLogin(String account,String password)throws IOException{
    	this.password=password;
    	this.username=account;
    	try {
			Map<String, String>requestHead=new HashMap<String,String>();
			requestHead.put("view", "1");
			requestHead.put("encoded", new GetEncoded().getEncoded(account, password));
			Connection connect = Jsoup.connect(loginUrl)
                    .header("Accept",
                            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .userAgent("Mozilla").method(Method.POST).data(requestHead).timeout(3000);
			//把cookie写入请求头中
			for (Map.Entry<String, String> entry : cookie.entrySet()) {
                connect.cookie(entry.getKey(), entry.getValue());
            }
			Response response = connect.execute();
            //System.out.println(response.parse().toString());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    /*
     * 获取课表
     */
    public Map<String, Object> getClassForm() {
    	try {
    		Map<String, String>requestHead=new HashMap<String,String>();
    		Connection connect = Jsoup.connect(classFormUrl)
                    .header("Accept",
                            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .userAgent("Mozilla").method(Method.GET).data(requestHead).timeout(30000);
    		for (Map.Entry<String, String> entry : cookie.entrySet()) {
                connect.cookie(entry.getKey(), entry.getValue());
            }
    		Response response = connect.execute();
    		Document doc=response.parse();
            Elements links = doc.getElementsByTag("table");
            Elements classFormTd=links.get(1).getElementsByTag("td");
            Map<String, Object>classMap=new HashMap<String,Object>();
            for(int i=0;i<7;i++) {
            	for(int j=0;j<7;j++) {
            		Element theClass=classFormTd.get(i*7+j).getElementsByTag("div").get(1);
            		Elements font=theClass.getElementsByTag("font");
            		Map<String, Object>oneTime=new HashMap<String,Object>();
            		String className1=theClass.ownText();
            		System.out.println(className1);
            		if(("").equals(className1)) {
            			classMap.put("class"+(i*7+j+1), oneTime);
            			continue;
            		}
            		String[] classNameSet=className1.split(" --------------------- ");
            		for(int k=0;k<font.size()/3;k++) {
            			Map<String, String>oneClass=new HashMap<String,String>();
            			String className=classNameSet[k];
        				String teacher=font.get(k*3).wholeText();
        				String time=font.get(k*3+1).wholeText();
        				String classroom=font.get(k*3+2).wholeText();
            			oneClass.put("className", className);
            			oneClass.put("teacher", teacher);
            			oneClass.put("time", time);
            			oneClass.put("classroom", classroom);
            			oneTime.put("oneClass"+(k+1), oneClass);
            			//System.out.println(classroom);
            		}
            		classMap.put("class"+(i*7+j+1), oneTime);
            		/*System.out.println();
            		System.out.println(oneClass);*/
            	}
            	
            }
            System.out.println(classFormTd.size());
            System.out.println(classMap.toString());
            System.out.println(classMap.size());
            System.out.println("over");
            return classMap;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
    	
    }

}
