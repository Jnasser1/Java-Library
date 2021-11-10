package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.BookGenre;

public class BookGenreDAO extends BaseDAO<BookGenre> {

	public BookGenreDAO(Connection conn) {
		super(conn);
	}

	public void add(BookGenre book_genre) throws SQLException, ClassNotFoundException {
		this.save("INSERT INTO tbl_book_genres VALUES (?, ?)",
				new Object[] { book_genre.getGenreId(), book_genre.getBookId() });
	}

	// Change a genre for a book.
	public void updateGenreIdBG(BookGenre book_genre) throws SQLException, ClassNotFoundException {
		this.save("UPDATE tbl_book_genres SET genre_id=? where bookId = ?",
				new Object[] { book_genre.getGenreId(), book_genre.getBookId() });
	}

	public void delete(BookGenre book_genre) throws SQLException, ClassNotFoundException {
		this.save("DELETE FROM tbl_book_genres WHERE genre_id = ? AND bookId = ?",
				new Object[] { book_genre.getGenreId(), book_genre.getBookId() });
	}

	public List<BookGenre> readAllGenreIdBookIds() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_genres", null);
	}

	public BookGenre readBookGenreByBookId(Integer genre_id, Integer bookId)
			throws SQLException, ClassNotFoundException {
		return readSingle("SELECT * FROM tbl_book_genres WHERE bookId = ?", new Object[] { genre_id, bookId });
	}

	@Override
	public List<BookGenre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookGenre> book_genres = new ArrayList<>();
		while (rs.next()) {
			BookGenre book_genre = new BookGenre();
			book_genre.setGenreId(rs.getInt("genre_Id"));
			book_genre.setBookId(rs.getInt("bookId"));
		}
		return book_genres;
	}

	@Override
	public BookGenre extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
		if (rs.next()) {
			BookGenre book_genre = new BookGenre();
			book_genre.setGenreId(rs.getInt("genre_Id"));
			book_genre.setBookId(rs.getInt("bookId"));
			return book_genre;
		}
		return null;
	}

}