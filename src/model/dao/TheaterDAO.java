package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.dto.TheaterDTO;

public class TheaterDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public TheaterDAO() {
		conn = DBConnection.getConnection();
	}

	public ArrayList<TheaterDTO> getTheaterByTheaterName(String theaterName) {
		ArrayList<TheaterDTO> result = new ArrayList<>();
		
		String sql = "select * from theater where theaterName like('%" + theaterName + "%')";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				TheaterDTO theater = new TheaterDTO(
						rs.getInt("theaterId"),
						rs.getString("theaterName"),
						rs.getString("theaterAddr"),
						rs.getInt("seatCnt"),
						rs.getString("dimension")
				);
				result.add(theater);
			}
			
		} catch (SQLException e) {
		}
		
		if(result.size() == 0) {
			return null;
		} else {
			return result;
		}
	}
	

}