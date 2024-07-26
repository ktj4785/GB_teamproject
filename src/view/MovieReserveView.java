package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import controller.ReserveController;
import model.dto.ScheduleDTO;
import model.dto.SeatDTO;
import model.dto.TheaterDTO;

public class MovieReserveView {
	public MovieReserveView(int movieId) {
		Scanner sc = new Scanner(System.in);
		ReserveController rcon = new ReserveController();
		
		HashMap<String, Object> list = rcon.getmovieDetail(movieId);
		
		ArrayList<ScheduleDTO> schedule = (ArrayList<ScheduleDTO>)list.get("schedule");
		ArrayList<TheaterDTO> theater = (ArrayList<TheaterDTO>) list.get("theater");
		
		if(schedule == null) {
			System.out.println("상영예정 일정이 없습니다.");
		}
		else {
			int count = 0;
			System.out.println("=======상영예정 일정=======");
			for(int i = 0; i < schedule.size(); i++) {
//				if(schedule.get(i).getStartTime()<nowtime) {
//					continue;
//				}
				System.out.printf("%d번 | 시작시간 : %s | 종료시간 : %s | 남은 좌석 : %d석 | %s | 주소 : %s",
						count, schedule.get(i).getStartTime(), schedule.get(i).getEndTime(),
						schedule.get(i).getLeftSeat(), theater.get(i).getTheaterName(), theater.get(i).getTheaterAddr());
				count++;
			}
			System.out.println("=====================");
			System.out.println("예약하실 영화 번호 입력(나가시려면 0번을 입력하세요) : ");
			int choice2 = sc.nextInt();
			if(choice2 != 0) {
				System.out.println("========예약 진행========");
				System.out.print("예약하실 인원 수를 적어주세요 : ");
				int pNum = sc.nextInt();

				int leftSeat = schedule.get(choice2-1).getLeftSeat();
				int scheduleId = schedule.get(choice2-1).getScheduleId();
				ArrayList<SeatDTO> seatList = rcon.getSeatList(scheduleId);
				//각 영화관의 좌석 정보 테이블을 불러옴
				ArrayList<String> reserveSeat = rcon.getReserveSeat(scheduleId);
				//예약테이블로부터 예약된 좌석의 리스트를 불러옴
				if(leftSeat>pNum) {
					int rowCnt = seatList.size();
					//seatlist의 크기가 곧 행의 갯수임
					
					System.out.print("\t");
					for(int i=0; i<Integer.parseInt(seatList.get(0).getSeatLine()); i++){
						System.out.print(i+1);
					}
					System.out.print("\n");
					//여기까지가 맨위의 1~10까지 좌석 번호 띄워줌
					for(int i= 0 ;i< rowCnt; i++ ) {
						//ABCDE~행을 돌도록
						System.out.print(seatList.get(i).getSeatRow()+"열\t");
						//앞에 A행 이런식으로 나오도록
						for(int j= 0 ;j < Integer.parseInt(seatList.get(i).getSeatLine()); j++) {
							//각 행의 크기만큼 반복
							if(reserveSeat!=null) {
								if(reserveSeat.contains(seatList.get(i).getSeatRow()+j)) {
									//좌석들을 돌면서 예약좌석과 같은게 있다면 ■로 예약이 이미 있는 자리임을 나타냄
									System.out.print("■");
									
									continue;
							}
							}
							System.out.print("□");
							//빈자리 표시
						}
						System.out.print("\n");
						//한 행 다 돌고 줄바꿈
					}
					
				}
				else if(schedule.get(scheduleId).getLeftSeat()<pNum) {
					System.out.println("예약가능한 좌석이 없습니다");
					return;
				}
				
				String seat = "";
				int price = 0;
				for (int i = 0; i < pNum; i++) {
					System.out.print("예약하실 좌석의 행을 입력해 주세요: ");
					String seatLine = sc.next().toUpperCase();
					System.out.print("예약하실 좌석의 열을 입력해 주세요: ");
					int seatRow = sc.nextInt();
					if(reserveSeat.contains(seatLine+seatRow)) {
						System.out.println("이미 예약된 좌석입니다");
						i--;
//						예약된 좌석인지 확인하고 예약된 좌석이라면 반복 횟수가 소모되었으므로 i--을 통해
//						그 다음부터 정상적으로 하도록 진행
						continue;
					}
					
					
					seat += seatLine+seatRow;
//					좌석 정보를 이어붙임
					price += (seatList.get(seatLine.charAt(0)-65)).getSeatPrice();
//					좌석 가격을 계속 더함
					//아스키코드를 이용해서 A라면 -65로  seatList.get(0)로 A열의 좌석 정보를 가져옴
					//seatList는 ABCD~이런순으로 정렬되도록 해놨음
					if(i==pNum-1) {
						continue;
					}
					seat +=":";
					//마지막엔 : 가 안 붙도록함
				}
				boolean payment;
				while(true){
					System.out.println("결제를 진행하시겠습니까?\n1.예	2.아니오");
					int choice = sc.nextInt();
					if(choice==1) {
						payment=rcon.Reservepayment(price);
//						잔액이 금액보다 크면 true 아니면 false
						if(payment==false) {
							System.out.println("잔액이 부족합니다. 충전을 해주세요");
						}
						break;
					}
					if(choice==2) {
						payment = false;
						break;
					}
				}
				if(rcon.reserveInfo(pNum, price, payment, scheduleId, seat)) {
					System.out.println("예약에 성공하였습니다.");
					rcon.updateLeftSeat(leftSeat-pNum,scheduleId);
					//예약에 남은 좌석 수 수정
				}
				else {
					System.out.println("예약에 실패하였습니다.");
				}
				return;
			}
		}
	}
}