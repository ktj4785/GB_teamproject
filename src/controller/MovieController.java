package controller;

import java.util.ArrayList;

import model.dao.ScheduleDAO;
import model.dao.TheaterDAO;
import model.dto.ScheduleDTO;
import model.dto.TheaterDTO;

public class MovieController {

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