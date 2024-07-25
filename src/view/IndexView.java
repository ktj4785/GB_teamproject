package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IndexView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true) {
            System.out.println("==========영화 예약 프로그램========");
            System.out.println("1.회원가입\n2.로그인\n3.나가기");
            try {
                choice = sc.nextInt();
                if(choice == 1) {
                     new JoinView();
                }
                else if (choice == 2) {
                     new LoginView();
                }
                else if (choice == 3) {
                    System.out.println("안녕히 가세요");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다, 메뉴의 숫자 번호를 입력하세요");
                sc.next(); // 입력 스트림을 비워줌
            }
        }
    }
}
