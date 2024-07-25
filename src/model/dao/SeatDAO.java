package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.dto.SeatDTO;

public class SeatDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public SeatDAO() {
		conn = DBConnection.getConnection();
	}
	
	public ArrayList<SeatDTO> getSeatBySchduelId(int scheduleId) {
			ArrayList<SeatDTO> seatlist = new ArrayList<>();
			
					
			String sql = "SELECT S.seatId, S.seatType, S.seatPrice, S.seatLine, S.seatRow " +
                    "FROM Schedule Sch " +
                    "JOIN Theater T ON Sch.theaterId = T.theaterId " +
                    "JOIN Seats S ON T.theaterId = S.theaterID " +
                    "WHERE Sch.scheduleId = ?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, scheduleId);
				rs = ps.executeQuery();
				
				
				while(rs.next()) {
					SeatDTO seat = new SeatDTO(
							rs.getInt("seatId"),
							rs.getString("seatType"),
							rs.getInt("seatPrice"),
							rs.getString("seatLine"),
							rs.getString("seatRow"),
							rs.getInt("theaterID")
					);
					seatlist.add(seat);
				}
			} catch (SQLException e) {
				System.err.println(e);
			}
			if(seatlist.size() == 0) {
				return null;
			}
			else {
				return seatlist;
			}

		}


	
	
	
	
}
