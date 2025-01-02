package com.gn.study.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gn.study.model.vo.Test;

public class Select_List_vo {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pw = "tiger";
			
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT t_no ,t_name ,t_date"
						+ " FROM test";
			
			rs = stmt.executeQuery(sql);
			
			List<Test> list = new ArrayList<Test>();
			while(rs.next()) {
				Test test = new Test(rs.getInt("t_no"), rs.getString("t_name"), rs.getTimestamp("t_date").toLocalDateTime());
				list.add(test);
			}
			
			if(list.isEmpty()) {
				System.out.println("데이터가 없습니다.");
			} else {
				for(Test t : list) {
					System.out.println(t);
				}
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
