package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import model.DBConnection;
import model.dto.TheaterDTO;
import model.dto.UserDTO;

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
	


	public TheaterDTO getTheaterByTheaterId(int theaterId) {
		String sql = "select * from theater where theaterId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, theaterId);
			
			rs = ps.executeQuery();

			//결과가 있다면 한 줄 꺼내서
			if(rs.next()) {
				//객체로 만들고
				TheaterDTO theater = new TheaterDTO(
						rs.getInt("theaterId"),
						rs.getString("theaterName"),
						rs.getString("theaterAddr"),
						rs.getInt("seatCnt"),
						rs.getString("dimension")
				  );
				return theater;
			  }
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;
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
							rs.getString(5)
							);
					list.add(theater);
					
				}
			} catch (SQLException e) {
			}
		}
		if(list.size()==0) {
			return null;
		}
		return list;
	}

//	public TheaterDTO getTheaterByTheaterId(int theaterId) {
//		String sql = "select * from theater where theaterId = " + theaterId;
//
//		try {
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//
//			if(rs.next()) {
//				TheaterDTO theater = new TheaterDTO(
//						rs.getInt(1),
//						rs.getString(2),
//						rs.getString(3),
//						rs.getInt(4),
//						rs.getInt(5)
//				);
//				return theater;
//			}
//		} catch (SQLException e) {
//		}
//		return null;
//	}

}