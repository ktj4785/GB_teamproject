package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.DBConnection;
import model.dto.ReserveDTO;
import model.dto.ReviewDTO;
import model.dto.UserDTO;

public class ReviewDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public ReviewDAO() {
		conn = DBConnection.getConnection();
	}
//	유저아이디를 기반으로 유저가 쓴 리뷰 리스트 불러오기
	public ArrayList<ReviewDTO> getReviewListByUserid(String userId) {
	    String sql = "SELECT r.*, m.movieName " +
                "FROM review r " +
                "JOIN movie m ON r.movieID = m.movieId " +
                "WHERE r.userId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			
			rs = ps.executeQuery();
			ArrayList<ReviewDTO> rvlist = new ArrayList<>();
			while(rs.next()) {
				//객체로 만들고
				ReviewDTO review = new ReviewDTO(
						rs.getInt("reviewId"),
						rs.getString("review"),
						rs.getDouble("grade"),
						rs.getTimestamp("createtime"),
						rs.getInt("movieId"),
						rs.getString("userId"),
						rs.getString("movieName")
				);
				rvlist.add(review);
			}
			return rvlist;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;
	}
	//리뷰아이디를 보고 리뷰 정보 수정
	public boolean updateReviewByreviewId(String cols, int reviewId, String newdata) {
		String sql = "UPDATE review SET "+cols+" = ? WHERE reviewId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newdata);
			ps.setInt(2, reviewId);
			
			int result = ps.executeUpdate();
							
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
	}
	//리뷰아이디를 기반으로 리뷰 삭제
	public boolean deleteReviewByreviewId(int reviewId) {
		String sql = "delete from review where reviewId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewId);
			
			int result = ps.executeUpdate();
							
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
		
	}
	//유저아이디를 보고 리뷰 가능한거 확인 
	public ArrayList<ReviewDTO> getAvailableReviewByUserid(String userId) {
	    String sql = "SELECT " +
                "r.reserveId, " +
                "s.scheduleId, " +
                "m.movieId, " +
                "m.movieName " +
                "FROM " +
                "reserve r " +
                "JOIN " +
                "schedule s ON r.scheduleId = s.scheduleId " +
                "JOIN " +
                "movie m ON s.movieId = m.movieId " +
                "LEFT JOIN " +
                "review rv ON r.userId = rv.userId AND m.movieId = rv.movieID " +
                "WHERE " +
                "r.userId = ? " +
                "AND rv.movieID IS NULL " +
                "AND r.payment = TRUE";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			
			rs = ps.executeQuery();
			ArrayList<ReviewDTO> rvlist = new ArrayList<>();
			while(rs.next()) {
				//객체로 만들고
				ReviewDTO review = new ReviewDTO(
						rs.getInt("movieId"),
						rs.getString("movieName")
				);
				rvlist.add(review);
			}
			return rvlist;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;
	}
	//리뷰 생성
	public boolean insertReview(String userId, int movieId, int grade, String reviewText, Timestamp nowtime) {
		String sql = "insert into review(review,grade,createtime,movieId,userId) values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, reviewText);
			ps.setInt(2, grade);
			ps.setTimestamp(3, nowtime);
			ps.setInt(4, movieId);
			ps.setString(5, userId);

			
			int result = ps.executeUpdate();
			
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
	}
	
		
	

}
