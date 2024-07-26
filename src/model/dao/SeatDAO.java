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
			
					
			String sql = "SELECT S.* " +
                    "FROM schedule Sch " +
                    "JOIN theater T ON Sch.theaterId = T.theaterId " +
                    "JOIN seat S ON T.theaterId = S.theaterId " +
                    "WHERE Sch.scheduleId = ? order by seatRow";
			
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
							rs.getInt("theaterId")
					);
					seatlist.add(seat);
				}
			} catch (SQLException e) {
				System.err.println(e);
				System.out.println("데이터베이스 오류");
			}
			if(seatlist.size() == 0) {
				return null;
			}
			else {
				return seatlist;
			}

		}


	
	
	
	
}
