package controller;

import java.sql.Timestamp;
import java.util.HashMap;

import model.Session;
import model.dao.AccountDAO;
import model.dao.MovieDAO;
import model.dao.ReserveDAO;
import model.dao.ScheduleDAO;
import model.dao.TheaterDAO;
import model.dto.AccountDTO;
import model.dto.MovieDTO;
import model.dto.ReserveDTO;
import model.dto.ScheduleDTO;
import model.dto.TheaterDTO;

public class ReserveController {

	public HashMap<String, Object> getReserveDetail(int scheduleId) {
		ScheduleDAO sdao = new ScheduleDAO();
		MovieDAO mdao = new MovieDAO();
		TheaterDAO tdao = new TheaterDAO();
		ScheduleDTO schedule = sdao.getScheduleById(scheduleId);
		MovieDTO movie = mdao.getMovieById(schedule.getMovieID());
		TheaterDTO theater = tdao.getTheaterById(schedule.getTheaterId());
		
		Timestamp startTime = schedule.getStartTime();
		String movieName = movie.getMovieName();
		String theaterName = theater.getTheaterName();
		HashMap<String, Object> reserveDetail = new HashMap<>();
		reserveDetail.put("startTime", startTime);
		reserveDetail.put("movieName", movieName);
		reserveDetail.put("theaterName", theaterName);
		return reserveDetail;
	}

	public boolean cancelReserve(int reserveId) {
		ReserveDAO rdao = new ReserveDAO();
		AccountDAO acdao = new AccountDAO();
		ReserveDTO reserve = rdao.getReserveByReserveid(reserveId);
		int price = reserve.getPrice();
		if(reserve.isPayment()) {
			String userId = (String)Session.getData("loginUser");
			AccountDTO account = acdao.getAccountByUserid(userId);
			int balance = account.getBalance()+price ;
			acdao.updateBalance(balance,userId);
		}
		return rdao.deleteReserveByReserveId(reserveId);
		
	}

}
