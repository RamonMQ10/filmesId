package Model;

public class Filme {
	
	private int idFilme;
	private String titulo;
	private String descricao;
	private double ratingImdb;
	private int idImdb;
	private int idTmdb;
	
	public Filme(int idFilme, String titulo, String descricao, double ratingImdb, int idImdb, int idTmdb) {
		super();
		this.idFilme = idFilme;
		this.titulo = titulo;
		this.descricao = descricao;
		this.ratingImdb = ratingImdb;
		this.idImdb = idImdb;
		this.idTmdb = idTmdb;
	}

	public int getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getRatingImdb() {
		return ratingImdb;
	}

	public void setRatingImdb(double ratingImdb) {
		this.ratingImdb = ratingImdb;
	}

	public int getIdImdb() {
		return idImdb;
	}

	public void setIdImdb(int idImdb) {
		this.idImdb = idImdb;
	}

	public int getIdTmdb() {
		return idTmdb;
	}

	public void setIdTmdb(int idTmdb) {
		this.idTmdb = idTmdb;
	}
	

}
