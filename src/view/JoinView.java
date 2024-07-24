package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.UserController;
import model.dto.UserDTO;
import model.dto.AccountDTO;

public class JoinView {
	public JoinView() {
		Scanner sc = new Scanner(System.in);
		UserController ucon = new UserController();
		
		System.out.println("아이디 : ");
		String userId = sc.next();
		ucon.checkId(userId);
		if(ucon.checkId(userId)){
			
			System.out.println("비밀번호 : ");
			String userPw = sc.next();
			System.out.println("비밀번호 확인 : ");
			String userPwCheck = sc.next();
			if(userPw.equals(userPwCheck)) {
				System.out.println("이름 : ");
				String userName = sc.next();
				int userAge;
				while(true) {
					try {
						System.out.println("나이 : ");
						userAge = sc.nextInt();
						sc.nextLine();
						break;
					} catch (InputMismatchException e) {
						System.out.println("나이에는 정수형만 입력해주세요");
						sc.next();
					}
				}
				System.out.println("주민번호 : ");
				String socialNum = sc.next();
				System.out.println("전화번호 : ");
				String phone = sc.next();
				sc.nextLine();
				System.out.println("주소 : ");
				String userAddr = sc.nextLine();
				System.out.println("계좌번호를 입력하세요");
				int accountId = sc.nextInt();
				sc.nextLine();
				System.out.println("은행명을 입력하세요");
				String bank = sc.next();
				UserDTO user = new UserDTO(userId, userPw, userName, userAge, socialNum, phone, userAddr);
				AccountDTO account = new AccountDTO(accountId,bank,userId);
//						int accountId, String bank, int balance, String userId
				if(ucon.join(user,account)) {
					System.out.println("회원가입을 성공하였습니다");
					return;
				}
				else {
					System.out.println("실패하였습니다.");
				}
			}
			else {
				System.out.println("비밀번호를 다시 입력해주세요.");
			}
		}
		else {
			System.out.println("중복된 아이디 입니다.");
		}
		
		
	}
}
