package model.dto;

import java.time.LocalDateTime;

public class ScheduleDTO {
	private int scheduleId;
	private String startTime; 
	private String endTime;
	private int leftSeat;
	private int theaterId;
	private int movieId;
	
	public ScheduleDTO() {}

	public ScheduleDTO(int scheduleId, String startTime, String endTime, int leftSeat, int theaterId,
			int movieId) {
		this.scheduleId = scheduleId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.leftSeat = leftSeat;
		this.theaterId = theaterId;
		this.movieId = movieId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getLeftSeat() {
		return leftSeat;
	}

	public void setLeftSeat(int leftSeat) {
		this.leftSeat = leftSeat;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	
	
	
}
