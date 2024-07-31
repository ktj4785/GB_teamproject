package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MovieController;
import model.dto.MovieDTO;

public class MovieSearchView {
	public MovieSearchView() {
		Scanner sc = new Scanner(System.in);
		MovieController mcon = new MovieController();
		System.out.println("1. 영화명으로 검색하기\n2. 배우명으로 검색하기\n3. 감독명으로 검색하기");
		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			System.out.print("검색어 입력: ");
			String ans = sc.nextLine();
			mcon.searchMovieName(ans);

			ArrayList<MovieDTO> list = mcon.searchMovieName(ans);
			if(list.size()==0) {
				System.out.println("검색 결과가 없습니다.");
				break;
			}
			else {
			System.out.println("번호\t제목\t\t감독\t상영시간\t장르\t평점");
			int count = 1;
			for (MovieDTO x : list) {
//		System.out.print(x.getMovieId()+"\t");
				System.out.print(count+". "+"\t");
				System.out.print(x.getMovieName() + "     " + "\t");
				System.out.print(x.getDirector() + "\t");
				System.out.print(x.getRunningTime() + "\t");
				System.out.print(x.getGenre() + "\t");
				System.out.print(x.getScore() + "\n");
				count++;
			}
			System.out.println("1. 돌아가기\n2. 예약하기");
			int choice1 = sc.nextInt();
			if(choice1 == 2) {
				System.out.println("예약하실 영화의 번호를 입력해주세요: ");
				sc.nextLine();
				int choice2 = sc.nextInt();
				new MovieReserveView((list.get(choice2-1)).getMovieId(),null);
				break;
			}
			else if(choice1 == 1){
				break;
			}
			}
		case 2:
			System.out.print("검색어 입력: ");
			String ans2 = sc.nextLine();
			mcon.searchActorName(ans2);

			ArrayList<MovieDTO> list2 = mcon.searchActorName(ans2);
			if(list2.size()==0) {
				System.out.println("검색 결과가 없습니다.");
				break;
			}
			else {
				
				System.out.println("번호\t제목\t\t감독\t상영시간\t장르\t평점");
				int count2 = 1;
				
				for (MovieDTO x : list2) {
					System.out.print(count2+". "+"\t");
					System.out.print(x.getMovieName() + "     " + "\t");
					System.out.print(x.getDirector() + "\t");
					System.out.print(x.getRunningTime() + "\t");
					System.out.print(x.getGenre() + "\t");
					System.out.print(x.getScore() + "\n");
					count2++;
				}
				System.out.println("1. 돌아가기\n2. 예약하기");
				int choice2 = sc.nextInt();
				if(choice2 == 2) {
					System.out.println("예약하실 영화의 번호를 입력해주세요: ");
					sc.nextLine();
					int choice3 = sc.nextInt();
					new MovieReserveView((list2.get(choice3-1)).getMovieId(),null);
					break;
				}
				else if(choice2 == 1){
					break;
				}
			}
		case 3:
			System.out.print("검색어 입력: ");
			String ans3 = sc.nextLine();
			mcon.searchDirectorName(ans3);

			ArrayList<MovieDTO> list3 = mcon.searchDirectorName(ans3);
			if(list3.size()==0) {
				System.out.println("검색 결과가 없습니다.");
				break;
			}
			else {
			System.out.println("번호\t제목\t\t감독\t상영시간\t장르\t평점");
			int count3 = 1;
			for (MovieDTO x : list3) {
				System.out.print(count3+". "+"\t");
				System.out.print(x.getMovieName() + "     " + "\t");
				System.out.print(x.getDirector() + "\t");
				System.out.print(x.getRunningTime() + "\t");
				System.out.print(x.getGenre() + "\t");
				System.out.print(x.getScore() + "\n");
				count3++;
			}
			System.out.println("1. 돌아가기\n2. 예약하기");
			int choice3 = sc.nextInt();
			if(choice3 == 2) {
				System.out.println("예약하실 영화의 번호를 입력해주세요: ");
				sc.nextLine();
				int choice4 = sc.nextInt();
				new MovieReserveView((list3.get(choice4-1)).getMovieId(),null);
				break;
			}
			else if(choice3 == 1){
				break;
			}
			}
		}
	}
}
