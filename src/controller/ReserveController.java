package controller;

import java.util.HashMap;

import model.Session;
import model.dao.AccountDAO;
import model.dao.MovieDAO;
import model.dao.ReserveDAO;
import model.dao.ScheduleDAO;
import model.dao.SeatDAO;
import model.dao.TheaterDAO;
import model.dto.MovieDTO;
import model.dto.ReserveDTO;
import model.dto.ScheduleDTO;
import model.dto.SeatDTO;
import model.dto.TheaterDTO;
import java.util.ArrayList;

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
	public ArrayList<SeatDTO> getSeatList(int scheduleId) {
		SeatDAO sdao = new SeatDAO();
		return sdao.getSeatBySchduelId(scheduleId);
		
	}
	public ArrayList<String> getReserveSeat(int scheduleId){
		ReserveDAO rdao = new ReserveDAO();
		ArrayList<ReserveDTO> list = rdao.getReserveListByScheduleID(scheduleId);
		if(list==null){
			return null;
		}
		ArrayList<String> seatlist = new ArrayList<>();
		for (ReserveDTO reserve : list) {
			String[] reserveSeat = reserve.getSeat().split(":");
			for(String s: reserveSeat) {
				if (!s.isEmpty()) {
					seatlist.add(s);
	            }
			}
		}
		return seatlist;
	}
	
	
}
