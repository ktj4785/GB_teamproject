package controller;

import model.dao.MovieDAO;

public class MovieController {
	
	//영화 목록 불러오기
	public void getList(int choice) {
		MovieDAO mdao = new MovieDAO();
		return mdao.getlist();
	}
}
