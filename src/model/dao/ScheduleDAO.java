package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.dto.ScheduleDTO;

public class ScheduleDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public ScheduleDAO() {
		conn = DBConnection.getConnection();
	}
	
	public ArrayList<ScheduleDTO> getScheduleByMovieId(int movieId) {
		ArrayList<ScheduleDTO> list = new ArrayList<>();
		
		String sql = "select * from schedule where movieId = "+ movieId;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ScheduleDTO schedule = new ScheduleDTO(
						rs.getInt("scheduleId"),
						rs.getString("startTime"),
						rs.getString("endTime"),
						rs.getInt("leftSeat"),
						rs.getInt("theaterId"),
						rs.getInt("movieId")
				);
				list.add(schedule);
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

	public ScheduleDTO getScheduleByScheduleId(int scheduleId) {
		String sql = "select * from schedule where scheduleId = " +scheduleId;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				ScheduleDTO schedule = new ScheduleDTO(
						rs.getInt("scheduleId"),
						rs.getString("startTime"),
						rs.getString("endTime"),
						rs.getInt("leftSeat"),
						rs.getInt("theaterId"),
						rs.getInt("movieId")
				);
				return schedule;
			}
		} catch (SQLException e) {
		}
		return null;
	}
	 
}
