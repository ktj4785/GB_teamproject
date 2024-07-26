package model.dao;

import java.util.ArrayList;

import model.dto.ReserveDTO;

public class ReserveSeat {
	public ArrayList<String> getSeatList(){
		ArrayList<ReserveDTO> reserveList = new ArrayList<>();
		ArrayList<String> seatList = new ArrayList<>();
		if(reserveList.size()==0) {
			return null;
		}
		for(ReserveDTO reserve : reserveList) {
			seatList.add(reserve.getSeat());
		}
		return seatList;
	}
	public void SeatList(ArrayList<String> seatList){
		for(String seat : seatList) {
		
		}
	}
	
}
