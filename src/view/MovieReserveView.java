package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import controller.ReserveController;
import model.dto.ScheduleDTO;
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
				ArrayList<String> seatList = new ArrayList<>();
				System.out.println("========예약 진행========");
				System.out.print("예약하실 인원 수를 적어주세요 : ");
				int pNum = sc.nextInt();
				
				String seat = "A1";
				int price = 14000;
				
				boolean payment = false;
				
				
				
				boolean rescerve = rcon.reserveInfo(pNum, price, payment, scheduleId, seat);
				
				if(rescerve) {
					System.out.println("예약에 성공하였습니다.");
				}
				else {
					System.out.println("예약에 실패하였습니다.");
				}
				
//				ScheduleDTO reseveSchedule = controller.getScheduleByScheduleId(scheduleId);
//				TheaterDTO scheduleTheater = controller.getTheaterByScheduleId(reseveSchedule.getTheaterId());
				
//				if(reseveSchedule.getLeftSeat() < scheduleTheater.getSeatCnt()) {
//					
//				}
				
//				
//				for (int i = 0; i < pNum; i++) {
//					System.out.print("예약하실 좌석의 행을 입력해 주세요(A ~ E): ");
//					String seatLine = sc.next();
//					System.out.print("예약하실 좌석의 열을 입력해 주세요(1 ~ 10): ");
//					int seatRow = sc.nextInt();
//					String seat = seatLine + "" + seatRow;
//					
//					seatList.add(seat);
//				}
				
				
				
			}
		}
	}
}
