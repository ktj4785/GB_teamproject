package controller;



import java.util.ArrayList;

import model.dao.MovieDAO;
import model.dto.MovieDTO;

public class MovieController {

	// 영화 목록 불러오기
	public ArrayList<MovieDTO> getList(int choice) {
		MovieDAO mdao = new MovieDAO();
		return mdao.getlist(choice);
	}

	public ArrayList<MovieDTO> searchMovieName(String ans) {
		MovieDAO mdao = new MovieDAO();
		return mdao.getMovieName(ans);
	}
	public ArrayList<MovieDTO> searchActorName(String ans) {
		MovieDAO mdao = new MovieDAO();
		return mdao.getActorName(ans);
	}

	public ArrayList<MovieDTO> searchDirectorName(String ans) {
		MovieDAO mdao = new MovieDAO();
		return mdao.getDirectorName(ans);
	}
}
