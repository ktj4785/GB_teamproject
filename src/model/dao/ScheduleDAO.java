package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.dto.ScheduleDTO;

public class ScheduleDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public ScheduleDAO() {
		conn = DBConnection.getConnection();
	}
	
	public ArrayList<ScheduleDTO> getScheduleList(int choice, int asc) {
		//1.영화별: 영화이름, 감독, 러닝타임, 장르, 평점
		//2.시간별(영화, 영화관): 영화이름, 영화관이름, dimension, 영화 시작 시작, 잔여좌석
		//3.영화관별:영화관이름, dimension, 현재 날짜, 영화이름, 영화 시작 시작, 러닝타임, 장르, 잔여좌석
		//asc 1 오름 2 내림
		ArrayList<ScheduleDTO> list = new ArrayList<>();
		String order_col = "";
		String sql = "";
		
		switch(choice) {
		case 1:
			order_col = "movieName";
			sql = "select m.movieName, m.director, m.runningTime, m.genre, m.avgScore from movie m order by " + order_col;
			break;
		case 2:
			order_col = "s.startTime";
			sql = "select m.movieName, t.theaterName, t.dimension, s.startTime, s.leftSeat from schedule s join movie m on s.movieId = m.movieId join theater t on s.theaterId = t.theaterId order by " + order_col;
			break;
		case 3:
			order_col = "t.theaterName";
			sql = "select m.movieName, t.dimension, m.movieName, s.startTime, m.runningTime, m.genre, s.leftSeat from schedule s join movie m on s.movieId = m.movieId join theater t on s.theaterId = t.theaterId order by " + order_col;
			break;
		}
		
		if(asc == 1) {
			sql += " asc";
		} else {
			sql += " desc";
		}
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ScheduleDTO schedule = new ScheduleDTO();
                if (choice == 1) {
                    // choice가 1일 때에 맞는 생성자 사용
                    schedule = new ScheduleDTO(
                            rs.getString("movieName"),
                            rs.getString("director"),
                            rs.getString("runningTime"),
                            rs.getString("genre"),
                            rs.getDouble("avgScore")
                    );
                } else if (choice == 2) {
                    schedule = new ScheduleDTO(
                    		rs.getString("movieName"),
                            rs.getString("theaterName"),
                            rs.getString("dimension"),
                            rs.getTimestamp("startTime"),
                            rs.getInt("leftSeat")
                    );
                } else if (choice == 3){
                    schedule = new ScheduleDTO(
                    		rs.getString("theaterName"),
                    		rs.getString("dimension"),
                            rs.getString("movieName"),
                            rs.getTimestamp("startTime"),
                            rs.getInt("leftSeat"),
                            rs.getString("genre"),
                            rs.getString("runningTime")
                    );
                }
                list.add(schedule);
			}
		} catch (SQLException e) {
		}
		
		return list.isEmpty() ? null : list;
	}

}
