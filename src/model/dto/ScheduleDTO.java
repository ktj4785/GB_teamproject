package model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ScheduleDTO {
	private int scheduleId;
	private Timestamp startTime;
	private Timestamp endTime;
	private int leftSeat;
	private int theaterId;
	private int movieID;

	public ScheduleDTO() {}

	public ScheduleDTO(int scheduleId, Timestamp startTime, Timestamp endTime, int leftSeat, int theaterId,
			int movieID) {
		super();
		this.scheduleId = scheduleId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.leftSeat = leftSeat;
		this.theaterId = theaterId;
		this.movieID = movieID;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
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

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	
	
	
}

	