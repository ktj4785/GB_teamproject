package model.dto;

public class TheaterDTO {
	private int theaterId;
	private String theaterName;
	private String theaterAddr;
	private int seatCnt;
	private String dimension;
	
	public TheaterDTO() {
	}

	public TheaterDTO(int theaterId, String theaterName, String theaterAddr, int seatCnt, String dimension) {
		this.theaterId = theaterId;
		this.theaterName = theaterName;
		this.theaterAddr = theaterAddr;
		this.seatCnt = seatCnt;
		this.dimension = dimension;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getTheaterAddr() {
		return theaterAddr;
	}

	public void setTheaterAddr(String theaterAddr) {
		this.theaterAddr = theaterAddr;
	}

	public int getSeatCnt() {
		return seatCnt;
	}

	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}



}
