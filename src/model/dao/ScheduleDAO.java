package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.dto.ScheduleDTO;
import model.dto.UserDTO;

public class ScheduleDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public ScheduleDAO() {
		conn = DBConnection.getConnection();
	}
	public ScheduleDTO getSchedule(int scheduleId) {
		String sql = "select * from schedule where scheduleId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scheduleId);
			
			rs = ps.executeQuery();
			
			//결과가 있다면 한 줄 꺼내서
			if(rs.next()) {
				//객체로 만들고
				ScheduleDTO schedule = new ScheduleDTO(
						rs.getInt("scheduleId"),
						rs.getTimestamp("startTime"),
						rs.getTimestamp("endTime"),
						rs.getInt("leftSeat"),
						rs.getInt("theaterId"),
						rs.getInt("movieId")
				);
				//리턴
				return schedule;
			}
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;
	}
		

}
