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

	public ArrayList<MovieDTO> getlist(int choice) {

		ArrayList<MovieDTO> list = new ArrayList<>();

		String[] column = {"score desc","movieName"};
		String sql = "select \r\n"
				+ "mt.movieId,\r\n"
				+ "mt.movieName,\r\n"
				+ "mt.director,\r\n"
				+ "mt.runningTime,\r\n"
				+ "mt.genre,\r\n"
				+ "format(avg(r.grade),1)as score\r\n"
				+ "from movietest mt\r\n"
				+ "	join reviewtest r using(movieId)\r\n"
				+ "group by mt.movieId,\r\n"
				+ "mt.movieName,\r\n"
				+ "mt.director,\r\n"
				+ "mt.runningTime,\r\n"
				+ "mt.genre\r\n"
				+ "order by "+column[choice-1];

		try {
			ps = conn.prepareStatement(sql);
//			ps.setString(1, column[choice]);
			rs = ps.executeQuery();
			while (rs.next()) {
				MovieDTO movielist = new MovieDTO(
						rs.getInt("movieId"), 
						rs.getString("movieName"),
						rs.getString("director"), 
						rs.getString("runningTime"), 
						rs.getString("genre"),
						rs.getDouble("score"));
				list.add(movielist);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return list;
	}

	
	
	public ArrayList<MovieDTO> getMovieName(String ans) {
		
		ArrayList<MovieDTO> list = new ArrayList<>();
		String sql = "select \r\n"
				+ "mt.movieId,\r\n"
				+ "mt.movieName,\r\n"
				+ "mt.director,\r\n"
				+ "mt.runningTime,\r\n"
				+ "mt.genre,\r\n"
				+ "format(avg(r.grade),1)as score\r\n"
				+ "from movietest mt\r\n"
				+ "	join reviewtest r using(movieId)\r\n"
				+ "where mt.moviename like ? \r\n"
				+ "group by mt.movieId,\r\n"
				+ "mt.movieName,\r\n"
				+ "mt.director,\r\n"
				+ "mt.runningTime,\r\n"
				+ "mt.genre;";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + ans + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				MovieDTO movielist = new MovieDTO(
						rs.getInt("movieId"), 
						rs.getString("movieName"),
						rs.getString("director"), 
						rs.getString("runningTime"), 
						rs.getString("genre"),
						rs.getDouble("score"));
				list.add(movielist);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return list;
	}

	public ArrayList<MovieDTO> getActorName(String ans) {

		ArrayList<MovieDTO> list = new ArrayList<>();
		String sql = "select\r\n"
				+ "ma.movieId,\r\n"
				+ "ma.movieName,\r\n"
				+ "ma.director,\r\n"
				+ "ma.runningTime,\r\n"
				+ "ma.genre,\r\n"
				+ "format(avg(r.grade),1)as score\r\n"
				+ "from(select \r\n"
				+ "mt.movieId,\r\n"
				+ "mt.movieName,\r\n"
				+ "mt.director,\r\n"
				+ "mt.runningTime,\r\n"
				+ "mt.genre,\r\n"
				+ "a.actorName\r\n"
				+ "from movietest mt\r\n"
				+ "	join actortest a using(movieId)\r\n"
				+ "where actorName like ?) ma\r\n"
				+ "join reviewtest r using(movieId)\r\n"
				+ "group by ma.movieId,\r\n"
				+ "ma.movieName,\r\n"
				+ "ma.director,\r\n"
				+ "ma.runningTime,\r\n"
				+ "ma.genre\r\n"
				+ "order by ma.movieName;";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + ans + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				MovieDTO movielist = new MovieDTO(
						rs.getInt("movieId"), 
						rs.getString("movieName"),
						rs.getString("director"), 
						rs.getString("runningTime"), 
						rs.getString("genre"),
						rs.getDouble("score"));
				list.add(movielist);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return list;
	}
	
	
	public ArrayList<MovieDTO> getDirectorName(String ans) {
		
		ArrayList<MovieDTO> list = new ArrayList<>();
		String sql = "select \r\n"
				+ "mt.movieId,\r\n"
				+ "mt.movieName,\r\n"
				+ "mt.director,\r\n"
				+ "mt.runningTime,\r\n"
				+ "mt.genre,\r\n"
				+ "format(avg(r.grade),1)as score\r\n"
				+ "from movietest mt\r\n"
				+ "	join reviewtest r using(movieId)\r\n"
				+ "where mt.director like ? \r\n"
				+ "group by mt.movieId,\r\n"
				+ "mt.movieName,\r\n"
				+ "mt.director,\r\n"
				+ "mt.runningTime,\r\n"
				+ "mt.genre;";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + ans + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				MovieDTO movielist = new MovieDTO(
						rs.getInt("movieId"), 
						rs.getString("movieName"),
						rs.getString("director"), 
						rs.getString("runningTime"), 
						rs.getString("genre"),
						rs.getDouble("score"));
				list.add(movielist);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return list;
	}

}
