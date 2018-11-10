package com.lxj;

import java.io.FileReader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
public class GetEncoded {
	
	 /**
     *  Java执行JavaScript脚本破解加密算法
     * @param str
     * @return 加密后的字符串
     */
    public String playJsTest(String str) {
    	// 创建一个脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        // 获取一个指定的名称的脚本引擎
        ScriptEngine engine = manager.getEngineByExtension("js");
        try {
            // 获取当前类的所在目录的路径（%20是处理空格）
            String path = this.getClass().getResource("/").getPath().replaceAll("%20", " ");
            System.out.println(path);
            // FileReader的参数为所要执行的js文件的路径（对空格进行处理）
            engine.eval(new FileReader(path +"com\\lxj\\"+ "conwork.js"));
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                // 从脚本引擎中返回一个给定接口的实现
                Methods executeMethod = invocable.getInterface(Methods.class);
                System.out.println(executeMethod);
                // 执行指定的js方法
                return executeMethod.encodeInp(str);
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        return null;
    }
    
    public String getEncoded(String account,String password) {
    	String encoded="";
    	encoded=playJsTest(account)+"%%%"+playJsTest(password);
    	return encoded;
    }

	/*public static void main(String[] args) {
		System.out.println("16451");
		System.out.println(playJsTest("171543428"));

	}*/

}
