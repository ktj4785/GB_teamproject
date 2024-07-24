package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.dto.AccountDTO;

public class AccountDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public AccountDAO() {
		conn=DBConnection.getConnection();
	}

	public AccountDTO getAccountByUserid(String loginUser) {
		String sql = "select * from account where userId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				AccountDTO account = new AccountDTO(
						rs.getInt("accountId"),
						rs.getString("bank"),
						rs.getInt("balance"),
						rs.getString("userId")

				);
				return account;
			}
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;
	}

	public boolean deleteAccountByUserId(String loginUser)  {
		String sql = "delete from account where userId = ?";
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

	public boolean insertAccount(AccountDTO account) {
		String sql = "insert into account (accountId,bank,userId) values (?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getAccountId());
			ps.setString(2, account.getBank());
			ps.setString(3, account.getUserId());
			

			int result = ps.executeUpdate();
							
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
	}

	public boolean updateBalance(int c,String userId) {

		String sql = "update account set balance = ? where userId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, c);
			ps.setString(2, userId);
			
			int result = ps.executeUpdate();
							
			return result == 1;
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
		
	}

	public boolean updateAccountData(String cols, String newdata, String userId) {
		try {
			if(cols.equals("accountId")) {
				String sql = "update account set "+ cols+" = ? where userId = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(newdata));
				ps.setString(2, userId);

			}
			else if(cols.equals("bank")) {
				String sql = "update account set "+cols+" = ? where userId = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, newdata);
				ps.setString(2, userId);

			}
			int result = ps.executeUpdate();
			
			return result == 1;
			
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 " + e);
		}
		return false;
	}
	public AccountDTO getAccountByAccountId(String accountId) {
		String sql = "select * from account where accountId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				AccountDTO account = new AccountDTO(
						rs.getInt("accountId"),
						rs.getString("bank"),
						rs.getInt("balance"),
						rs.getString("userId")

				);
				return account;
			}
		} catch (SQLException e) {
			System.out.println("DB오류가 발생하였습니다 "+ e);
		}
		return null;
	}
	


}

