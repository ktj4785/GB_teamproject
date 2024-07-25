package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.dto.MovieDTO;

public class MovieDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public MovieDAO() {
		conn = DBConnection.getConnection();
	}

	public ArrayList<MovieDTO> getMovieByKeyword(String keyword) {
		ArrayList<MovieDTO> result = new ArrayList<>();
		
		String sql = "select * from moive where movieName like('%"+keyword+"%')"
				+ "or director like('%"+keyword+"%') "
				+ "or genre like('%"+keyword+"%')";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MovieDTO movie = new MovieDTO(
						rs.getInt("movieId"),
						rs.getString("movieName"),
						rs.getString("director"),
						rs.getString("runningTime"),
						rs.getString("genre"),
						rs.getDouble("avgScore")		
				);
				result.add(movie);
			}
		} catch (SQLException e) {
		}
		if(result.size() == 0) {
			return null;
		}
		return result;
	}
	
	
	

}
