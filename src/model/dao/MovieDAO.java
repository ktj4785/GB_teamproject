package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DBConnection;
import model.dto.MovieDTO;
import model.dto.TheaterDTO;
import model.dto.UserDTO;
import java.util.ArrayList;




public class MovieDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	public MovieDAO() {
		conn = DBConnection.getConnection();
	}

	//영화 리스트를 획득함
	//쿼리문 수정 필요
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
				+ "from movie mt\r\n"
				+ "	join review r using(movieId)\r\n"
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

	
	//영화 이름으로 검색하고 리스트 획득 
	public ArrayList<MovieDTO> getMovieName(String ans) {
		
		ArrayList<MovieDTO> list = new ArrayList<>();
		String sql = "select \r\n"
				+ "mt.movieId,\r\n"
				+ "mt.movieName,\r\n"
				+ "mt.director,\r\n"
				+ "mt.runningTime,\r\n"
				+ "mt.genre,\r\n"
				+ "format(avg(r.grade),1)as score\r\n"
				+ "from movie mt\r\n"
				+ "	join review r using(movieId)\r\n"
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
	//영화 배우이름으로 검색하고 획득
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
				+ "from movie mt\r\n"
				+ "	join actor a using(movieId)\r\n"
				+ "where actorName like ?) ma\r\n"
				+ "join review r using(movieId)\r\n"
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
	
	//영화 감독이름으로 검색하고 획득
	public ArrayList<MovieDTO> getDirectorName(String ans) {
		
		ArrayList<MovieDTO> list = new ArrayList<>();
		String sql = "select \r\n"
				+ "mt.movieId,\r\n"
				+ "mt.movieName,\r\n"
				+ "mt.director,\r\n"
				+ "mt.runningTime,\r\n"
				+ "mt.genre,\r\n"
				+ "format(avg(r.grade),1)as score\r\n"
				+ "from movie mt\r\n"
				+ "	join review r using(movieId)\r\n"
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
	//영화 아이디로 영화 정보 획득
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
						rs.getString("genre")
				);
				//리턴
				return movie;
			}
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;

	}
	public ArrayList<MovieDTO> getMovieByKeyword(String keyword) {
		ArrayList<MovieDTO> result = new ArrayList<>();
		

		String sql = "SELECT "
		        + "mt.movieId, "
		        + "mt.movieName, "
		        + "mt.director, "
		        + "mt.runningTime, "
		        + "mt.genre, "
		        + "FORMAT(AVG(r.grade), 1) AS avgScore "
		        + "FROM movie mt "
		        + "JOIN review r USING(movieId) "
		        + "WHERE mt.movieName LIKE '%" + keyword + "%' "
		        + "OR mt.director LIKE '%" + keyword + "%' "
		        + "OR mt.genre LIKE '%" + keyword + "%' "
		        + "GROUP BY mt.movieId, mt.movieName, mt.director, mt.runningTime, mt.genre";
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
			System.out.println("데이터베이스 오류");
			
		}
		if(result.size() == 0) {
			return null;
		}
		return result;
	}

	public ArrayList<MovieDTO> getMovieByScheduleTheaterId(ArrayList<Integer> idList) {
		ArrayList<MovieDTO> list = new ArrayList<>();

		for (int i = 0; i < idList.size(); i++) {
			System.out.println((int)idList.get(i));
			String sql = "select * from movie where movieId = "+ (int)idList.get(i);

			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				if(rs.next()) {
					MovieDTO movie = new MovieDTO(
							rs.getInt("movieId"),
							rs.getString("movieName"),
							rs.getString("director"),
							rs.getString("runningTime"),
							rs.getString("genre")	
					);
					list.add(movie);
					System.out.println(list.size()+"현재"); 
					
				}
			} catch (SQLException e) {
			}
		}
		if(list.size()==0) {
			return null;
		}
		return list;
	}

}
