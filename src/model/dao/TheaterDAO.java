package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.dto.ScheduleDTO;
import model.dto.TheaterDTO;

public class TheaterDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public TheaterDAO() {
		conn = DBConnection.getConnection();
	}
	
	public ArrayList<TheaterDTO> getTheaterByScheduleTheaterId(ArrayList<Integer> idList) {
		ArrayList<TheaterDTO> list = new ArrayList<>();

		for (int i = 0; i < idList.size(); i++) {
			
			String sql = "select * from theater where theaterId = "+ (int)idList.get(i);
			
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					TheaterDTO theater = new TheaterDTO(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getInt(5)
							);
					list.add(theater);
					return list;
				}
			} catch (SQLException e) {
			}
		}
		return null;
	}

	public TheaterDTO getTheaterByTheaterId(int theaterId) {
		String sql = "select * from theater where theaterId = " + theaterId;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				TheaterDTO theater = new TheaterDTO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5)
				);
				return theater;
			}
		} catch (SQLException e) {
		}
		return null;
	}
}
