package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ReviewController;
import model.dto.ReserveDTO;
import model.dto.ReviewDTO;

public class AddReView {
	public AddReView() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			ReviewController rvcon = new ReviewController();
			System.out.println("리뷰 관리 메뉴");
			System.out.println("1.내 리뷰 보기\n2. 리뷰 추가\n3. 나가기");
			int choice = sc.nextInt();
			if(choice==3) {
				break;
			}
			else if(choice==1) {
				ArrayList<ReviewDTO> rvlist =  rvcon.getMyReview();
				if(rvlist==null || rvlist.size()==0) {
					System.out.println("리뷰가 존재하지 않습니다");
					break;
				}
				System.out.println("번호 영화			평점	작성시간			내용						");
				int count = 1;
				for(ReviewDTO review : rvlist) {
					System.out.print(count+"\t");
					System.out.print(review.getMovieName()+"\t");
					System.out.print(review.getGrade()+"\t");
					System.out.print(review.getCreatetime()+"\t");
					System.out.print(review.getReview()+"\n");
				}
				System.out.println("1.리뷰 수정 2. 리뷰 삭제 3. 나가기");
				int choice2 = sc.nextInt();
				if(choice2==3) {
					break;
				}
				if(choice2==1) {
					System.out.println("수정할 리뷰의 정보를 고르세요\n 1.평점 2.한줄평 3.나가기");
					int choice3 = sc.nextInt();
					if(choice3==3) {
						break;
					}
					System.out.println("수정할 리뷰의 번호를 입력하세요");
					int select = sc.nextInt();
					sc.nextLine();
					System.out.println("수정할 내용을 입력하세요");
					String newdata = sc.nextLine();
					if(rvcon.updateReview(choice3,(rvlist.get(select-1)).getReviewId(),newdata)) {
						System.out.println("리뷰가 수정되었습니다.");
						break;
					}
					break;
				}
				if(choice2==2) {
					System.out.println("삭제할 리뷰의 번호를 입력하세요");
					int select = sc.nextInt();	
					if(rvcon.deleteReview((rvlist.get(select-1)).getReviewId())) {
						System.out.println("리뷰가 삭제되었습니다");
						break;
					}
				}
			}
			else if(choice==2) {
				ArrayList<ReviewDTO> canlist = rvcon.getAvailableReview();
				if(canlist==null || canlist.size()==0) {
					System.out.println("리뷰 추가가 가능한 영화가 없습니다");
					return;
				}
				System.out.println("리뷰 추가가 가능한 영화의 목록");
				int count = 1;
				for(ReviewDTO rv : canlist) {
				
				System.out.println(count +" 제목 : "+rv.getMovieName());
				count++;
				}
				System.out.println("추가할 영화의 번호를 고르세요");
				int select_count = sc.nextInt();
				System.out.println("리뷰의 평점을 입력하세요");
				double grade = sc.nextDouble();
				sc.nextLine();
				System.out.println("리뷰의 한줄평을 입력하세요");
				String reviewText = sc.nextLine();
				if(rvcon.addReview((canlist.get(select_count-1)).getmovieId(),	grade,reviewText)) {
					System.out.println("리뷰가 등록되었습니다.");
					break;
				}
					
				
			}
		}
	}
}
