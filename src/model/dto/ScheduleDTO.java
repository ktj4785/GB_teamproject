package model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ScheduleDTO {
	private int scheduleId;
	private Timestamp startTime;
	private Timestamp endTime;
	private int leftSeat;
	private String movieName;
	private String director;
	private String runningTime;
	private String genre;
	private double avgScore;
	private String theaterName;
	private String dimension;
	private int theaterId;
	private int	movieId;

	public ScheduleDTO() {
	}
	
	
	public ScheduleDTO(int scheduleId, Timestamp startTime, Timestamp endTime, int leftSeat, int theaterId,
			int movieId) {
		this.scheduleId = scheduleId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.leftSeat = leftSeat;
		this.theaterId = theaterId;
		this.movieId = movieId;
	}


	public ScheduleDTO(String movieName, String director, String runningTime, String genre, Double avgScore) {
		this.movieName = movieName;
		this.director = director;
		this.runningTime = runningTime;
		this.genre = genre;
		this.avgScore = avgScore;
	}

	public ScheduleDTO(String movieName, String theaterName, String dimension, Timestamp startTime, int leftSeat) {
		this.startTime = startTime;
		this.leftSeat = leftSeat;
		this.movieName = movieName;
		this.theaterName = theaterName;
		this.dimension = dimension;
	}

	public ScheduleDTO(String theaterName, String dimension, String movieName, Timestamp startTime, int leftSeat, String genre, String runningTime) {
		this.startTime = startTime;
		this.leftSeat = leftSeat;
		this.movieName = movieName;
		this.runningTime = runningTime;
		this.genre = genre;
		this.theaterName = theaterName;
		this.dimension = dimension;
	}

	public ScheduleDTO(int scheduleId, String movieName, String director, String runningTime, String genre, double avgScore, String theaterName, String dimension, Timestamp startTime, int leftSeat) {
		this.scheduleId = scheduleId;
		this.movieName = movieName;
		this.director = director;
		this.runningTime = runningTime;
		this.genre = genre;
		this.avgScore = avgScore;
		this.theaterName = theaterName;
		this.dimension = dimension;
		this.startTime = startTime;
		this.leftSeat = leftSeat;
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

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
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