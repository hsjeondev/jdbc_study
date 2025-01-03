package com.gn.study.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gn.study.model.vo.Test;

public class Insert_Scanner {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름 : ");
		String userName = scanner.next();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pwd = "tiger";
			
			conn = DriverManager.getConnection(url, id, pwd);
			
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String insertSql = "INSERT INTO test (t_name) VALUES ('" + userName +"')";
			int insertResult = stmt.executeUpdate(insertSql);
			
			if(insertResult > 0) {
				System.out.println("===== test =====");
				String selectSql ="SELECT * FROM test WHERE t_name =" + "'" + userName + "'";
				rs = stmt.executeQuery(selectSql);
				List<Test> list = new ArrayList<Test>();
				while(rs.next()) {
					Test t = new Test(rs.getInt("t_no"), rs.getString("t_name"), rs.getTimestamp("t_date").toLocalDateTime());
					list.add(t);
				}
				if(list.isEmpty() == false) {
					System.out.println(list);
				} else {
					System.out.println("데이터가 없습니다.");
				}
			} else {
				System.out.println("실패");
			}
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			scanner.close();
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
