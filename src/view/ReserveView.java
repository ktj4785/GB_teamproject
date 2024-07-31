package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ReserveController;
import model.dto.MovieDTO;

public class ReserveView {
	public ReserveView() {
		Scanner sc = new Scanner(System.in);
		System.out.print("예약하실 영화 검색 : ");
		String keyword = sc.nextLine();

		ReserveController controller = new ReserveController();
		ArrayList<MovieDTO> mList = controller.searchMovieList(keyword);

		System.out.println("\""+keyword+"\" 로 검색된 결과");
		if(mList == null) {
			System.out.println("검색된 결과가 없습니다.");
		}
		else {
			for(MovieDTO movie : mList) {
				System.out.printf("%d번 제목 : %s | 감독 : %s | 상영시간 : %s | 장르 : %s | 평점 : %f점\n",
						movie.getMovieId(), movie.getMovieName(), movie.getDirector(),
						movie.getRunningTime(), movie.getGenre(), movie.getScore());
			}
			System.out.println("===============================");
			System.out.println("예약하실 영화 번호 입력(나가시려면 0번을 입력하세요) : ");
			int movieId = sc.nextInt();
			if(movieId != 0) {
				new MovieReserveView(movieId,null);
			}
		}
	}
}