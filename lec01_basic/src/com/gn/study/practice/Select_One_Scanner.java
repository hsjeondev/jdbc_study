package com.gn.study.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.gn.study.model.vo.Test;

public class Select_One_Scanner {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("조회하고자 하는 행의 번호를 입력하세요.");
		System.out.print("번호 : ");
		int userInput = scanner.nextInt();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String address = "org.mariadb.jdbc.Driver";
			Class.forName(address);
			
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT t_no ,t_name ,t_date"
					+ " FROM test"
					+ " WHERE t_no = " + userInput;
			rs = stmt.executeQuery(sql);
			
			Test test = null;
			if(rs.next()) {
				test = new Test(rs.getInt("t_no"), rs.getString("t_name"), rs.getTimestamp("t_date").toLocalDateTime());
				System.out.print("번호 : " + test.getTestName());
				System.out.print(", 이름 : " + test.getTestName());
				System.out.print(", 등록일 : " + test.changeDate());
			} else {
				System.out.println("해당하는 데이터가 없습니다.");
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
				scanner.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
