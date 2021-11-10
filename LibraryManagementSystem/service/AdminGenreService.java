package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.dao.GenreDAO;
import lms.domain.Genre;

public class AdminGenreService {

	Utility util = new Utility();
	private GenreDAO gdao;
	private GenreDAO genredao;
	
	public void setService(GenreDAO gdao) {
		this.gdao = gdao;
	}

	public List<Genre> DisplayAllGenres() throws SQLException {
		Connection conn = null;
		String str;
		List<Genre> genre_list = new ArrayList<>();
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);

			System.out.println("List of Genres [genre-id, genre]: ");
			genre_list = gdao.readAllGenres();
			int genreIndex = 1;
			for (Genre g : genre_list) {
				System.out.println(genreIndex + ") " + g.getGenreId() + ", " + g.getGenreName());
				genreIndex++;
			}

			conn.commit();
			str = "Successfully displayed genre id's and names.";

		} catch (Exception e) {
			conn.rollback();
			str = "Problem has occured could not display genres";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		System.out.println(str);
		return genre_list;
	}

	public String addGenre() throws SQLException {
		Connection conn = null;
		String str;
		Genre genre = new Genre();
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);

			System.out.print("Enter Genre Name ('quit' to cancel): ");
			String genreName = InputHandler.InputString();
			if (genreName.equals("quit")) {
				return "Transaction cancelled";
			}

			genre.setGenreName(genreName);
			gdao.addToEnd(genre); // increments genreid by 1 and adds to the db.
			conn.commit();
			str = "Successfully added " + genre.getGenreName();
		} catch (Exception e) {
			conn.rollback();
			str = "Genre could not be added";
			e.printStackTrace();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return str;
	}

	public String updateGenre() throws SQLException {
		Connection conn = null;
		String str;
		Genre genre = new Genre();
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);

			System.out.println("Select Genre you wish to update: ");
			List<Genre> genre_list = gdao.readAllGenres();
			int genreIndex = 1;
			for (Genre g : genre_list) {
				System.out.println(genreIndex + ") " + g.getGenreId() + ", " + g.getGenreName());
				genreIndex++;
			}

			System.out.println(genreIndex + ") Cancel Transaction");
			int genreChoice = InputHandler.InputInt(1, genreIndex);
			if (genreChoice == genreIndex) {
				return "Transaction Cancelled";
			}

			System.out.print("Enter new name ('quit' to cancel): ");
			String genreName = InputHandler.InputString();
			if (genreName.equals("quit")) {
				return "Transaction cancelled";
			}

			genre.setGenreId(genre_list.get(--genreChoice).getGenreId());
			genre.setGenreName(genreName);
			gdao.updateGenreName(genre);
			conn.commit();
			str = "Successfully updated to name " + genre.getGenreName();
		} catch (Exception e) {
			conn.rollback();
			str = "Problem has occured, genre was not updated";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return str;
	}

	public String deleteGenre() throws SQLException {
		Connection conn = null;
		String str;
		Genre genre = new Genre();
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);

			System.out.println("Select Genre you wish to delete: ");
			List<Genre> genre_list = gdao.readAllGenres();
			int genreIndex = 1;
			for (Genre g : genre_list) {
				System.out.println(genreIndex + ") " + g.getGenreId() + ", " + g.getGenreName());
				genreIndex++;
			}

			System.out.println(genreIndex + ") Cancel Transaction");
			int genreChoice = InputHandler.InputInt(1, genreIndex);
			if (genreChoice == genreIndex) {
				return "Transaction Cancelled";
			}

			genre.setGenreId(genre_list.get(--genreChoice).getGenreId());
			gdao.delete(genre);

			conn.commit();
			str = "Successfully deleted genre";
		} catch (Exception e) {
			conn.rollback();
			str = "Problem has occured, genre was not deleted";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return str;
	}

}