package com.gn.homework.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.gn.homework.model.vo.User;

public class UserDao {
	private static final String className = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
	private static final String id = "scott";
	private static final String pwd = "tiger";
	
	public int createAccount(String userId, String userPwd, String userNickname) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO sm_user(u_id ,u_pwd ,u_nickname) "
					+ " VALUES (? ,? ,?)";
		
		try {
			
			Class.forName(className);
			
			conn = DriverManager.getConnection(url, id, pwd);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			pstmt.setString(3, userNickname);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	public boolean checkUserId(String userId) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * "
				+ " FROM sm_user "
				+ " WHERE u_id = ?";
		
		try{
			Class.forName(className);
			
			conn = DriverManager.getConnection(url, id, pwd);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
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
	
	public User login(String userId, String userPwd) {
		User user = null;
		
		String sql = "SELECT * "
				+ " FROM sm_user "
				+ " WHERE u_id = '"+userId+"' AND u_pwd = '"+userPwd+"'";
		
		try(Connection conn = DriverManager.getConnection(url, id, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
				
			Class.forName(className);
				
			if(rs.next()) {
				int userNo = rs.getInt("u_no");
				String uId = rs.getString("u_id");
				String uPwd = rs.getString("u_pwd");
				String userNickname = rs.getString("u_nickname");
				String isAdmin = rs.getString("is_admin");
				LocalDateTime regDate = rs.getTimestamp("reg_date").toLocalDateTime();
				LocalDateTime modDate = rs.getTimestamp("mod_date").toLocalDateTime();
				user = new User(userNo, uId, uPwd, userNickname, isAdmin, regDate, modDate);
			}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return user;
	}
}
