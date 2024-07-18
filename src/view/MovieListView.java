package view;

import java.util.Scanner;


public class MovieListView {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MovieController controller = new MovieController();
		System.out.println("1. 평점 순으로 보기\n2. 이름 순으로 보기");
		int choice = sc.nextInt();	
	}
}
