package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.dto.UserDTO;



public class UserDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public UserDAO() {
		conn = DBConnection.getConnection();
	}
	
	public UserDTO getUserByUserid(String userid) {
		String sql = "select * from user where userId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			
			rs = ps.executeQuery();
			
			//결과가 있다면 한 줄 꺼내서
			if(rs.next()) {
				//객체로 만들고
				UserDTO user = new UserDTO(
						rs.getString("userId"),
						rs.getString("userPw"),
						rs.getString("userName"),
						rs.getInt("userAge"),
						rs.getString("socialNum"),
						rs.getString("phone"),
						rs.getString("userAddr")
				);
				//리턴
				return user;
			}
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;
	}

	public boolean insertUser(UserDTO user) {
		String sql = "insert into user values(?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserPw());
			ps.setString(3, user.getUserName());
			ps.setInt(4, user.getUserAge());
			ps.setString(5, user.getSocialNum());
			ps.setString(6, user.getPhone());
			ps.setString(7, user.getUserAddr());
			
			int result = ps.executeUpdate();
			
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
	}

	public boolean deleteUser(String loginUser) {
		String sql = "delete from user where userId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser);
			
			int result = ps.executeUpdate();
							
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
	}

	public boolean updateUserInfo(String column, String newInfo, String userId) {
		String sql = "update set ?=? where userId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, column);
			ps.setString(2, newInfo);
			ps.setString(3, userId);
			
			int result = ps.executeUpdate();
							
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
		
	}
	

}