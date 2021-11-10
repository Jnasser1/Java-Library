package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.BookAuthor;

public class BookAuthorDAO extends BaseDAO<BookAuthor> {

	public BookAuthorDAO(Connection conn) {
		super(conn);
	}

	public void add(BookAuthor book_author) throws SQLException, ClassNotFoundException {
		this.save("INSERT INTO tbl_book_authors VALUES (?, ?)",
				new Object[] { book_author.getBookId(), book_author.getAuthorId() });
	}

	// Only to be used in a single transaction when a new entry has been added to
	// author and book table.
	// Takes the recently changed maximum Ids and adds an element with those id
	// numbers.
	public void addToEnd() throws SQLException, ClassNotFoundException {
		save("SET @max_id = (SELECT MAX(bookId) FROM `tbl_book`)", null);
		save("SET @max_ida = (SELECT MAX(authorId) FROM `tbl_author`)", null);
		save("insert into tbl_book_authors values (@max_id, @max_ida)", null);
	}

	public void delete(BookAuthor book_author) throws SQLException, ClassNotFoundException {
		this.save("DELETE FROM tbl_book_authors WHERE authorId = ?", new Object[] { book_author.getAuthorId() });
	}

	public void deletebid(BookAuthor book_author) throws SQLException, ClassNotFoundException {
		this.save("DELETE FROM tbl_book_authors WHERE bookId = ?", new Object[] { book_author.getBookId() });
	}

	public List<BookAuthor> readAllBookAuthors() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_authors", null);
	}

	public BookAuthor readBookAuthorByAuthorId(Integer authorId) throws SQLException, ClassNotFoundException {
		return this.readSingle("select * from tbl_book_authors where authorId = ?", new Object[] { authorId });
	}

	public BookAuthor readBookAuthorsByBookId(Integer bookId) throws SQLException, ClassNotFoundException {
		return this.readSingle("select * from tbl_book_authors where bookId = ?", new Object[] { bookId });
	}

	@Override
	public List<BookAuthor> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

		List<BookAuthor> book_authorList = new ArrayList<>();
		while (rs.next()) {
			BookAuthor book_author = new BookAuthor();
			book_author.setBookId(rs.getInt("bookId"));
			book_author.setAuthorId(rs.getInt("authorId"));
			book_authorList.add(book_author);
		}
		return book_authorList;
	}

	@Override
	public BookAuthor extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
		if (rs.next()) {
			BookAuthor book_author = new BookAuthor();
			book_author.setBookId(rs.getInt("bookId"));
			book_author.setAuthorId(rs.getInt("authorId"));
			return book_author;
		}
		return null;
	}

}