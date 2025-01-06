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
	private static final String className = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
	private static final String id = "scott";
	private static final String pw = "tiger";
	
	public int withdrawalMember(String memId, String memPw) {
		int result = 0;
		
		String sql = "DELETE FROM `member` "
				+ " WHERE m_id = '"+memId+"' AND m_pw = '"+memPw+"'";
		
		try(Connection conn = DriverManager.getConnection(url, id, pw);
				Statement stmt = conn.createStatement();) {
			
			Class.forName(className);
			
			result = stmt.executeUpdate(sql);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateMemberInfo(String memId, String memPw, String name, String phone, String email) {
		int result = 0;
		
		String sql = "UPDATE `member` "
					+ " SET m_name = '"+name+"' "
					+ ",m_phone = '"+phone+"' "
					+ ",m_email = '"+email+"'"
					+ "WHERE m_id = '"+memId+"' AND m_pw = '"+memPw+"'";
		
		try(Connection conn = DriverManager.getConnection(url, id, pw);
				Statement stmt = conn.createStatement();) {
			
			Class.forName(className);
			
			result = stmt.executeUpdate(sql);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Member> selectMemberKeywordByName(String keyword) {
		List<Member> list = new ArrayList<Member>();
		
		String sql = "SELECT *\r\n"
				+ "FROM `member`\r\n"
				+ "WHERE m_name LIKE '%"+keyword+"%'";
		
		try(Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			Class.forName(className);
			
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Member selectMemberOne(String memId) {
		
		Member m = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(className);
			
			conn = DriverManager.getConnection(url, id, pw);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM member WHERE m_id = '"+memId+"'");
			
			if(rs.next()) {
				int memberNo = rs.getInt("m_no");
				String memberId = rs.getString("m_id");
				String memberPw = rs.getString("m_pw");
				m = new Member(memberNo, memberId, memberPw);
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
		
		return m;
	}
	
	public Member selectMemberOne(String memId, String memPw) {
		
		Member m = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(className);
			
			conn = DriverManager.getConnection(url, id, pw);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT *\r\n"
					+ "FROM `member`\r\n"
					+ "WHERE m_id = '"+memId+"' AND m_pw = '"+memPw+"'");
			
			if(rs.next()) {
				int memberNo = rs.getInt("m_no");
				String memberId = rs.getString("m_id");
				String memberPw = rs.getString("m_pw");
				m = new Member(memberNo, memberId, memberPw);
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
		
		return m;
	}
	
	public List<Member> selectMemberAll() {
		// 전체 member 정보 조회 -> List<Member>
		List<Member> list = new ArrayList<Member>();
		// DB에 SQL문 요청
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(className);
			
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
		int result = 0;
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(className);
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
