package controller;



import java.util.ArrayList;

import model.dao.MovieDAO;
import model.dto.MovieDTO;
import model.dao.ScheduleDAO;
import model.dao.TheaterDAO;
import model.dto.ScheduleDTO;
import model.dto.TheaterDTO;

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


	//영화관 주소 검색 메서드
	public ArrayList<TheaterDTO> search(String theaterName) {
		TheaterDAO tdao = new TheaterDAO();
		ArrayList<TheaterDTO> list = tdao.getTheaterByTheaterName(theaterName);
		
		if(list == null) {
			return null;
		} 
		else {
			ArrayList<TheaterDTO> result = new ArrayList<>();
			for(TheaterDTO theater : list) {
				if(theater.getTheaterName().contains(theaterName)) {
					result.add(theater);
				}
			}
			return result;
		}
	}

	//상영시간표 조회 메서드
	public ArrayList<ScheduleDTO> getScheduleList(int choice, int asc) {
		ScheduleDAO sdao = new ScheduleDAO();
		return sdao.getScheduleList(choice, asc);
	}
}

