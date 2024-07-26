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
			System.out.println("=======상영예정 일정=======");
			for(int i = 0; i < schedule.size(); i++) {
				System.out.printf("%d번 | 시작시간 : %s | 종료시간 : %s | 남은 좌석 : %d석 | %s | 주소 : %s",
						schedule.get(i).getScheduleId(), schedule.get(i).getStartTime(), schedule.get(i).getEndTime(),
						schedule.get(i).getLeftSeat(), theater.get(i).getTheaterName(), theater.get(i).getTheaterAddr());
			}
			System.out.println("=====================");
			System.out.println("예약하실 영화 번호 입력(나가시려면 0번을 입력하세요) : ");
			int scheduleId = sc.nextInt();
			if(scheduleId != 0) {
				System.out.println("========예약 진행========");
				System.out.print("예약하실 인원 수를 적어주세요 : ");
				int pNum = sc.nextInt();
				
//				String seat = "A1";

				ScheduleDTO reseveSchedule = rcon.getScheduleByScheduleId(scheduleId);
				
				ArrayList<SeatDTO> seatList = rcon.getSeatList(scheduleId);
				ArrayList<String> reserveSeat = rcon.getReserveSeat(scheduleId);
				
				if(reseveSchedule.getLeftSeat()>0) {
					int rowCnt = seatList.size();
					System.out.println(rowCnt);
					
					System.out.print("\t");
					for(int i=0; i<Integer.parseInt(seatList.get(0).getSeatLine()); i++){
						System.out.print(i);
					}
					System.out.print("\n");
					for(int i= 0 ;i< rowCnt; i++ ) {
						
						System.out.print(seatList.get(i).getSeatRow()+"열\t");
						
						for(int j= 0 ;j < Integer.parseInt(seatList.get(i).getSeatLine()); j++) {
							
							if(reserveSeat!=null) {
								if(reserveSeat.contains(seatList.get(i).getSeatRow()+j)) {
									
									System.out.print("■");
									
									continue;
							}
							}
							System.out.print("□");
						}
						System.out.print("\n");
					}
					
				}
				String seat = "";
				int price = 0;
				for (int i = 0; i < pNum; i++) {
					System.out.print("예약하실 좌석의 행을 입력해 주세요: ");
					String seatLine = sc.next().toUpperCase();
					System.out.print("예약하실 좌석의 열을 입력해 주세요: ");
					int seatRow = sc.nextInt();
					seat += seatLine+seatRow;
					System.out.println(seatLine.charAt(0)-65);
					price += (seatList.get(seatLine.charAt(0)-65)).getSeatPrice();
					if(i==pNum-1) {
						continue;
					}
					seat +=":";
				}
				System.out.println(seat);
				System.out.println(price);
//				비용 지불하는 메소드 만들어서 입력할것!!!
				boolean payment = false;
				
				
				if(rcon.reserveInfo(pNum, price, payment, scheduleId, seat)) {
					System.out.println("예약에 성공하였습니다.");
				}
				else {
					System.out.println("예약에 실패하였습니다.");
				}
				
				
				
				
				
				
				
				
				
				
			}
		}
	}
}