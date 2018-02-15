package Principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import Database.BancoDados;
import Model.Filme;
import Model.MovieLens;
import Model.RatingMovieLens;

public class WebService {

	public static List<MovieLens> lerArquivoMovieLens() {

		List<MovieLens> filmes = new ArrayList<MovieLens>(); int i=1;
		List<RatingMovieLens> filmesRating = new ArrayList<RatingMovieLens>();

		try {
			FileReader arq = new FileReader("links.csv");
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine(); 
			
			System.out.println("Linha: "+linha);
			while (linha != null) {
				String [] valores = linha.split(",");
				System.out.println("===================== Arquivo 1================================");	
				if(valores.length == 3) {
				System.out.println("Filme "+i+": "+linha+" tmdbId: "+valores[2]);
				
				MovieLens movieLens = new MovieLens(valores[0], valores[1], valores[2]);
				filmes.add(movieLens);
				i++;
				}
				linha = lerArq.readLine(); // lê da segunda até a última linha
				
			}

			arq.close();
		
			
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		System.out.println();

		return filmes;
	}

	public static String getJSON(MovieLens movie, int timeout) {

		HttpURLConnection c = null;
		String url = "https://api.themoviedb.org/3/movie/" + movie.getIdTmdb()
				+ "?api_key=f96f35dad2d93764c36ed623ec9148ff&language=en-US";
		BancoDados bancoDados = new BancoDados();

		try {
			URL u = new URL(url);
			c = (HttpURLConnection) u.openConnection();
			c.setRequestMethod("GET");
			c.setRequestProperty("Content-length", "0");
			c.setUseCaches(false);
			c.setAllowUserInteraction(false);
			c.setConnectTimeout(timeout);
			c.setReadTimeout(timeout);
			c.connect();
			int status = c.getResponseCode();
			// System.out.println(status);
			switch (status) {
			case 200:
				BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				// System.out.println(sb.toString());
				JSONObject json = new JSONObject(sb.toString());
				// JSONArray items = json.getJSONArray("results");

				int idFilme = Integer.parseInt(movie.getIdMovieLens());
				String titulo = json.getString("title");
				String descricao = json.getString("overview");
				double ratingImdb = json.getDouble("vote_average");
				int idImdb = Integer.parseInt(movie.getIdImdb());
				int idTmdb = Integer.parseInt(movie.getIdTmdb());
				

				Filme filme = new Filme(idFilme, titulo, descricao, ratingImdb, idImdb, idTmdb);

				System.out.println("\tTitulo: " + filme.getTitulo() + "\n\tDescricao: " + descricao
						+ "\n\tRating do IMDB: " + ratingImdb);
				System.out.println("\tInsere BD\n\n\n");
				bancoDados.insereFilme(filme);

				return sb.toString();
			case 201:
			}

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {

		List<MovieLens> filmes = lerArquivoMovieLens();


		for (int i = 0; i < filmes.size(); i++) {

			getJSON(filmes.get(i), 7000);

		}

	}

}
