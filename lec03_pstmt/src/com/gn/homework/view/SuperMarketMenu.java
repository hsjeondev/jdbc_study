package com.gn.homework.view;

import java.util.Scanner;

import com.gn.homework.controller.ProductController;
import com.gn.homework.controller.UserController;
import com.gn.homework.model.vo.User;

public class SuperMarketMenu {
	private Scanner scanner = new Scanner(System.in);
	private UserController uc = new UserController();
	private ProductController pc = new ProductController();

	public void mainMenu() {
			User loginedUser = new User();
		
			while(true) {
				System.out.println("\nSuper Market 방문을 환영합니다!");
				
				System.out.println("1. 회원가입");
				System.out.println("2. 로그인");
				System.out.println("9. 나가기");
				System.out.print("메뉴 선택 : ");
				int select = scanner.nextInt();
				scanner.nextLine();
				
				switch(select) {
				case 1 : createAccount(); break;
				case 2 : loginedUser = login(); break;
				case 9 : System.out.println("또 오세요~"); return;
				default : System.out.println("화면에 있는 숫자만 입력해주세요."); break;
			}
		}
	}
	
	public void createAccount() {
		System.out.println("\n=== 회원가입 ===");
		String id = "";
		boolean checkId = false;
		do {
			checkId = false;
			System.out.print("아이디 : ");
			id = scanner.nextLine();
			if(uc.checkUserId(id)) {
				checkId = true;
				System.out.println("중복된 아이디입니다.");
			}
		} while(checkId);
		System.out.print("비밀번호 : ");
		String pwd = scanner.nextLine();
		String checkPwd = "";
		do {
			System.out.print("비밀번호 확인 : ");
			checkPwd = scanner.nextLine();
			if(!pwd.equals(checkPwd)) {
				System.out.println("비밀번호가 다릅니다.");
			}
		} while(!pwd.equals(checkPwd));
		System.out.print("닉네임 : ");
		String nickname = scanner.nextLine();
		
		int result = uc.creatAccount(id, pwd, nickname);
		
		if(result > 0) {
			System.out.println("회원가입이 정상적으로 되었습니다!");
		} else {
			System.out.println("회원가입 중 문제가 생겼습니다. 다시 시도해주세요.");
		}	
	}
	
	public User login() {
		System.out.println("\n=== 로그인 ===");
		
		System.out.print("아이디 : ");
		String id = scanner.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = scanner.nextLine();
		
		User user = uc.login(id, pwd);
		
		if(user != null) {
			if(user.getIsAdmin()) {
				adminMenu();
			} else {
				System.out.println(user.getUserNickname() + "님, 환영합니다!");
				generalUserMenu(id, pwd);
			}
		} else {
			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
		}
		
		return user;
	}
	
	public void adminMenu() {
		System.out.println("\n***** 관리자 메뉴 *****");
		
		while(true) {
			System.out.println("1. 제품 등록");
			System.out.println("2. 제품 입고");
			System.out.println("3. 판매 현황");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 선택 : ");
			int select = scanner.nextInt();
			scanner.nextLine();
			
			switch(select) {
			case 1 : addProduct(); break;
			case 2 :  break;
			case 9 : System.out.println("로그아웃 되었습니다."); return;
			default : System.out.println("관리자가 왜 그러지?\n"); break;
			}
		}
	}
	
	public void stockProduct() {
		
	}
	
	public void addProduct() {
		System.out.println("\n*** 제품 등록 ***");
		
		
		System.out.print("제품명 : ");
		String productName = scanner.nextLine();
		if(pc.checkProduct(productName)) {
			System.out.print("이미 있는 제품입니다. 제품 입고로 이동하시겠습니까? (Y/N): ");
			String answer = scanner.nextLine();
			if(answer.toUpperCase().equals("Y")) {
				stockProduct();
				return;
			} else {
				return;
			}
		}
		System.out.print("제품 가격 : ");
		String productPrice = scanner.nextLine();
		System.out.print("입고 개수 : ");
		String productAmount = scanner.nextLine();
	}
	
	public void generalUserMenu(String id, String pwd) {
		System.out.println("\n===== 일반 사용자 메뉴 =====");
		
//		while(true) {
//			System.out.println("1. 음악 재생");
//			System.out.println("2. 개인 정보 수정");
//			System.out.println("3. 회원 탈퇴");
//			System.out.println("9. 로그아웃");
//			System.out.print("메뉴 선택 : ");
//			int select = scanner.nextInt();
//			scanner.nextLine();
//			
//			switch(select) {
//			case 1 : playSong(); break;
//			case 2 : updateUserProfile(id, pwd); break;
//			case 3 : withdrawUser(id, pwd); break;
//			case 9 : System.out.println("로그아웃 되었습니다."); return;
//			default : System.out.println("화면에 있는 번호에서 선택해주세요."); break;
//			}
	}
}

	

