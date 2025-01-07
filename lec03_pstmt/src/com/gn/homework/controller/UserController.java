package com.gn.homework.controller;

import com.gn.homework.model.dao.UserDao;
import com.gn.homework.model.vo.User;

public class UserController {
	private UserDao ud = new UserDao();
	
	public int creatAccount(String userId, String userPwd, String userNickname) {
		return ud.createAccount(userId, userPwd, userNickname);
	}
	
	public boolean checkUserId(String id) {
		boolean result = false;
		
		if(ud.checkUserId(id)) {
			result = true;
		}
		
		return result;
	}
	
	public User login(String id, String pwd) {
		return ud.login(id, pwd);
	}
}
