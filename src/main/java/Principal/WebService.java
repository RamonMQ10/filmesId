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

	/*
	 * Ler o arquivo do movieLens e pegar os ids dos filmes no movielens, no imdb e no tmdb
	 */
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

				if(valores.length == 3) { // verificação para pegar somente os filmes que tem o id do tmdb		
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

	/*
	 * Pega os dados dos filmes como titulo, rating no imdb, descrição e etc
	 */
	public static String getJSON(MovieLens movie, int timeout) {

		HttpURLConnection httpURLConnection = null;
		String urlTmdb = "https://api.themoviedb.org/3/movie/" + movie.getIdTmdb()
				+ "?api_key=f96f35dad2d93764c36ed623ec9148ff&language=en-US";
		BancoDados bancoDados = new BancoDados();

		try {
			//  conexão com a url do imdb
			URL url = new URL(urlTmdb);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("Content-length", "0");
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setAllowUserInteraction(false);
			httpURLConnection.setConnectTimeout(timeout);
			httpURLConnection.setReadTimeout(timeout);
			httpURLConnection.connect();
			int status = httpURLConnection.getResponseCode(); // pega o status da requisição
			
			switch (status) {
			case 200:
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
				StringBuilder stringBuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line + "\n"); // transforma o resultado em string
				}
				
				bufferedReader.close();
				
				JSONObject jsonObject = new JSONObject(stringBuilder.toString()); // transforma a string em json
				

				int idFilme = Integer.parseInt(movie.getIdMovieLens());
				String titulo = jsonObject.getString("title");
				String descricao = jsonObject.getString("overview");
				double ratingImdb = jsonObject.getDouble("vote_average");
				int idImdb = Integer.parseInt(movie.getIdImdb());
				int idTmdb = Integer.parseInt(movie.getIdTmdb());
				

				Filme filme = new Filme(idFilme, titulo, descricao, ratingImdb, idImdb, idTmdb);

				System.out.println("\tTitulo: " + filme.getTitulo() + "\n\tDescricao: " + descricao
						+ "\n\tRating do IMDB: " + ratingImdb);
				
				
				bancoDados.insereFilme(filme); // salva o filme no banco de dados

				return stringBuilder.toString();
				
			}

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpURLConnection != null) {
				try {
					httpURLConnection.disconnect();
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
