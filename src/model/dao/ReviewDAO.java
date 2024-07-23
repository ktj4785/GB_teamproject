package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.dto.ReserveDTO;
import model.dto.ReviewDTO;
import model.dto.UserDTO;

public class ReviewDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public ArrayList<ReviewDTO> getReviewListByUserid(String userId) {
		String sql = "SELECT r.*, m.movieName\n"
				+ "FROM review r\n"
				+ "JOIN reserve res ON r.reserveId = res.reserveId\n"
				+ "JOIN schedule s ON res.scheduleId = s.scheduleId\n"
				+ "JOIN movie m ON s.movieId = m.movieId\n"
				+ "WHERE r.userId = ?";
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
						rs.getInt("scheduleId"),
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

	public boolean updateReviewByreviewId(String cols, int reviewId, String newdata) {
		String sql = "UPDATE user SET "+cols+" = ? WHERE reviewId = ?";
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

	public ArrayList<ReserveDTO> getAvailableReviewByUserid(String userId) {
		String sql = "SELECT r.id, m.movieName\n"
				+ "FROM reserve r\n"
				+ "LEFT JOIN review rv ON r.reserveId = rv.reserveId\n"
				+ "JOIN schedule s ON r.scheduleId = s.scheduleId\n"
				+ "JOIN movie m ON s.movieId = m.movieId\n"
				+ "WHERE rv.reserveId IS NULL\n"
				+ "AND r.payment = TRUE\n"
				+ "AND r.userId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			
			rs = ps.executeQuery();
			ArrayList<ReserveDTO> rvlist = new ArrayList<>();
			while(rs.next()) {
				//객체로 만들고
				ReserveDTO reserve = new ReserveDTO(
						rs.getInt("reserveId"),
						rs.getString("movieName")
				);
				rvlist.add(reserve);
			}
			return rvlist;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;
	}

	public boolean insertReview(String userId, int reserveId, int grade, String reviewText, Timestamp nowtime) {
		String sql = "insert into review(review,grade,createtime,reservId,userId) values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, reviewText);
			ps.setInt(2, grade);
			ps.setTimestamp(3, nowtime);
			ps.setInt(4, reserveId);
			ps.setString(5, userId);

			
			int result = ps.executeUpdate();
			
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
	}
	
		
	

}

////
//SELECT r.*, m.movieName
//FROM review r
//JOIN reserve res ON r.reserveId = res.reserveId
//JOIN schedule s ON res.scheduleId = s.scheduleId
//JOIN movie m ON s.movieId = m.movieId
//WHERE r.reviewId = ?;