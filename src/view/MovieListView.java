package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MovieController;
import model.dto.MovieDTO;
import controller.MovieController;


public class MovieListView {
	public MovieListView() {
		
		MovieController mcon = new MovieController();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1. 평점 순으로 보기\n2. 이름 순으로 보기");
		int choice = sc.nextInt();
		int count = 1;
		ArrayList<MovieDTO> list = mcon.getList(choice);
		System.out.println("번호\t제목\t\t감독\t상영시간\t장르\t평점");
		
		for(MovieDTO x : list) {
//		System.out.print(x.getMovieId()+"\t");
		System.out.print(count+". "+"\t");
		System.out.print(x.getMovieName()+"     "+"\t");
		System.out.print(x.getDirector()+"\t");
		System.out.print(x.getRunningTime()+"\t");
		System.out.print(x.getGenre()+"\t");
		System.out.print(x.getScore()+"\n");
		count++;
		}
		
		sc.nextLine();
		System.out.println("1. 돌아가기\n2. 예약하기");
		int choice1 = sc.nextInt();
		if(choice1 == 2) {
			System.out.println("예약하실 영화의 번호를 입력해주세요: ");
			sc.nextLine();
			int choice2 = sc.nextInt();
			new MovieReserveView((list.get(choice2-1)).getMovieId(),null,null);
			//나중에 해당 아이디를 넘겨주기
		}
		else if(choice1 == 1){
		}
		
	}
}
