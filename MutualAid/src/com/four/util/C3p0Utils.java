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
			dataSource = new ComboPooledDataSource("MySQL");     //使用c3p0的命名配置来创建数据源
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
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
 
	
	//释放连接回连接池
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
