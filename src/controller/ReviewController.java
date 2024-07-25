package controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import model.Session;
import model.dao.ReviewDAO;
import model.dto.ReserveDTO;
import model.dto.ReviewDTO;

public class ReviewController {
	//리뷰 불러오는 메소드
	public ArrayList<ReviewDTO> getMyReview() {
		String userId = (String)Session.getData("loginUser");
		ReviewDAO rvdao = new ReviewDAO();
		return rvdao.getReviewListByUserid(userId);
	}
	//리뷰 수정하는 메소드
	public boolean updateReview(int choice2, int reviewId, String newdata) {
		ReviewDAO rvdao = new ReviewDAO();
		String[] cols = { "grade","review"};
		return rvdao.updateReviewByreviewId(cols[choice2-1],reviewId,newdata);
	}
	//리뷰 삭제하는 메소드
	public boolean deleteReview(int reviewId) {
		ReviewDAO rvdao = new ReviewDAO();
		return rvdao.deleteReviewByreviewId(reviewId);
		
	}
	//리뷰 작성 가능한 영화 리스트 반환하는 메소드
	//그 유저의 예약 정보와 리뷰 정보를 대조해서 본 영화에 한해서 리뷰 작성 가능
	public ArrayList<ReviewDTO> getAvailableReview() {
		ReviewDAO rvdao = new ReviewDAO();
		String userId = (String)Session.getData("loginUser");
		return rvdao.getAvailableReviewByUserid(userId);
		
	}
	//리뷰 작성
	public boolean addReview(int reserveId, int grade, String reviewText) {
		String userId = (String)Session.getData("loginUser");
		ReviewDAO rvdao = new ReviewDAO();
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());
		return rvdao.insertReview(userId,reserveId,grade,reviewText,nowtime);
		
	}

}
