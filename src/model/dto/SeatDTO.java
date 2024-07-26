package model.dto;

public class SeatDTO {
	private	int seatId;
	private String seatType;
	private int seatPrice;
	private	String seatLine;
	private String	seatRow;
	private int	theaterId;
	
	
	public SeatDTO(int seatId, String seatType, int seatPrice, String seatLine, String seatRow, int theaterId) {
		this.seatId = seatId;
		this.seatType = seatType;
		this.seatPrice = seatPrice;
		this.seatLine = seatLine;
		this.seatRow = seatRow;
		this.theaterId = theaterId;
	}


	public int getSeatId() {
		return seatId;
	}


	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}


	public String getSeatType() {
		return seatType;
	}


	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}


	public int getSeatPrice() {
		return seatPrice;
	}


	public void setSeatPrice(int seatPrice) {
		this.seatPrice = seatPrice;
	}


	public String getSeatLine() {
		return seatLine;
	}


	public void setSeatLine(String seatLine) {
		this.seatLine = seatLine;
	}


	public String getSeatRow() {
		return seatRow;
	}


	public void setSeatRow(String seatRow) {
		this.seatRow = seatRow;
	}


	public int getTheaterId() {
		return theaterId;
	}


	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}
	
}
