package com.gn.homework.model.vo;

import java.time.LocalDateTime;

public class User {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userNickname;
	private boolean isAdmin;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	
	public User() {}

	public User(int userNo, String userId, String userPwd, String userNickname, String isAdmin, LocalDateTime regDate,
			LocalDateTime modDate) {
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userNickname = userNickname;
		if(isAdmin.toUpperCase().equals("Y")) {
			this.isAdmin = true;
		} else {
			this.isAdmin = false;
		}
		this.regDate = regDate;
		this.modDate = modDate;
	}
	
	public User(String userId, String userPwd, String userNickname) {
		this.userId = userId;
		this.userPwd = userPwd;
		this.userNickname = userNickname;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
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

	public boolean getIsAdmin() {
		return isAdmin;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userNickname="
				+ userNickname + ", isAdmin=" + isAdmin + ", regDate=" + regDate + ", modDate=" + modDate + "]";
	}
}
