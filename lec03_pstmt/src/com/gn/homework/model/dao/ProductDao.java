package com.gn.homework.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDao {
	private static final String className = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
	private static final String id = "scott";
	private static final String pwd = "tiger";
	
	public boolean checkProduct(String productName) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * "
				+ " FROM sm_product "
				+ " WHERE p_name = ?";
		
		try{
			Class.forName(className);
			
			conn = DriverManager.getConnection(url, id, pwd);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
				
			if(rs.next()) {
				result = true;
			}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		
		
		return result;
	}
}
