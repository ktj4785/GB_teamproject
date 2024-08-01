package view;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.ReserveController;
import controller.UserController;
import model.Session;
import model.dto.AccountDTO;
import model.dto.ReserveDTO;
import model.dto.UserDTO;

public class UserInfoView {
	public UserInfoView() {
		Scanner sc = new Scanner(System.in);
		UserController ucon = new UserController();
		String loginUser = (String)Session.getData("loginUser");
		HashMap<String, Object> infodata = ucon.getDetail(loginUser);
		//돌려받은 HashMap 안에 있는 데이터 꺼내기
		UserDTO user = (UserDTO)infodata.get("user");
		AccountDTO account = (AccountDTO)infodata.get("account");
		
		String balance 	= (String)infodata.get("balance");
		int reserveCnt	= (Integer)infodata.get("reserveCnt");
		System.out.printf("======%8s님의 회원정보======\n",loginUser);
		System.out.println("아이디 : "+user.getUserId());
		System.out.println("비밀번호 : "+user.getUserPw());
		System.out.println("이름 : "+user.getUserName());
		System.out.println("나이 : "+user.getUserAge());
		System.out.println("주민등록 번호 : "+user.getSocialNum());
		System.out.println("핸드폰 번호 : "+user.getPhone());
		System.out.println("주소 : "+user.getUserAddr());
		System.out.println("예약한 영화 수 : " +reserveCnt);
		System.out.println("계좌 잔액 : " +balance);
		System.out.println("==============================");
		while(true) {
			infodata = ucon.getDetail(loginUser);
			//돌려받은 HashMap 안에 있는 데이터 꺼내기
			user = (UserDTO)infodata.get("user");
			account = (AccountDTO)infodata.get("account");
			balance 	= (String)infodata.get("balance");
			reserveCnt	= (Integer)infodata.get("reserveCnt");
			
			System.out.println("1. 내 정보 수정\n2. 예약 관리\n3. 계좌 관리\n4. 메인으로\n5. 회원 탈퇴");

			try {
				int choice = sc.nextInt();
				if(choice == 5) {
					//회원 탈퇴
					System.out.print("비밀번호 재 입력 : ");
					String userpw = sc.next();
					//비번을 바꾼 경우 데이터베이스의 정보와
					//현재 메소드의 정보가 틀리기 때문에 다시 불러와야함
					if(user.getUserPw().equals(userpw)) {
						if(ucon.leaveId(loginUser)) {
							System.out.println(loginUser+"님 회원 탈퇴 처리 완료되었습니다.");
							Session.setData("loginUser", null);
							return;
						}

					}
					else {
						System.out.println("비밀번호가 틀렸습니다");
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
						if(reserveCnt==0) {
							System.out.println("예약된 영화가 없습니다.");
							break;
						}
						new UserReserveView();
						break;
					case 3:
						System.out.println("계좌번호 : "+account.getAccountId());
						System.out.println("은행 : "+account.getBank());
						System.out.println("잔액 : "+account.getBalance());

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
						if(choice==2) {
//						계좌번호와 은행을 수정하는 뷰
							System.out.println("수정하실 계좌의 정보를 고르세요\n 1.계좌 2.은행3.나가기");
							int choice3 = sc.nextInt();
							if(choice3==3) {
								break;
							}
							System.out.println("수정할 내용을 입력하세요");
							String newdata = sc.next();
							if(choice3==1) {
								if(ucon.checkAccount(newdata)) {
									System.out.println("중복된 계좌가 존재합니다");
									break;
								}
							}
							if(ucon.updateAccount(choice3,newdata)) {
								System.out.println("수정되었습니다");
							}
						}
						
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("정수만 입력해주세요");
				sc.next();

			}
		}
		
		
		
	}
}