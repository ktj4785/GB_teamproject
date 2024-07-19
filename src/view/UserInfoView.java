package view;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.UserController;
import model.Session;
import model.dto.UserDTO;

public class UserInfoView {
	public UserInfoView() {
		Scanner sc = new Scanner(System.in);
		UserController ucon = new UserController();
		String loginUser = (String)Session.getData("loginUser");
		HashMap<String, Object> infodata = ucon.getDetail(loginUser);
		//돌려받은 HashMap 안에 있는 데이터 꺼내기
		UserDTO user = (UserDTO)infodata.get("user");
		String balance 	= (String)infodata.get("balance");
		int reserveCnt	= (Integer)infodata.get("reserveCnt");
		System.out.printf("======%8s님의 회원정보======\n",loginUser);
		System.out.println("아이디 : "+user.getUserId());
		System.out.println("비밀번호 : "+user.getUserPw());
		System.out.println("나이 : "+user.getUserAge());
		System.out.println("주민등록 번호 : "+user.getSocialNum());
		System.out.println("핸드폰 번호 : "+user.getPhone());
		System.out.println("주소 : "+user.getUserAddr());
		System.out.println("예약한 영화 수 : " +reserveCnt);
		System.out.println("계좌 잔액 : " +balance);
		System.out.println("==============================");
		while(true) {
			System.out.println("1. 내 정보 수정n2. 예약 관리\n3. 계좌 관리\n4. 메인으로\n5. 회원 탈퇴");
			try {
				int choice = sc.nextInt();
				if(choice == 5) {
					//회원 탈퇴
					System.out.print("비밀번호 재 입력 : ");
					String userpw = sc.next();
					if(user.getUserPw().equals(userpw)) {
						if(ucon.leaveId(loginUser)) {
							System.out.println(loginUser+"님 회원 탈퇴 처리 완료되었습니다.");
							return;
						}
					}
				}
				if(choice == 4) {
					System.out.println("메인으로 돌아갑니다");
					break;
				}
				switch(choice) {
					case 1:
						System.out.println("수정할 정보를 입력하세요");
						System.out.println("1.비밀번호 수정\n2.핸드폰 번호\n3.주소");
						choice = sc.nextInt();
						System.out.println("변경할 내용을 입력하세요");
						String newinfo = sc.next();
						if(ucon.updateInfo(choice,newinfo)) {
							System.out.println("수정에 성공하였습니다");
						}
						else {
							System.out.println("실패하였습니다");
						}
						break;
					case 2:
						break;
					case 3:
						System.out.println("계좌번호 : ");
						System.out.println("은행 : ");
						System.out.println("잔액 : ");
						System.out.println("1.충전하기 2.계좌수정 3.나가기");
						choice=sc.nextInt();
						if(choice==1) {
							System.out.println("충전하실 금액을 입력하세요");
							int money = sc.nextInt();
							if(ucon.depositMoney(money+Integer.parseInt(balance))) {
								System.out.println("충전에 성공하였습니다");
							}
							else {
								System.out.println("실패했습니다");
							}
						}

						
						break;
					default:
				            System.out.println("잘못된 입력입니다. 다시 선택해 주세요.");
				            break;
				}
			} catch (InputMismatchException e) {
				System.out.println("정수만 입력해주세요");
			}
		}
		
		
		
	}
}
