package view;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import controller.ReserveController;
import controller.UserController;
import model.Session;
import model.dto.ReserveDTO;

public class UserReserveView {
	public UserReserveView(){
		Scanner sc = new Scanner(System.in);
		UserController ucon = new UserController();
		String loginUser = (String)Session.getData("loginUser");
		HashMap<String, Object> infodata = ucon.getDetail(loginUser);
		ArrayList<ReserveDTO> list = (ArrayList<ReserveDTO>)infodata.get("list");
		String balance 	= (String)infodata.get("balance");
		System.out.println("번호	영화		영화시간				극장			인원	가격		좌석		결재여부");
		int count = 1;
		for(ReserveDTO reserve : list) {
//			스케쥴 불러와서 영화시간 시작 시간 적기, 
//			근데 스케쥴 불러올때 그냥 불러오는게 아니고 영화와 극장의 이름도 제대로 데려와야함
			ReserveController rcon = new ReserveController();
			HashMap<String, Object> reserveData = rcon.getReserveDetail(reserve.getScheduleId());
			System.out.print(count+"\t");
			System.out.print((String)reserveData.get("movieName")+"\t\t");
			System.out.print((Timestamp)reserveData.get("scheduleStartTime")+"\t");
			System.out.print((String)reserveData.get("theaterName")+"\t");
			System.out.print(reserve.getpNum()+"\t");
			System.out.print(reserve.getPrice()+"\t");
			System.out.print(reserve.getSeat()+"\t");
			System.out.print(reserve.isPayment()+"\n");
			count++;
		}
		System.out.println("==========================================");
		System.out.println("1.예약 취소 2.결제 3. 나가기");
		int choice = sc.nextInt();
		if (choice==3) {
			return;
		}
		if (choice==1) {
			System.out.println("예약을 취소하시겠습니까?\n 1.예 2.아니오");
			int choice2 = sc.nextInt();
			if(choice2==1) {
				System.out.println("최소할 예약의 번호를 입력하세요");
				int select = sc.nextInt();
				ReserveController rcon = new ReserveController();
				if(rcon.deleteReserve(list.get(select-1),Integer.parseInt(balance))) {
					System.out.println("예약이 취소되었습니다");
					return;
				}
				
			}
			else if(choice2==2) {
				return;
			}
		}
		if (choice==2) {
			System.out.println("결제할 예약의 번호를 입력하세요");
			int choice3 = sc.nextInt();
			ReserveController rcon = new ReserveController();
			int price=list.get(choice3-1).getPrice();
			if(list.get(choice3-1).isPayment()==false) {
				boolean payment = rcon.Reservepayment(price);
				int reserveId = list.get(choice3-1).getReserveId();
				rcon.updateReservePayment(payment,reserveId);
				if(payment==true) {
					System.out.println("결제성공하였습니다");
					return;
				}
				else if(payment==false){
					System.out.println("잔액이 부족합니다");
					return;
				}
			}
			else if(list.get(choice3-1).isPayment()==true)  {
				System.out.println("이미 결제하신 예약입니다");
				return;
			}
//				Integer.parseInt(balance)
		}
	}
}
