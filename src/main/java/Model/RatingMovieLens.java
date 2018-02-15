package Model;

public class RatingMovieLens {
	
	private String userIdMovieLens;
	private String movieIdMovieLens;
	private  String ratingMovieLens;
	
	public RatingMovieLens(String userIdMovieLens, String movieIdMovieLens, String ratingMovieLens) {
		super();
		this.userIdMovieLens = userIdMovieLens;
		this.movieIdMovieLens = movieIdMovieLens;
		this.ratingMovieLens = ratingMovieLens;
	}

	public String getUserIdMovieLens() {
		return userIdMovieLens;
	}

	public void setUserIdMovieLens(String userIdMovieLens) {
		this.userIdMovieLens = userIdMovieLens;
	}

	public String getMovieIdMovieLens() {
		return movieIdMovieLens;
	}

	public void setMovieIdMovieLens(String movieIdMovieLens) {
		this.movieIdMovieLens = movieIdMovieLens;
	}

	public String getRatingMovieLens() {
		return ratingMovieLens;
	}

	public void setRatingMovieLens(String ratingMovieLens) {
		this.ratingMovieLens = ratingMovieLens;
	}


	
}
