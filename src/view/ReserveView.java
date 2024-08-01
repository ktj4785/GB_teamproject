package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ReserveController;
import model.dto.ScheduleDTO;

public class ReserveView {
	public ReserveView(){
		Scanner sc = new Scanner(System.in);
		System.out.println("선택지를 입력하세요");
		System.out.println("1.키워드로 검색\n2.영화 검색\n3.영화관으로 검색\n4.영화목록 보기\n5.스케쥴 목록\n6.나가기");
		int choice = sc.nextInt();
		if(choice==6) {
			return;
		}
		switch(choice) {
			case 1:
				new KeywordSearchView();
				break;
			case 2:
				new MovieSearchView();	
				break;
			case 3:
				new TheaterSearchView();
				break;
			case 4:
				new MovieListView();
				break;
			case 5:
				ReserveController rcon = new ReserveController();
				ArrayList<ScheduleDTO> list = rcon.getSchedule();
				int count = 1;
				for(ScheduleDTO schedule : list) {
					System.out.printf("%d번 | 제목 : %s | 시작시간 : %s | 남은 좌석 : %d석 | 영화관 : %s\n",
							count,schedule.getMovieName(),schedule.getStartTime(),
							schedule.getLeftSeat(),schedule.getTheaterName());
					count++;
				}
				System.out.println("예약할 영화의 번호를 고르세요");
				int choice2 = sc.nextInt();
				new MovieReserveView(null,null,list.get(choice2-1).getScheduleId());
		}
	}
}
