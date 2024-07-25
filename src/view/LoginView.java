package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {
	public LoginView() {
		Scanner sc = new Scanner(System.in);
        System.out.println("아이디 : ");
        String userId = sc.next();
        System.out.println("비밀번호 : ");
        String userPw = sc.next();
//      로그인 기능 
        if(true) {
//      로그인 성공
        	new MainView();
        }
        else {
//      로그인 실패 
        	System.out.println("로그인에 실패하였습니다.");
        	new LoginView();
            
        }
	}

}
