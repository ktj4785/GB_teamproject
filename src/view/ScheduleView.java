package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MovieController;
import model.dto.ScheduleDTO;

//상영시간표 조회(영화별, 시간별, 영화관별)
//영화별: 영화이름, 감독, 러닝타임, 장르, 평점
//시간별(영화, 영화관): 영화이름, 영화관이름, dimension, 영화 시작 시작, 잔여좌석
//영화관별:영화관이름, dimension, 현재 날짜, 영화이름, 영화 시작 시작, 러닝타임, 장르, 잔여좌석
// 
public class ScheduleView {
	public ScheduleView() {
		Scanner sc = new Scanner(System.in);
		MovieController controller = new MovieController();
		
		System.out.println("==== 상영시간표 ====");
		System.out.print("1.영화별 조회\n2.시간별 조회\n3.영화관별 조회");
		int choice = sc.nextInt();
		System.out.print("1.오름차순으로 보기\n2.내림차순으로 보기");
		int asc = sc.nextInt();
		
		ArrayList<ScheduleDTO> list = controller.getScheduleList(choice, asc);
		
		if(list == null || list.isEmpty()) {
			System.out.println("검색된 결과가 없습니다.");
		} else {
			System.out.println("--- 검색결과 ---");
			switch(choice) {
			case 1:
				for(ScheduleDTO sd : list) {
					System.out.printf("%s | %s감독 | %s | %s | %.1f\n", 
							sd.getMovieName(), sd.getDirector(), sd.getRunningTime(), sd.getGenre(), sd.getAvgScore());
				}
				break;
			case 2:
				for(ScheduleDTO sd : list) {
					System.out.printf("%s | %s | %s | %s | %d\n",
							sd.getMovieName(), sd.getTheaterName(), sd.getDimension(), sd.getStartTime().toString(), sd.getLeftSeat());
				}
				break;
			case 3:
				 for(ScheduleDTO sd : list) {
		               System.out.printf("%s | %sD | %s | %s | 잔여 %d석 | %s | %s\n",
		                     sd.getTheaterName(), sd.getDimension(), sd.getMovieName(), sd.getStartTime().toString(), sd.getLeftSeat(), sd.getGenre(), sd.getRunningTime());
		            }
				break;
				
			default:
					System.out.println("잘못된 선택입니다.");
			break;
			}
		}
		System.out.println();
		
	}
}
