package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.dao.MovieDAO;
import model.dao.ReserveDAO;
import model.dao.ScheduleDAO;
import model.dao.TheaterDAO;
import model.dto.MovieDTO;
import model.dto.ScheduleDTO;
import model.dto.TheaterDTO;

public class ReserveController {
	//
	public ArrayList<MovieDTO> searchMovieList(String keyword) {
		MovieDAO mdao = new MovieDAO();
		
		ArrayList<MovieDTO> mList = mdao.getMovieByKeyword(keyword);
		if(mList == null) {
			System.out.println("검색 결과가 존재하지 않습니다. 영화 제목, 감독 이름, 장르로 검색해 주세요");
			return null;
		}
		return mList;
	}

	public HashMap<String, Object> getmovieDetail(int movieId) {
		ScheduleDAO sdao = new ScheduleDAO();
		TheaterDAO tdao = new TheaterDAO();
		
		HashMap<String, Object> scheduleList = new HashMap<>();
		
		ArrayList<ScheduleDTO> list = sdao.getScheduleByMovieId(movieId); 
//		ScheduleDTO schedule = new ScheduleDTO(
//				list.get(0).getScheduleId(),
//				list.get(0).getStartTime(),
//				list.get(0).getEndTime(),
//				list.get(0).getLeftSeat(),
//				list.get(0).getTheaterId(),
//				list.get(0).getMovieId()
//				);		
		ArrayList<Integer> idList = new ArrayList<>();
		for(ScheduleDTO schedule : list) {
			idList.add(schedule.getTheaterId());
		}
		
		ArrayList<TheaterDTO> theater = tdao.getTheaterByScheduleTheaterId(idList);
		
		scheduleList.put("schedule", list);
		scheduleList.put("theater", theater);
		
		return scheduleList;
	}

	public ScheduleDTO getScheduleByScheduleId(int scheduleId) {
		ScheduleDAO sdao = new ScheduleDAO();
		return sdao.getScheduleByScheduleId(scheduleId);
	}

	public TheaterDTO getTheaterByScheduleId(int theaterId) {
		TheaterDAO tdao = new TheaterDAO();
		return tdao.getTheaterByTheaterId(theaterId);
		
	}

	

	public boolean reserveInfo(int pNum, int price, boolean payment, int scheduleId, String seat) {
		ReserveDAO rdao = new ReserveDAO();
		return rdao.insetReserveInfo(pNum, price, payment, scheduleId, seat);
	}
	
}
//1. 검색에서 영화를/// 영화 id랑 상영표 id를 넘겨받았을때 예약하기(데이터베이스 등록)	
//2. 영화id만 or 영화관만 받았을때 상영표 띄워주고 골라서 -> 예약하기
//	

//좌석(후처리)//좌석예약하는 메소드를 올true로
//	계좌에서 돈 빼가고
//	예약 객체 만들어서 데이터베이스에 입력
