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
