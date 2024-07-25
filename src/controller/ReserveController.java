package controller;

import java.util.HashMap;

import model.Session;
import model.dao.AccountDAO;
import model.dao.MovieDAO;
import model.dao.ReserveDAO;
import model.dao.ScheduleDAO;
import model.dao.TheaterDAO;
import model.dto.MovieDTO;
import model.dto.ReserveDTO;
import model.dto.ScheduleDTO;
import model.dto.TheaterDTO;

public class ReserveController {

	//예약 정보 조회, 해쉬맵으로 각 데이터 불러와서 저장하고 반환함
	public HashMap<String, Object> getReserveDetail(int scheduleId) {
		ScheduleDAO sdao = new ScheduleDAO();
		ScheduleDTO schedule = sdao.getSchedule(scheduleId);
		MovieDAO mdao = new MovieDAO();
		MovieDTO movie = mdao.getMovieByMovieId(schedule.getMovieId());
		TheaterDAO tdao = new TheaterDAO();
		TheaterDTO theater = tdao.getTheaterByTheaterId(schedule.getTheaterId());
		
		
		
		HashMap<String, Object> datas = new HashMap<>();
		datas.put("scheduleStartTime", schedule.getStartTime());
		datas.put("movieName", movie.getMovieName());
		datas.put("theaterName", theater.getTheaterName());
		return datas;
	}
	//예약 취소하는 메소드 만약 결제까지한 상황이라면 환불절차도 진행
	public boolean deleteReserve(ReserveDTO reserve,int balance) {
		ReserveDAO rdao = new ReserveDAO();
		String userId = (String)Session.getData("loginUser");
		if(reserve.isPayment()) {
			AccountDAO acdao = new AccountDAO();
			acdao.updateBalance(reserve.getPrice()+balance, userId);
		}
		return rdao.deleteReserveByReserveId(reserve.getReserveId());
		
	}

}
