package com.gn.study.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gn.study.model.vo.Member;

public class MemberDao {
	
	public List<Member> selectMemberAll() {
		// 전체 member 정보 조회 -> List<Member>
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// DB에 SQL문 요청
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pw = "tiger";
			
			conn = DriverManager.getConnection(url, id, pw);
			stmt = conn.createStatement();
			
			String sql = "SELECT * "
					+ " FROM `member`";
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int memberNo = rs.getInt("m_no");
				String memberId = rs.getString("m_id");
				String memberPw = rs.getString("m_pw");
				String memberName = rs.getString("m_name");
				String memberEmail = rs.getString("m_email");
				String memberPhone = rs.getString("m_phone");
				String memberGender = rs.getString("m_gender");
				LocalDateTime regDate = rs.getTimestamp("reg_date").toLocalDateTime();
				LocalDateTime modDate = rs.getTimestamp("mod_date").toLocalDateTime();
				Member member = new Member(memberNo, memberId, memberPw, memberName, memberEmail,
						memberPhone, memberGender, regDate, modDate);
				list.add(member);
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
		
		return list;
	}
	
	public int insertMember(Member m) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			stmt = conn.createStatement();
			String sql = "INSERT INTO `member` (m_id ,m_pw ,m_name ,m_email ,m_gender ,m_phone) "
					+ "VALUES ('"+m.getMemberId()+"','"+m.getMemberPw()+"','"+m.getMemberName()
					+"','"+m.getMemberEmail()+"','"+m.getMemberGender()+"','"+m.getMemberPhone()+"')";
			
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
