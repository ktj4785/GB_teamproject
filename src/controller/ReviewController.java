package controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import model.Session;
import model.dao.ReviewDAO;
import model.dto.ReserveDTO;
import model.dto.ReviewDTO;

public class ReviewController {

	public ArrayList<ReviewDTO> getMyReview() {
		String userId = (String)Session.getData("loginUser");
		ReviewDAO rvdao = new ReviewDAO();
		return rvdao.getReviewListByUserid(userId);
	}

	public boolean updateReview(int choice2, int reviewId, String newdata) {
		ReviewDAO rvdao = new ReviewDAO();
		String[] cols = { "grade","review"};
		return rvdao.updateReviewByreviewId(cols[choice2-1],reviewId,newdata);
	}

	public boolean deleteReview(int reviewId) {
		ReviewDAO rvdao = new ReviewDAO();
		return rvdao.deleteReviewByreviewId(reviewId);
		
	}

	public ArrayList<ReserveDTO> getAvailableReview() {
		ReviewDAO rvdao = new ReviewDAO();
		String userId = (String)Session.getData("loginUser");
		return rvdao.getAvailableReviewByUserid(userId);
		
	}

	public boolean addReview(int reserveId, int grade, String reviewText) {
		String userId = (String)Session.getData("loginUser");
		ReviewDAO rvdao = new ReviewDAO();
		Timestamp nowtime = new Timestamp(System.currentTimeMillis());
		return rvdao.insertReview(userId,reserveId,grade,reviewText,nowtime);
		
	}

}
