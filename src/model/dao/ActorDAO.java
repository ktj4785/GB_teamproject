package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.dto.ActorDTO;

public class ActorDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	public ActorDAO() {
		conn = DBConnection.getConnection();
	}

	public ArrayList<ActorDTO> getActorByKeyword(String keyword) {
		ArrayList<ActorDTO> result = new ArrayList<>();

		String sql = "select * from moive where actorName like('%"+keyword+"%')";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ActorDTO actor = new ActorDTO(
						rs.getInt("actorId"),
						rs.getString("actorName"),
						rs.getInt("movieId")
				);
				result.add(actor);
			}
		} catch (SQLException e) {
		}
		if(result == null) {
			return null;
		}
		return result;
	}

}