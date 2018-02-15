package Model;

public class Rating {
	
	private int userIdMovieLens;
	private int movieIdMovieLens;
	private  Double ratingMovieLens;
	
	public Rating(int userIdMovieLens, int movieIdMovieLens, Double ratingMovieLens) {
		super();
		this.userIdMovieLens = userIdMovieLens;
		this.movieIdMovieLens = movieIdMovieLens;
		this.ratingMovieLens = ratingMovieLens;
	}

	public int getUserIdMovieLens() {
		return userIdMovieLens;
	}

	public void setUserIdMovieLens(int userIdMovieLens) {
		this.userIdMovieLens = userIdMovieLens;
	}

	public int getMovieIdMovieLens() {
		return movieIdMovieLens;
	}

	public void setMovieIdMovieLens(int movieIdMovieLens) {
		this.movieIdMovieLens = movieIdMovieLens;
	}

	public Double getRatingMovieLens() {
		return ratingMovieLens;
	}

	public void setRatingMovieLens(Double ratingMovieLens) {
		this.ratingMovieLens = ratingMovieLens;
	}

	
}
