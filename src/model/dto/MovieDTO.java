package model.dto;

public class MovieDTO {
	int movieId;
	String movieName;
	String director;
	String runningTime;
	String genre;
	double avgScore;
	
	public MovieDTO() {}

	public MovieDTO(int movieId, String movieName, String director, String runningTime, String genre, double avgScore) {
		this.movieId = movieId;
		this.movieName = movieName;
		this.director = director;
		this.runningTime = runningTime;
		this.genre = genre;
		this.avgScore = avgScore;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
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

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
	
	
}
