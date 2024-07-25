package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.Session;

public class ReserveDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public ReserveDAO() {
		conn = DBConnection.getConnection();
	}

	public boolean insetReserveInfo(int pNum, int price, boolean payment, int scheduleId, String seat) {
		String sql = "insert into reserve (pNum, price, payment, userId, scheduleId, seat) values(?, ?, ?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pNum);
			ps.setInt(2, price);
			ps.setBoolean(3, payment);
			ps.setString(4, (String) Session.getData("loginUser"));
			ps.setInt(4, scheduleId);
			ps.setString(5, seat);
			
			int result = ps.executeUpdate();
			
			return result == 1;
		} catch (SQLException e) {
		}
		return false;
	}

}
