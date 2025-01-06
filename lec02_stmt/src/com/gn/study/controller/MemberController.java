package com.gn.study.controller;

import java.util.ArrayList;
import java.util.List;

import com.gn.study.model.dao.MemberDao;
import com.gn.study.model.vo.Member;

public class MemberController {
	private MemberDao md = new MemberDao();
	
	public int withdrawalMember(String memId, String memPw) {
		return md.withdrawalMember(memId, memPw);
	}
	
	public int updateMemberInfo(String memId, String memPw, String name, String phone, String email) {	
		return md.updateMemberInfo(memId, memPw, name, phone, email);
	}
	
	public List<Member> selectMemberKeywordByName(String keyword) {
		
		List<Member> list = new ArrayList<Member>();
		list = md.selectMemberKeywordByName(keyword);
		
		return list;
	}
	
	public Member selectMemberOne(String id) {
//		Member m = new MemberDao().selectMemberOneId(memberId);
//		return m;
		return md.selectMemberOne(id);
	}
	
	public Member selectMemberOne(String id, String pw) {
		return md.selectMemberOne(id, pw);
	}
	
	public List<Member> selectMemberAll() {
		List<Member> list = md.selectMemberAll();
		return list;
	}
	
	public int insertMember(String memberId, String memberPw, String memberName
			,String memberEmail, String memberPhone, String memberGender) {
		Member m = new Member(memberId, memberPw, memberName, memberEmail, memberPhone, memberGender);
		
		int result = md.insertMember(m);
		
		return result;
	}
	
	
}
