package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MovieController;
import model.dto.TheaterDTO;

//영화관 검색(위치)
//"CGV강남"으로 검색된 결과
//서울특별시 강남구 역삼동 814-6 스타플렉스 | cgv강남1관 | 74석
public class TheaterSearchView {
	public TheaterSearchView() {
		MovieController controller = new MovieController();
		Scanner sc = new Scanner(System.in);
		
		//입력
		System.out.print("검색할 영화관을 입력하세요: ");
		String theaterName = sc.nextLine();
		
		//처리
		ArrayList<TheaterDTO> theaterList = controller.search(theaterName);
		
		System.out.println("\"" + theaterName + "\" 로 검색된 결과");
		if(theaterName == null) {
			System.out.println("검색된 결과가 없습니다.");
		} else {
			for(TheaterDTO th : theaterList) {
				
				System.out.printf("%d. %s | %s | %d석 | %sD\n",
						th.getTheaterId(),
						th.getTheaterAddr(),
						th.getTheaterName(),
						th.getSeatCnt(),
						th.getDimension()
				);
			}
		}
		System.out.println();		
	}
}
