package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.Session;
import model.dto.ReserveDTO;
import model.dto.ScheduleDTO;

public class ReserveDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public ReserveDAO() {
		conn = DBConnection.getConnection();
	}
	
//	유저 아이디로 예약 정보 획득
	public ArrayList<ReserveDTO> getReserveListByUserid(String loginUser) {
		ArrayList<ReserveDTO> list = new ArrayList<>();
				
		String sql = "select * from reserve where userId = ? ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReserveDTO reserve = new ReserveDTO(
						rs.getInt("reserveId"),
						rs.getInt("pnum"),
						rs.getInt("price"),
						rs.getBoolean("payment"),
						rs.getString("userId"),
						rs.getInt("scheduleId"),
						rs.getString("seat")
				);
				list.add(reserve);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		if(list.size() == 0) {
			return null;
		}
		else {
			return list;
		}

	}

// 	예약 삭제(유저아이디기반)
	public boolean deleteReserveByUserId(String loginUser) {
		String sql = "delete from reserve where userId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser);
			
			int result = ps.executeUpdate();
							
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
	}

//	예약번호를 보고 예약 삭제
	public boolean deleteReserveByReserveId(int reserveId) {
		String sql = "delete from reserve where reserveId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reserveId);
			
			int result = ps.executeUpdate();
							
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
	}
	public boolean insetReserveInfo(int pNum, int price, boolean payment, int scheduleId, String seat) {
		String sql = "insert into reserve (pNum, price, payment, userId, scheduleId, seat) values(?, ?, ?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pNum);
			ps.setInt(2, price);
			ps.setBoolean(3, payment);
			ps.setString(4, (String) Session.getData("loginUser"));
			ps.setInt(4, scheduleId);
			ps.setString(5, seat);

			int result = ps.executeUpdate();

			return result == 1;
		} catch (SQLException e) {
		}
		return false;
	}
	public ArrayList<ReserveDTO> getReserveListByScheduleID(int scheduleId) {
		ArrayList<ReserveDTO> list = new ArrayList<>();
				
		String sql = "select * from reserve where scheduleId = ? ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scheduleId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReserveDTO reserve = new ReserveDTO(
						rs.getInt("reserveId"),
						rs.getInt("pnum"),
						rs.getInt("price"),
						rs.getBoolean("payment"),
						rs.getString("userId"),
						rs.getInt("scheduleId"),
						rs.getString("seat")
				);
				list.add(reserve);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		if(list.size() == 0) {
			return null;
		}
		else {
			return list;
		}

	}



}

