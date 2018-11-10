package com.four.util;
import java.sql.*;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * @author:jinner
 * Description:数据库连接工具
 */
public class jdbcUtils {
	
	private static ComboPooledDataSource ds = null;
	
	//在静态代码块中创建数据库连接池
	//详情看xml配置文件
	static {
		try {
			ds = new ComboPooledDataSource("MySQL");     //使用c3p0的命名配置来创建数据源
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @author jinner
	 * @deprecated:从数据源中获取数据库连接
	 * @method:getConnection
	 */
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	} 
	
	/**
	 * @deprecated:释放资源
	 * @author 76429
	 */
	
	public  static void release(Connection conn,Statement st,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
}
