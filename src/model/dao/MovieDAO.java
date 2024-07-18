package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.DBConnection;

public class MovieDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public MovieDAO() {		
		conn = DBConnection.getConnection();
	}
	
	public void getlist(int choice) {
		
		switch(choice) {
		case 1://평점순으로 보기
				String sql = "select * from review order by grade";
				ps = conn.prepareStatement(sql);
				break;
				
		case 2://이름순으로 보기
				String sql2 = "select * from movie order by movieName";
				ps = conn.prepareStatement(sql);
				break;
		}
	
	}
}
