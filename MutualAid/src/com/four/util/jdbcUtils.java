package com.four.util;
import java.sql.*;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * @author:jinner
 * Description:���ݿ����ӹ���
 */
public class jdbcUtils {
	
	private static ComboPooledDataSource ds = null;
	
	//�ھ�̬������д������ݿ����ӳ�
	//���鿴xml�����ļ�
	static {
		try {
			ds = new ComboPooledDataSource("MySQL");     //ʹ��c3p0��������������������Դ
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @author jinner
	 * @deprecated:������Դ�л�ȡ���ݿ�����
	 * @method:getConnection
	 */
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	} 
	
	/**
	 * @deprecated:�ͷ���Դ
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
