package com.gn.study.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gn.study.controller.MemberController;
import com.gn.study.model.vo.Member;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	// View :  사용자와의 상호작용
	public void mainMenu() {
		System.out.println("환영합니다!!");
		// 사용자에게 정보 입력받아서
		// Controller에게 전달
		while(true) {
			System.out.println("\n=== 회원 관리 프로그램 ===");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
				case 1 : createMember(); break;
				case 2 : selectMemberAll(); break;
				case 3 : selectMemberOneById(); break;
				case 4 : selectMemberKeywordByName(); break;
				case 5 : updateMember(); break;
				case 6 : withdrawalMember(); break;
				// 리턴은 종료 시점을 강제로 할 때만 사용. 아무 곳이나 사용하면 안 됨.
				case 0 : System.out.println("잘가요~안녕~"); return; 
				default : System.out.println("잘못된 번호입니다."); break;
			}
		}
	}
	
	// 회원 탈퇴
	public void withdrawalMember() {
		System.out.println("\n=== 회원 탈퇴 ===");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
		Member m = mc.selectMemberOne(id, pw);
		
		if(m != null) {
			System.out.print("정말 탈퇴하시겠습니까? : ");
			String answer = sc.nextLine();
			if(answer.toUpperCase().equals("Y")) {
				int result = mc.withdrawalMember(id, pw);
				if(result > 0) {
					System.out.println("정상적으로 탈퇴 되었습니다.");
				} else {
					System.out.println("탈퇴 중 문제가 생겼습니다. 다시 시도해주세요.");
				}
			} else {
				System.out.println("남아주셔서 감사합니다.");
			}
		} else {
			System.out.println("해당 사용자가 업습니다.");
		}
	}
	
	// 회원 정보 수정
	public void updateMember() {
		// 관리자 -> 모든 회원 정보 수정
		// 사용자 -> 내것만 수정
		System.out.println("\n=== 회원 정보 수정 ===");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
		Member m = mc.selectMemberOne(id, pw);
		if(m != null) {
			System.out.println(m);
			// 이메일, 전화번호, 이름 -> 수정
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("전화번호 : ");
			String phone = sc.nextLine();
			System.out.print("이메일 : ");
			String email = sc.nextLine();
			int result = mc.updateMemberInfo(id, pw, name, phone, email);
			if(result > 0) {
				System.out.println("수정 성공!");
			} else {
				System.out.println("수정 실패ㅠㅠ");
			}
		} else {
			System.out.println("잘못된 아이디 혹은 비밀번호입니다.");
		}
	}
	
	// 키워드에 해당하는 이름 회원 조회
	public void selectMemberKeywordByName() {
		System.out.println("\n=== 회원 이름으로 키워드 검색 ===");
		System.out.print("키워드 : ");
		String keyword = sc.nextLine();
		
		List<Member> list = new ArrayList<Member>();
		
		list = mc.selectMemberKeywordByName(keyword);
		
		if(list.isEmpty() == false) {
			for(int i = 0; i < list.size(); i++) {
				if(i == list.size()-1) {
					System.out.println(list.get(i).printMember() + "\n");
				} else {
					System.out.println(list.get(i).printMember());
				}
			}
		} else {
			System.out.println(keyword + "에 해당하는 이름이 없습니다.");
		}
	}
	
	// 아이디 기준 회원 조회
	public void selectMemberOneById() {
		System.out.println("\n=== 회원 아이디 검색 ===");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		// WHERE -> =(UNIQUE / X) / LIKE
		
		Member m = mc.selectMemberOne(id);
		
		if(m != null) {
			System.out.println(m);
		} else {
			System.out.println(id + "는 존재하지 않는 정보입니다.");
		}
		
	}
	
	//회원 전체 조회
	public void selectMemberAll() {
		System.out.println("\n=== 회원 전체 조회 ===");
		
		List<Member> list = mc.selectMemberAll();
		// (1) 만약에 list가 비어있다면 -> 조회된 결과가 없습니다.
		// (2) Member 목록 출력
		if(list.isEmpty() == false) {
			for(int i = 0; i < list.size(); i++) {
				if(i == list.size()-1) {
					System.out.println(list.get(i) + "\n");
				} else {
					System.out.println(list.get(i));
				}
			}
		} else {
			System.out.println("회원이 없습니다. 저희의 새로운 회원이 되어주세요.");
		}
	}
	
	// 회원 추가 화면
	public void createMember() {
		System.out.println("\n=== 회원 정보 추가 ===");
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPw = sc.nextLine();
		System.out.print("이름 : ");
		String memberName = sc.nextLine();
		System.out.print("이메일 : ");
		String memberEmail = sc.nextLine();
		System.out.print("전화번호(-빼고 입력) : ");
		String memberPhone = sc.nextLine();
		System.out.print("성별:");
		String memberGender = sc.nextLine();
		
		int result = mc.insertMember(memberId, memberPw, memberName, memberEmail, memberPhone, memberGender);
		if(result > 0) {
			System.out.println("성공!!");
		} else {
			System.out.println("실패ㅠㅠ");
		}
	}
	
	
}
