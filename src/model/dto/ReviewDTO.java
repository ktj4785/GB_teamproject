package model.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class ReviewDTO {
	private int reviewId;
	private String review;
	private double grade;
	private Timestamp createtime;
	private int movieId;
	private String userId;
	private String movieName;
	

	public ReviewDTO(int reviewId, String review, double grade, Timestamp createtime, int movieId,
			String userId,String movieName) {
		this.reviewId = reviewId;
		this.review = review;
		this.grade = grade;
		this.createtime = createtime;
		this.movieId = movieId;
		this.userId = userId;
		this.movieName = movieName;
	}
	
	public ReviewDTO(int movieId, String movieName) {
		this.movieId = movieId;
		this.movieName = movieName;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public int getmovieId() {
		return movieId;
	}

	public void setScheduleId(int movieId) {
		this.movieId = movieId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

}
