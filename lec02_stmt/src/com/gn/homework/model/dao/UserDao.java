package com.gn.homework.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.gn.homework.model.vo.User;

public class UserDao {
	private static final String className = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
	private static final String id = "scott";
	private static final String pwd = "tiger";
	
	public int changePwd(String userId, String userPwd, String changePwd) {
		int result = 0;
		
		try(Connection conn = DriverManager.getConnection(url, id, pwd);
				Statement stmt = conn.createStatement();) {
				
				Class.forName(className);
				
				String sql = "UPDATE wm_user "
						+ " SET u_pwd = '"+changePwd+"' "
						+ " WHERE u_pwd = '"+userPwd+"'";
				
				result = stmt.executeUpdate(sql);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return result;
	}
	
	public int withdrawUser(String userId, String userPwd) {
		int result = 0;
		
		try(Connection conn = DriverManager.getConnection(url, id, pwd);
				Statement stmt = conn.createStatement();) {
				
				Class.forName(className);
				
				String sql = "DELETE FROM wm_user "
						+ " WHERE u_id = '"+userId+"' AND u_pwd = '"+userPwd+"'";
				
				result = stmt.executeUpdate(sql);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return result;
	}
	
	public User login(String userId, String userPwd) {
		User user = null;
		
		String sql = "SELECT * "
				+ " FROM wm_user "
				+ " WHERE u_id = '"+userId+"' AND u_pwd = '"+userPwd+"'";
		
		try(Connection conn = DriverManager.getConnection(url, id, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
				
			Class.forName(className);
				
			if(rs.next()) {
				int userNo = rs.getInt("u_no");
				String uId = rs.getString("u_id");
				String uPwd = rs.getString("u_pwd");
				String userName = rs.getString("u_name");
				LocalDateTime regDate = rs.getTimestamp("reg_date").toLocalDateTime();
				String isAdmin = rs.getString("is_admin");
				user = new User(userNo, uId, uPwd, userName, regDate, isAdmin);
			}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return user;
	}
	
	public boolean checkUserId(String userId) {
		boolean result = false;
		
		String sql = "SELECT * "
				+ " FROM wm_user "
				+ " WHERE u_id = '"+userId+"'";
		
		try(Connection conn = DriverManager.getConnection(url, id, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
				
			Class.forName(className);
				
			if(rs.next()) {
				result = true;
			}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		return result;
	}
	
	public int createAccount(String userId, String userPwd, String userName) {
		int result = 0;
		
		try(Connection conn = DriverManager.getConnection(url, id, pwd);
			Statement stmt = conn.createStatement();) {
			
			Class.forName(className);
			
			String sql = "INSERT INTO wm_user(u_id ,u_pwd ,u_name) "
					+ " VALUES ('"+userId+"' ,'"+userPwd+"' ,'"+userName+"')";
			
			result = stmt.executeUpdate(sql);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

}
