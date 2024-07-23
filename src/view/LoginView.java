package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.UserController;
import model.Session;

public class LoginView {
	public LoginView() {
		UserController ucon = new UserController();
		Scanner sc = new Scanner(System.in);
        System.out.println("아이디 : ");
        String userId = sc.next();
        System.out.println("비밀번호 : ");
        String userPw = sc.next();
        
        if(ucon.login(userId,userPw)) {
        	if(userId.equals("admin")) {
        		System.out.println("관리자님 어서오세요~");
//        		new AdminView();
        	}
        	System.out.println(Session.getData("loginUser")+"님 어서오세요~");
        	new MainView();
        }

        else {
        	System.out.println("로그인에 실패하였습니다.");
        	return;
        }
	}

}


