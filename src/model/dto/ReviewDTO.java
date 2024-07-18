package model.dto;

import java.sql.Date;

public class ReviewDTO {
	private int reviewId;
	private String review;
	private double grade;
	private Date createtime;
	private String socialNum;
	private int movieId;
	private String userId;
	
	public ReviewDTO(int reviewId, String review, double grade, Date createtime, String socialNum, int movieId,
			String userId) {
		this.reviewId = reviewId;
		this.review = review;
		this.grade = grade;
		this.createtime = createtime;
		this.socialNum = socialNum;
		this.movieId = movieId;
		this.userId = userId;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getSocialNum() {
		return socialNum;
	}

	public void setSocialNum(String socialNum) {
		this.socialNum = socialNum;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
