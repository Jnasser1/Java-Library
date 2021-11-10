package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import lms.dao.AuthorDAO;
import lms.dao.BookAuthorDAO;
import lms.dao.BookDAO;
import lms.dao.GenreDAO;
import lms.dao.PublisherDAO;
import lms.domain.Author;
import lms.domain.Book;
import lms.domain.BookAuthor;
import lms.domain.Genre;
import lms.domain.Publisher;

public class AdminBookService {

	Utility util = new Utility();

	public String DisplayAllBooks() throws SQLException {
		Connection conn = null;
		String s;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			BookAuthorDAO badao = new BookAuthorDAO(conn);

			System.out.println("List of books and authors: ");

			List<BookAuthor> bookauthors = badao.readAllBookAuthors();
			// Use table bookauthors to get the book title and author name from bookId and
			// authorId
			int bookIndex = 1;
			for (BookAuthor bookauthor : bookauthors) {
				Book book = bdao.readBookById(bookauthor.getBookId());
				Author author = adao.readAuthorById(bookauthor.getAuthorId());
				System.out.println(bookIndex + ") " + book.getTitle() + " by " + author.getAuthorName());
				bookIndex++;
			}

			conn.commit();
			s = "Successfully displayed books";
		} catch (Exception e) {
			conn.rollback();
			s = "Problem has occured";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

	public String addBook() throws SQLException {
		Connection conn = null;
		String s;
		Book book = new Book();
		try {
			// Same connection for everything, using subclasses.
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			PublisherDAO pdao = new PublisherDAO(conn);
			GenreDAO gdao = new GenreDAO(conn);

			System.out.print("Enter title ('quit' to cancel): ");
			String bookName = InputHandler.InputString();
			if (bookName.equals("quit")) {
				return "Transaction cancelled";
			}

			// List authors out for admin to pick from.
			System.out.println("List of Authors: ");
			List<Author> authors = adao.readAllAuthors();
			int authorIndex = 1;
			for (Author a : authors) {
				System.out.println(authorIndex + ") " + a.getAuthorName());
				authorIndex++;
			}
			System.out.println(authorIndex + ") Cancel Transaction");
			int authorChoice = InputHandler.InputInt(1, authorIndex);
			if (authorChoice == authorIndex) {
				return "Transaction cancelled";
			}
			/*
			 * // User has chosen an author, now he chooses a genre for the book.
			 * System.out.println("List of Genres: "); List<Genre> genres =
			 * gdao.readAllGenres(); int genreIndex = 1; for (Genre g : genres) {
			 * System.out.println(genreIndex + ") " + g.getGenreName()); genreIndex++; }
			 * 
			 * System.out.println(genreIndex + ") Cancel Transaction"); int genreChoice =
			 * InputHandler.InputInt(1, genreIndex); if (genreChoice == genreIndex) { return
			 * "Transaction cancelled"; }
			 */

			System.out.println("List of Publishers ");
			List<Publisher> publishers = pdao.readAllPublishers();
			int publisherIndex = 1;
			for (Publisher p : publishers) {
				System.out.println(publisherIndex + ") " + p.getPublisherName());
				publisherIndex++;
			}
			System.out.println(publisherIndex + ") Cancel Transaction");
			int publisherChoice = InputHandler.InputInt(1, publisherIndex);
			if (publisherChoice == publisherIndex) {
				return "Transaction cancelled";
			}

			book.setTitle(bookName);
			book.setPubId(publishers.get(--publisherChoice).getPublisherId());
			bdao.addToEnd(book);
			conn.commit();
			s = "Successfully added " + bookName;
		} catch (Exception e) {
			conn.rollback();
			s = "Book not added";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

	public String updateBook() throws SQLException {
		Connection conn = null;
		String s;
		Book bookupdated = new Book();
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			PublisherDAO pdao = new PublisherDAO(conn);
			BookAuthorDAO badao = new BookAuthorDAO(conn);
			GenreDAO gdao = new GenreDAO(conn);
			System.out.println("Select Book you wish to update: ");

			List<BookAuthor> bookauthors = badao.readAllBookAuthors();
			List<Publisher> publishers = pdao.readAllPublishers();
			int bookIndex = 1;
			for (BookAuthor bookauthor : bookauthors) {
				Book book = bdao.readBookById(bookauthor.getBookId());
				Author author = adao.readAuthorById(bookauthor.getAuthorId());
				System.out.println(bookIndex + ") " + book.getTitle() + " by " + author.getAuthorName());
				bookIndex++;
			}

			System.out.println(bookIndex + ") Cancel Transaction");
			int bookChoice = InputHandler.InputInt(1, bookIndex);
			if (bookChoice == bookIndex) {
				return "Transaction Cancelled";
			}

			// Title
			System.out.print("Enter title ('quit' to cancel): ");
			String bookName = InputHandler.InputString();
			if (bookName.equals("quit")) {
				return "Transaction cancelled";
			}

			// Pubid
			System.out.println("List of Publishers ");
			int publisherIndex = 1;
			for (Publisher p : publishers) {
				System.out.println(publisherIndex + ") " + p.getPublisherName());
				publisherIndex++;
			}
			System.out.println(publisherIndex + ") Cancel Transaction");
			int publisherChoice = InputHandler.InputInt(1, publisherIndex);
			if (publisherChoice == publisherIndex) {
				return "Transaction cancelled";
			}

			List<Book> books = bdao.readAllBooks();

			bookupdated.setBookId(books.get(--bookChoice).getBookId());
			bookupdated.setTitle(bookName);
			bookupdated.setPubId(publishers.get(--publisherChoice).getPublisherId());
			bdao.update(bookupdated);
			conn.commit();
			s = "Successfully updated " + bookupdated.getTitle();
		} catch (Exception e) {
			conn.rollback();
			s = "Problem has occured, book not updated";
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

	public String deleteBook() throws SQLException {
		Connection conn = null;
		String s;
		Book book = new Book();
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			BookAuthorDAO badao = new BookAuthorDAO(conn);
			List<Book> books = bdao.readAllBooks();
			List<BookAuthor> bookauthors = badao.readAllBookAuthors();

			System.out.println("Select Book you wish to delete: ");

			int bookIndex = 1;
			// Use table bookauthors to get the book title and author name from bookId and
			// authorId
			for (BookAuthor bookauthor : bookauthors) {
				book = bdao.readBookById(bookauthor.getBookId());
				Author author = adao.readAuthorById(bookauthor.getAuthorId());
				System.out.println(bookIndex + ") " + book.getTitle() + " by " + author.getAuthorName());
				bookIndex++;
			}

			System.out.println(bookIndex + ") Cancel Transaction");
			int bookChoice = InputHandler.InputInt(1, bookIndex);
			if (bookChoice == bookIndex) {
				return "Transaction Cancelled";
			}

			book.setBookId(books.get(--bookChoice).getBookId());
			bdao.delete(book);

			conn.commit();
			s = "Successfully deleted book";
		} catch (Exception e) {
			conn.rollback();
			s = "Problem has occured, book not deleted";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

}