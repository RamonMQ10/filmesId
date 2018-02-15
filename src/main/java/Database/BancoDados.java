package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Filme;
import Model.Rating;

public class BancoDados {

	public Connection conectaBanco() {
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/movies_movielens?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insereFilme(Filme filme) {
		Connection conn = conectaBanco();
		PreparedStatement ps = null;

		String query = "INSERT INTO `movie` (`idMovieLens`, `title`, `description`, `ratingImdb`, `idIMDB`, `idTMDB`) VALUES (?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, filme.getIdFilme());
			ps.setString(2, filme.getTitulo());
			ps.setString(3, filme.getDescricao());
			ps.setDouble(4, filme.getRatingImdb());
			ps.setInt(5, filme.getIdImdb());
			ps.setInt(6, filme.getIdTmdb());
			ps.execute();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public void insereRating(Rating rating) {
		Connection conn = conectaBanco();
		PreparedStatement ps = null;

		String query = "INSERT INTO `filme` (`userIdMovieLens`, `movieIdMovieLens`, `ratingMovieLens`) VALUES (?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, rating.getUserIdMovieLens());
			ps.setInt(5, rating.getMovieIdMovieLens());
			ps.setDouble(4, rating.getRatingMovieLens());
			ps.execute();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}


}
