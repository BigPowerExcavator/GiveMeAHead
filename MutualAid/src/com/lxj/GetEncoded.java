package com.lxj;

import java.io.FileReader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
public class GetEncoded {
	
	 /**
     *  Javaִ��JavaScript�ű��ƽ�����㷨
     * @param str
     * @return ���ܺ���ַ���
     */
    public String playJsTest(String str) {
    	// ����һ���ű����������
        ScriptEngineManager manager = new ScriptEngineManager();
        // ��ȡһ��ָ�������ƵĽű�����
        ScriptEngine engine = manager.getEngineByExtension("js");
        try {
            // ��ȡ��ǰ�������Ŀ¼��·����%20�Ǵ���ո�
            String path = this.getClass().getResource("/").getPath().replaceAll("%20", " ");
            System.out.println(path);
            // FileReader�Ĳ���Ϊ��Ҫִ�е�js�ļ���·�����Կո���д���
            engine.eval(new FileReader(path +"com\\lxj\\"+ "conwork.js"));
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                // �ӽű������з���һ�������ӿڵ�ʵ��
                Methods executeMethod = invocable.getInterface(Methods.class);
                System.out.println(executeMethod);
                // ִ��ָ����js����
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
