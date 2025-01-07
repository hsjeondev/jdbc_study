package com.gn.study.model.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectVo {
	// 필드 옆에 데이터베이스 연결명 주석 권장
	private int projectId; // PK(번호)
	private String projectName; // 프로젝트명
	private String projectManager; 
	private int projectManagerId; // FK(employee의 emp_id) -> 관리자 사번
	private LocalDateTime regDate; // 등록일
	private LocalDateTime modDate; // 수정일
	
	public ProjectVo() {}

	public ProjectVo(int projectId, String projectName, String projectManager, int projectManagerId, LocalDateTime regDate,
			LocalDateTime modDate) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectManager = projectManager;
		this.projectManagerId = projectManagerId;
		this.regDate = regDate;
		this.modDate = modDate;
	}
	
	public int getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(int projectManagerId) {
		this.projectManagerId = projectManagerId;
	}

	public ProjectVo(int projectId, String projectName, String projectManager) {
		this.projectId = projectId;
		this.projectName = projectName;
		if(projectManager == null) {
			this.projectManager = "미정";
		} else {
			this.projectManager = projectManager;
		};
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		if(projectManager == null) {
			this.projectManager = "미정";
		} else {
			this.projectManager = projectManager;
		}
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public LocalDateTime getModDate() {
		return modDate;
	}

	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd(E)");
		return "[번호 : " + projectId
				+ ", 이름 : " + projectName 
				+ ", 관리자 : "+ projectManager + "(" + projectManagerId + ")"
				+ ", 등록일 : " + dtf.format(regDate)
				+ ", 수정일 : " + dtf.format(modDate) + "]";
	}
	
}
