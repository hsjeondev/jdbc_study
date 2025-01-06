package com.gn.homework.model.vo;

import java.time.LocalDateTime;

public class User {

	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private LocalDateTime regDate;
	private boolean isAdmin;
	
	public User() {}

	public User(int userNo, String userId, String userPwd, String userName, LocalDateTime regDate, String isAdmin) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.regDate = regDate;
		if(isAdmin.equals("Y")) {
			this.isAdmin = true;
		} else {
			this.isAdmin = false;
		}
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", regDate=" + regDate + ", isAdmin=" + isAdmin + "]";
	}
	
}
