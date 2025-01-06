package com.gn.homework.controller;

import com.gn.homework.model.dao.UserDao;
import com.gn.homework.model.vo.User;

public class UserController {
	private UserDao ud = new UserDao();
	
	public int changePwd(String id, String pwd, String changePwd) {
		return ud.changePwd(id, pwd, changePwd);
	}
	
	public int withdrawUser(String id, String pwd) {
		return ud.withdrawUser(id, pwd);
	}
	
	public User login(String id, String pwd) {
		return ud.login(id, pwd);
	}
	
	public boolean checkUserId(String id) {
		boolean result = false;
		
		if(ud.checkUserId(id)) {
			result = true;
		}
		
		return result;
	}

	public int creatAccount(String userId, String userPwd, String userName) {
		return ud.createAccount(userId, userPwd, userName);
	}
}
