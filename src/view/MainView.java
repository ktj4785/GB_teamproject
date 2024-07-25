package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
	public MainView() {
		Scanner sc = new Scanner(System.in);
        int choice;
        while(true) {
            System.out.println("==========메뉴========");
            System.out.println("1.영화목록\n2.영화검색\n3.영화관검색\n4.상영시간표 확인\n5.영화예약\n6.회원정보\n7.리뷰작성\n8.로그아웃");
            try {
                choice = sc.nextInt();
                if(choice == 8) {
                     System.out.println("~~님 안녕히 가세요");
                     break;
                }
                switch(choice) {
                	case 1:
                		new MovieListView();
                		break;
                	case 2:
                		new MovieSearchView();
                		break;
                	case 3:
                		new TheaterSearchView();
                		break;
                	case 4:
                		new ScheduleView();
                		break;
                	case 5:
                		new ReserveView();
                		break;
                	case 6:
                		new UserInfoView();
                		break;
                	case 7:
                		new AddReView();
                		break;
                		
                		
                		                		                		
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다, 메뉴의 숫자 번호를 입력하세요");
                sc.next(); // 입력 스트림을 비워줌
            }
        }
	}
	
}
