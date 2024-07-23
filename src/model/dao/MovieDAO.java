package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.MovieDTO;
import model.dto.UserDTO;

public class MovieDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	public MovieDTO getMovieByMovieId(int movieId) {
		String sql = "select * from movie where movieId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, movieId);
			
			rs = ps.executeQuery();

			//결과가 있다면 한 줄 꺼내서
			if(rs.next()) {
				//객체로 만들고
				MovieDTO movie = new MovieDTO(
						rs.getInt("movieId"),
						rs.getString("movieName"),
						rs.getString("director"),
						rs.getString("runningTime"),
						rs.getString("genre"),
						rs.getDouble("score")
				);
				//리턴
				return movie;
			}
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;
	}

}
