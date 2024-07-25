package model.dto;

public class ActorDTO {
	private int actorId;
	private String actorName;
	private int movieId;

	public ActorDTO() {}

	public ActorDTO(int actorId, String actorName, int movieId) {
		this.actorId = actorId;
		this.actorName = actorName;
		this.movieId = movieId;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


}