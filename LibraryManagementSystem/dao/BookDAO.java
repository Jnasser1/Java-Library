package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Author;
import lms.domain.Book;
import lms.domain.Genre;

public class BookDAO extends BaseDAO<Book> {

	public BookDAO(Connection conn) {
		super(conn);
	}

	public void add(Book book) throws SQLException, ClassNotFoundException {

		save("insert into tbl_book (bookId, title, pubId) values ( ?, ?, ?)",
		new Object[] { book.getBookId(), book.getTitle(), book.getPubId() });
		
	}

	public void addToEnd(Book book) throws SQLException, ClassNotFoundException {

		save("SET @max_id = (SELECT MAX(bookId) FROM tbl_book)", null);
		save("insert into tbl_book values (@max_id + 1, ?, ?)", new Object[] { book.getTitle(), book.getPubId() });
		
	}

	public void addToEndpID(Book book) throws SQLException, ClassNotFoundException {
		
		save("SET @max_id = (SELECT MAX(publisherId) FROM tbl_publisher)", null);
		save("insert into tbl_book values (?, ?, @max_id+1)", new Object[] { book.getBookId(), book.getTitle(), });
		
	}

	public void update(Book book) throws SQLException, ClassNotFoundException {
		save("update tbl_book set title = ?, pubId = ? where bookId = ?",
				new Object[] { book.getTitle(), book.getPubId(), book.getBookId() });
	}

	public void delete(Book book) throws SQLException, ClassNotFoundException {
		save("delete from tbl_book where bookId = ?", new Object[] { book.getBookId() });
	}

	public void deleteck(Book book) throws SQLException, ClassNotFoundException {
		save("delete from tbl_book where bookId = ? and pubId = ?", new Object[] { book.getBookId(), book.getPubId() });
	}

	public void insertBookAuthor(Book book, Author author) throws SQLException, ClassNotFoundException {
		save("insert into tbl_book_authors (bookId, authorId) values(?, ?)",
				new Object[] { book.getBookId(), author.getAuthorId() });
	}

	public void insertBookGenre(Genre genre, Book book) throws SQLException, ClassNotFoundException {
		save("insert into tbl_book_genres (genre_Id, bookId) values(?, ?)",
				new Object[] { genre.getGenreId(), book.getBookId() });
	}

	public List<Book> readAllBooks() throws SQLException, ClassNotFoundException {
		return this.read("select * from tbl_book", null);
	}

	public List<Book> readBooksByTitle(String title) throws SQLException, ClassNotFoundException {
		return this.read("select * from tbl_book where title = ?", new Object[] { title });
	}

	public Book readBookById(Integer bookId) throws SQLException, ClassNotFoundException {
		return readSingle("select * from tbl_book where bookId = ?", new Object[] { bookId });
	}

	/*
	 * public List<Book> readBookswithPositiveCopiesUsingBranchId(Integer branchId)
	 * throws SQLException, ClassNotFoundException { String sql =
	 * "  select B.title, B.bookId, B.pubID from " +
	 * "  tbl_book as B, tbl_book_copies as BC," +
	 * "  tbl_author as A, tbl_book_authors as BA" +
	 * "  where A.authorId=BA.authorId and" + "  BA.bookId=B.bookId and" +
	 * "  branchId = ? and " + "  BC.noOfCopies > 0";
	 * 
	 * return read(sql, new Object[] { branchId });
	 * 
	 * }
	 * 
	 * 
	 * public List<Book> readBookswithPositiveCopiesUsingBothID(Integer branchId,
	 * Integer bookId) throws SQLException, ClassNotFoundException { String sql =
	 * "  select B.title, B.bookId, B.pubID from " +
	 * "  tbl_book as B, tbl_book_copies as BC," +
	 * "  tbl_author as A, tbl_book_authors as BA" +
	 * "  where A.authorId=BA.authorId and" + "  BA.bookId=B.bookId and" +
	 * "  B.bookId = ? and" + "  BC.branchId = ? and " + "  BC.noOfCopies > 0";
	 * 
	 * return read(sql, new Object[] { bookId, branchId, });
	 * 
	 * }
	 */

	public List<Book> readBookswithPositiveCopiesNew(Integer branchId) throws SQLException, ClassNotFoundException {
		String sql = "select tbl_book.bookId, tbl_book.title, tbl_book.pubId "
				+ "from (tbl_book inner join tbl_book_copies on " + "tbl_book.bookId = tbl_book_copies.bookId) "
				+ "where branchId = ? and noOfCopies > 0";

		return read(sql, new Object[] { branchId, });

	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

		try {
			List<Book> bookList = new ArrayList<>();
			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("bookId"));
				book.setTitle(rs.getString("title"));
				book.setPubId(rs.getInt("pubId"));
				bookList.add(book);
			}
			return bookList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
		try {
			if (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getInt("bookId"));
				book.setTitle(rs.getString("title"));
				book.setPubId(rs.getInt("pubId"));
				return book;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}