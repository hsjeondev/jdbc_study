package com.gn.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class E_Insert_Transaction {
	public static void main(String[] args) {
		// 1. JDBC 작업용 객체 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 2. try~catch~finally
		try {
			// 3. DriverManager 등록
			Class.forName("org.mariadb.jdbc.Driver");
			// 4. Connection 객체 생성
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			// 5. AutoCommit 해제
			conn.setAutoCommit(false); // 오토커밋 끄는 것과 동시에 트랜잭션 시작으로 설정해줌.
			// 6. Statement 생성
			stmt = conn.createStatement();
			// 7. SQL문 실행
			// 데이터 존재 여부 -> 갯수
			String str = "테스트6";
			String sql1 = "SELECT COUNT(*) FROM test WHERE t_name = '"+str+"'";
			rs = stmt.executeQuery(sql1);
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt(1); // 1은 SELECT문의 첫번째 것을 가져온다는 것
			}
			// 8. 결과 확인
			if(cnt == 0) {
				// INSERT하기
				String sql2 = "INSERT INTO test (t_name) VALUES ('"+str+"')";
				cnt = stmt.executeUpdate(sql2);
				if(cnt > 0) {
					System.out.println("성공!!");
				} else {
					System.out.println("실패!!");
				}
			} else {
				System.out.println("이미 존재하는 이름입니다.");
			}
			conn.commit();
		} catch (Exception e) {
			// 9. 결과 데이터베이스에 반영
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
