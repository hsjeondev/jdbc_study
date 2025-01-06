package com.gn.homework.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gn.homework.controller.SongController;
import com.gn.homework.controller.UserController;
import com.gn.homework.model.vo.Song;
import com.gn.homework.model.vo.User;

public class WatermelonMusicMenu {
	private Scanner scanner = new Scanner(System.in);
	private UserController uc = new UserController();
	private SongController sc = new SongController();

	public void mainMenu() {
		
		while(true) {
			System.out.println("\nWatermelon Music 방문을 환영합니다!");
			
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("9. 나가기");
			System.out.print("메뉴 선택 : ");
			int select = scanner.nextInt();
			scanner.nextLine();
			
			switch(select) {
			case 1 : createAccount(); break;
			case 2 : login(); break;
			case 9 : System.out.println("다음에 또 만나요~"); return;
			default : System.out.println("화면에 있는 숫자만 입력해주세요."); break;
			}
		}
	}
	
	public void login() {
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
				System.out.println(user.getUserName() + "님, 환영합니다!");
				generalUserMenu(id, pwd);
			}
		} else {
			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
		}
	}
	
	public void adminMenu() {
		System.out.println("\n***** 관리자 메뉴 *****");
		
		while(true) {
			System.out.println("1. 음악 추가");
			System.out.println("2. 음악 인기 순위 조회");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 선택 : ");
			int select = scanner.nextInt();
			scanner.nextLine();
			
			switch(select) {
			case 1 : addSong(); break;
			case 2 : topTen(); break;
			case 9 : System.out.println("로그아웃 되었습니다."); return;
			default : System.out.println("관리자가 왜 그러지?\n"); break;
			}
		}
		
	}
	
	public void addSong() {
		System.out.println("\n*** 음악을 추가합니다. ***");
		
		System.out.print("곡 제목 : ");
		String songTitle = scanner.nextLine();
		System.out.print("아티스트 : ");
		String songArtist = scanner.nextLine();
		int result = sc.addSong(songTitle, songArtist);
		
		if(result > 0) {
			System.out.println("음악이 성공적으로 추가 되었습니다.\n");
		} else {
			System.out.println("음악 추가 중 문제가 생겼습니다. 코드를 확인해주세요.\n");
		}
	}
	
	public void topTen() {
		System.out.println("\n*** 인기 순위 Top 10 ***");
		
		List<Song> songList = sc.topTen();
		
		if(songList.isEmpty() == false) {
			for(int i = 0; i < songList.size(); i++) {
				if(i == songList.size()-1) {
					System.out.println((i+1) + "위 " +songList.get(i).printRank() + "\n");
				} else {
					System.out.println((i+1) + "위 " + songList.get(i).printRank());
				}
			}
		} else {
			System.out.println("곡이 없습니다.");
		}
		
	}
	
	public void generalUserMenu(String id, String pwd) {
		System.out.println("\n===== 일반 사용자 메뉴 =====");
		
		while(true) {
			System.out.println("1. 음악 재생");
			System.out.println("2. 개인 정보 수정");
			System.out.println("3. 회원 탈퇴");
			System.out.println("9. 로그아웃");
			System.out.print("메뉴 선택 : ");
			int select = scanner.nextInt();
			scanner.nextLine();
			
			switch(select) {
			case 1 : playSong(); break;
			case 2 : updateUserProfile(id, pwd); break;
			case 3 : withdrawUser(id, pwd); break;
			case 9 : System.out.println("로그아웃 되었습니다."); return;
			default : System.out.println("화면에 있는 번호에서 선택해주세요."); break;
			}
		}
	}
	
	public void playSong() {
		List<Song> songs = new ArrayList<Song>();
		
		songs = sc.songList();
		
		System.out.println("곡 정렬");
		if(songs.isEmpty() == false) {
			for(int i = 0; i < songs.size(); i++) {
				if(i == songs.size()-1) {
					System.out.println(songs.get(i).printSongs() + "\n");
				} else {
					System.out.println(songs.get(i).printSongs());
				}
			}
		} else {
			System.out.println("곡이 없습니다.");
		}
		
		
		System.out.print("곡 번호 선택 : ");
		int select = scanner.nextInt();
		
		Song choseSong = sc.choseSong(select);
		
		System.out.println("노래를 재생합니다.\n");
		System.out.println("제목 : " + choseSong.getSongTitle() + " 가수 : " + choseSong.getSongArtist());
		String lyrics = choseSong.getSongLyrics();
		if (lyrics != null) {
		    try {
		        Thread.sleep(1000);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }

		    String[] lines = lyrics.split("\n");
		    for (String line : lines) {
		        System.out.println(line);
		        try {
		            Thread.sleep(2000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		} else {
		    try {
		        Thread.sleep(1000);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }

		    for (int i = 0; i < 5; i++) {
		        System.out.print("♪ ");
		        try {
		            Thread.sleep(1300);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		}

		System.out.println("\n노래를 종료합니다.\n");
	}
	
	public void updateUserProfile(String id, String pwd) {
		System.out.println("\n=== 개인 정보 수정 ===");
		
		System.out.println("1. 비밀번호 변경");
		System.out.println("2. 이름 변경");
		System.out.print("메뉴 선택 : ");
		int select = scanner.nextInt();
		scanner.nextLine();
		
		switch(select) {
		case 1 : changePwd(id, pwd); break;
		case 2 : changeName(id, pwd); break;
		default : System.out.println("화면에 있는 번호에서 선택해주세요."); break;
		}
	}
	
	public void changePwd(String id, String pwd) {
		System.out.print("현재 비밀번호 : ");
		String checkPwd = scanner.nextLine();
		
		String checkChangePassword = "";
		if(pwd.equals(checkPwd)) {
			System.out.print("변경할 비밀번호 : ");
			String changePassword = scanner.nextLine();
			do {
				System.out.print("비밀번호 확인 : ");
				checkChangePassword = scanner.nextLine();
				if(!changePassword.equals(checkChangePassword)) {
					System.out.println("비밀번호가 다릅니다.\n");
				}
			} while(!changePassword.equals(checkChangePassword));
			
			int result = uc.changePwd(id, pwd, changePassword);
			if(result > 0) {
				System.out.println("정상적으로 변경되었습니다.\n");
			} else {
				System.out.println("비밀번호 변경 중 문제가 생겼습니다. 다시 시도해주세요.");
			}
			
		} else {
			System.out.println("비밀번호가 틀렸습니다.\n");
		}
	}
	
	public void changeName(String id, String pwd) {
		System.out.println("\n=== 이름 변경 ===");
		
		// 1. 이름 체크하고 보여주기
		String oldName = uc.checkName(id, pwd);
		System.out.println("현재 이름 : " + oldName);
		// 2. 변경할 이름 받기
		System.out.print("변경할 이름 : ");
		String newName = scanner.nextLine();
		// 3. 변경 후 결과 출력
		int result = uc.changeName(id, pwd, newName);
		if(result > 0) {
			System.out.println("이름이 정상적으로 변경 되었습니다\n");
		} else {
			System.out.println("이름 변경 중 문제가 생겼습니다. 다시 시도해주세요.");
		}
	}
	
	public void withdrawUser(String id, String pwd) {
		System.out.println("\n=== 회원 탈퇴 ===");
		
		System.out.print("비밀번호 : ");
		String checkPwd = scanner.nextLine();
		
		if(pwd.equals(checkPwd)) {
			System.out.print("정말로 탈퇴하시겠습니까? : ");
			String answer = scanner.nextLine();
			if(answer.toUpperCase().equals("Y")) {
				int result = uc.withdrawUser(id, pwd);
				if(result > 0) {
					System.out.println("정상적으로 탈퇴 되었습니다.\n");
				} else {
					System.out.println("탈퇴 중 문제가 생겼습니다. 다시 시도해주세요.");
				}
			}
		} else {
			System.out.println("비밀번호가 틀렸습니다.");
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
		System.out.print("이름 : ");
		String name = scanner.nextLine();
		
		int result = uc.creatAccount(id, pwd, name);
		
		if(result > 0) {
			System.out.println("회원가입이 정상적으로 되었습니다!");
		} else {
			System.out.println("회원가입 중 문제가 생겼습니다. 다시 시도해주세요.");
		}
		
	}
}
