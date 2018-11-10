package com.four.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {
	private static ComboPooledDataSource dataSource=null;
	static{
		try {
			dataSource = new ComboPooledDataSource("MySQL");     //ʹ��c3p0��������������������Դ
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn=null;
		try {
			 conn=dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
 
	
	//�ͷ����ӻ����ӳ�
    public static void close(Connection ct,PreparedStatement ps,ResultSet rs){  
           if(rs!=null){  
               try {  
                   rs.close();  
               } catch (SQLException e) { 
            	   e.printStackTrace();
               }  
           }  
           if(ps!=null){  
               try {  
                   ps.close();  
               } catch (SQLException e) { 
            	   e.printStackTrace();
               }  
           }  
     
           if(ct!=null){  
               try {  
                   ct.close();  
               } catch (SQLException e) {  
            	   e.printStackTrace();
               }  
           }  
       }

}
