package com.gn.study.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gn.study.model.vo.ProjectVo;

public class ProjectDao {
	
	public int deleteProjectOne(int projectNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String user = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, user, pw);
			String sql = "DELETE FROM project "
					+ " WHERE project_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projectNo);
			
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
	
	public int updateProjectOne(int projectNo, String projectName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String user = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, user, pw);
			String sql = "UPDATE project "
					+ " SET project_name = ? "
					+ " WHERE project_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, projectName);
			pstmt.setInt(2, projectNo);
			
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
	
	public List<ProjectVo> selectProjectAllByManagerName(String managerName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProjectVo> list = new ArrayList<ProjectVo>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String user = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "SELECT p.project_id ,p.project_name ,IFNULL(e.emp_name, '미정') AS `e.emp_name` ,e.emp_id ,p.reg_date ,p.mod_date "
					+ " FROM project p "
					+ " JOIN employee e "
					+ " ON p.project_manager = e.emp_id "
					+ " WHERE p.project_manager = (SELECT emp_id "
					+ "							   FROM employee "
					+ "							   WHERE emp_name = ?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, managerName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProjectVo vo = new ProjectVo();
				vo.setProjectId(rs.getInt("p.project_id"));
				vo.setProjectName(rs.getString("p.project_name"));
				vo.setProjectManager(rs.getString("e.emp_name"));
				vo.setProjectManagerId(rs.getInt("e.emp_id"));
				vo.setRegDate(rs.getTimestamp("p.reg_date").toLocalDateTime());
				vo.setModDate(rs.getTimestamp("p.mod_date").toLocalDateTime());
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) rs.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<ProjectVo> selectProjectAllByName(String projectName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProjectVo> list = new ArrayList<ProjectVo>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String user = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "SELECT p.project_id ,p.project_name ,e.emp_name ,p.reg_date ,p.mod_date "
						+ " FROM project p "
						+ " LEFT JOIN employee e "
						+ " ON p.project_manager = e.emp_id "
						+ " WHERE p.project_name LIKE CONCAT('%',?,'%')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, projectName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProjectVo vo = new ProjectVo();
				vo.setProjectId(rs.getInt("p.project_id"));
				vo.setProjectName(rs.getString("p.project_name"));
				vo.setProjectManager(rs.getString("e.emp_name"));
				vo.setRegDate(rs.getTimestamp("p.reg_date").toLocalDateTime());
				vo.setModDate(rs.getTimestamp("p.mod_date").toLocalDateTime());
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) rs.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<ProjectVo> selectProjectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProjectVo> list = new ArrayList<ProjectVo>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String user = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, user, pw);
			
			String sql = "SELECT p.project_id ,p.project_name ,e.emp_name ,p.reg_date ,p.mod_date "
						+ " FROM project p "
						+ " LEFT JOIN employee e "
						+ " ON p.project_manager = e.emp_id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProjectVo vo = new ProjectVo();
				vo.setProjectId(rs.getInt("p.project_id"));
				vo.setProjectName(rs.getString("p.project_name"));
				vo.setProjectManager(rs.getString("e.emp_name"));
				vo.setRegDate(rs.getTimestamp("p.reg_date").toLocalDateTime());
				vo.setModDate(rs.getTimestamp("p.mod_date").toLocalDateTime());
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) rs.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int insertProjectOne(String projectName, String managerName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String user = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, user, pw);
			String sql = "INSERT INTO project(project_name ,project_manager) "
					+ " VALUES(? ,(SELECT emp_id "
					+ "			   FROM employee "
					+ "			   WHERE emp_name = ?)) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, projectName);
			pstmt.setString(2, managerName);
			
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
}
