package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Genre;

public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection conn) {
		super(conn);
	}

	public void add(Genre genre) throws SQLException, ClassNotFoundException {
		this.save("INSERT INTO tbl_genre VALUES (?, ?)", new Object[] { genre.getGenreId(), genre.getGenreName() });
	}

	// Adds a genre row with genre_name and genre_id = max(genre_id)+1
	public void addToEnd(Genre genre) throws SQLException, ClassNotFoundException {
		save("SET @max_id = (SELECT MAX(genre_id) FROM `tbl_genre`)", null);
		save("insert into tbl_genre values (@max_id + 1, ?)", new Object[] { genre.getGenreName() });
	}

	public void updateGenreName(Genre genre) throws SQLException, ClassNotFoundException {
		this.save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id=?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void delete(Genre genre) throws SQLException, ClassNotFoundException {
		this.save("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] { genre.getGenreId() });
	}

	public List<Genre> readAllGenres() throws SQLException, ClassNotFoundException {
		return this.read("select * from tbl_genre", null);
	}

	@Override
	List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Genre> GenreList = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			GenreList.add(genre);
		}

		return GenreList;
	}

	@Override
	Genre extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
		if (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			return genre;
		}
		return null;
	}

}