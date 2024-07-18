package model.dto;

public class ReserveDTO {
	
	private int reserveId;
	private int pNum;
	private int price;
	private boolean payment;
	private String userId;
	private int scheduleId;
	private String seat;
	
	public ReserveDTO(int reserveId, int pNum, int price, boolean payment, String userId, int scheduleId,String seat) {
		this.reserveId = reserveId;
		this.pNum = pNum;
		this.price = price;
		this.payment = payment;
		this.userId = userId;
		this.scheduleId = scheduleId;
		this.seat = seat;
	}

	public int getReserveId() {
		return reserveId;
	}

	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	
}
