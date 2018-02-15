package Model;

public class MovieLens {
	
	private String idMovieLens;
	private String idImdb;
	private String idTmdb;
	
	public MovieLens(String idMovieLens, String idImdb, String idTmdb) {
		super();
		this.idMovieLens = idMovieLens;
		this.idImdb = idImdb;
		this.idTmdb = idTmdb;
	}

	public String getIdMovieLens() {
		return idMovieLens;
	}

	public void setIdMovieLens(String idMovieLens) {
		this.idMovieLens = idMovieLens;
	}

	public String getIdImdb() {
		return idImdb;
	}

	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	public String getIdTmdb() {
		return idTmdb;
	}

	public void setIdTmdb(String idTmdb) {
		this.idTmdb = idTmdb;
	}
	

}
