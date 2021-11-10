package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import lms.dao.AuthorDAO;
import lms.dao.BookAuthorDAO;
import lms.dao.BookDAO;
import lms.dao.GenreDAO;
import lms.domain.Author;
import lms.domain.Book;
import lms.domain.BookAuthor;
import lms.domain.Publisher;
import lms.dao.PublisherDAO;

public class AdminBookAuthorService {

	Utility util = new Utility();

	public String DisplayAllBooksAndAuthors() throws SQLException {
		Connection conn = null;
		String s;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			BookAuthorDAO badao = new BookAuthorDAO(conn);

			System.out.println("List of books and authors: ");

			List<BookAuthor> bookauthors = badao.readAllBookAuthors();
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

	// Will add a new Book, Author and BookAuthor...
	public String addBookAndAuthor() throws SQLException {
		Connection conn = null;
		String s;
		Book book = new Book();
		Author author = new Author();
		BookAuthor book_author = new BookAuthor();
		String ReturnStr = "";
		try {
			// Same connection for everything, using subclass constructor.
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			BookAuthorDAO badao = new BookAuthorDAO(conn);
			PublisherDAO pdao = new PublisherDAO(conn);

			System.out.print("Enter title ('quit' to cancel): ");
			String bookName = InputHandler.InputString();
			if (bookName.equals("quit")) {
				return "Transaction cancelled";
			}

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

			System.out.print("Enter Author name or 'quit' to cancel ");
			String authorName = InputHandler.InputString();
			if (authorName.equals("quit")) {
				return "Transaction cancelled";
			}

			author.setAuthorName(authorName);
			adao.addToEnd(author);
			conn.commit();
			ReturnStr = "Successfully added " + author.getAuthorName() + " as an" + " author.";

			badao.addToEnd();

			conn.commit();
			ReturnStr = "Successfully added book, author, and book_author to respective tables.";
		} catch (Exception e) {
			conn.rollback();
			ReturnStr = "Exception has occured, transaction was rolledback.";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return ReturnStr;
	}

	public String updateBookAndAuthor() throws SQLException {
		Connection conn = null;
		String s;
		Book bookupdated = new Book();
		Author authorupdated = new Author();
		Author author = new Author();
		Book book = new Book();
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
				book = bdao.readBookById(bookauthor.getBookId());
				author = adao.readAuthorById(bookauthor.getAuthorId());
				System.out.println(bookIndex + ") " + book.getTitle() + " by " + author.getAuthorName());
				bookIndex++;
			}

			System.out.println(bookIndex + ") Cancel Transaction");
			int bookChoice = InputHandler.InputInt(1, bookIndex);
			if (bookChoice == bookIndex) {
				return "Transaction Cancelled";
			}

			// Title
			System.out.print("Enter new title ('quit' to cancel): ");
			String bookName = InputHandler.InputString();
			if (bookName.equals("quit")) {
				return "Transaction cancelled";
			}

			// Pubid
			System.out.println("Choose a publisher. ");
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
			Integer bookID = books.get(--bookChoice).getBookId();

			bookupdated.setBookId(books.get(--bookChoice).getBookId());
			bookupdated.setTitle(bookName);
			bookupdated.setPubId(publishers.get(--publisherChoice).getPublisherId());

			BookAuthor bookauthor = badao.readBookAuthorsByBookId(bookID);
			authorupdated = adao.readAuthorById(bookID);
			// Author name
			System.out.print("Enter updated Author Name ('quit' to cancel 'N/A' for no change) ");
			String input_AuthorName = InputHandler.InputString();
			if (input_AuthorName.equals("quit")) {
				return "Transaction cancelled";
			}
			if (input_AuthorName.equals("N/A")) {
				authorupdated.setAuthorName(authorupdated.getAuthorName());
			} else {
				authorupdated.setAuthorName(input_AuthorName);
			}

			bdao.update(bookupdated);
			adao.update(authorupdated);

			conn.commit();
			s = "Successfully updated to title to " + bookupdated.getTitle() + " and author name to "
					+ authorupdated.getAuthorName();
		} catch (Exception e) {
			conn.rollback();
			s = "Problem has occured, author and book not updated";
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

	public String deleteBookAndAuthor() throws SQLException {
		Connection conn = null;
		String s;
		Book book = new Book();
		BookAuthor book_author = new BookAuthor();
		Author author = new Author();
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			BookAuthorDAO badao = new BookAuthorDAO(conn);

			List<Book> books = bdao.readAllBooks();
			List<BookAuthor> bookauthors = badao.readAllBookAuthors();

			System.out.println("Select Book and Author you wish to delete: ");

			int bookIndex = 1;
			for (BookAuthor bookauthor : bookauthors) {
				book = bdao.readBookById(bookauthor.getBookId());
				author = adao.readAuthorById(bookauthor.getAuthorId());
				System.out.println(bookIndex + ") " + book.getTitle() + " by " + author.getAuthorName());
				bookIndex++;
			}

			System.out.println(bookIndex + ") Cancel Transaction");
			int bookChoice = InputHandler.InputInt(1, bookIndex);
			if (bookChoice == bookIndex) {
				return "Transaction Cancelled";
			}

			int authorid = 0;
			Integer bookid = books.get(--bookChoice).getBookId();

			book_author.setBookId(bookid);
			book.setBookId(bookid);

			BookAuthor bac = bookauthors.get(5);
			author = adao.readAuthorById(bac.getAuthorId());

			book_author.setAuthorId(author.getAuthorId());
			book_author.setBookId(bookid);

			badao.deletebid(book_author);
			adao.delete(author);
			bdao.delete(book);

			conn.commit();
			s = "Successfully deleted book and author and book_author.";
		} catch (Exception e) {
			conn.rollback();
			s = "Problem has occured, entries not deleted";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

}

/*
 * System.out.print("Enter book title ('quit' to cancel): "); String bookName =
 * InputHandler.InputString(); if (bookName.equals("quit")) { return
 * "Transaction cancelled"; }
 * 
 * System.out.print("Enter Author name ('quit' to cancel): "); String
 * input_AuthorName = InputHandler.InputString(); if
 * (input_AuthorName.equals("quit")) { return "Transaction cancelled"; }
 */

/*
 * 
 * 
 * 
 * 
 * 
 * //Check if publisher is unique if so we can use the known pubid.
 * 
 * 
 * System.out.print("Enter Publisher name ('quit' to cancel): "); String
 * input_PubName = InputHandler.InputString();
 * 
 * if (input_PubName.equals("quit")) { return "Transaction cancelled"; }
 * 
 * List<Publisher> publishers = pdao.readAllPublishers(); List<String>
 * publisher_names = publishers.stream() .map(n->(n.getPublisherName()))
 * .collect(Collectors.toList());
 * 
 * Publisher new_publisher = new Publisher(1, "tbe", "tbe", "tbe");
 * 
 * 
 * 
 * 
 * int index =0;
 * 
 * // author.setAuthorName(input_AuthorName); // book.setTitle(bookName);
 * 
 * int pubID=0; String inputs = ""; String pubName, pubAdd, pubPhone; if
 * (publisher_names.contains(input_PubName)) {
 * 
 * adminbook.addBook(); index = publisher_names.indexOf(input_PubName);
 * Publisher known_publisher = publishers.get(index);
 * 
 * pubID=known_publisher.getPublisherId();
 * pubName=known_publisher.getPublisherName();
 * pubAdd=known_publisher.getPublisherAddress();
 * pubPhone=known_publisher.getPublisherPhone();
 * 
 * System.out.println("Publisher id matches publisher " + pubName);
 * 
 * book.setPubId(pubID);
 * 
 * bdao.addToEnd(book); adao.addToEnd(author); // Add author to database,
 * increasing authorId by 1.
 * 
 * book_author.setAuthorId(author.getAuthorId());
 * book_author.setBookId(book.getBookId()); badao.add(book_author);
 * 
 * }
 */

// If publisher is not already in database: see if user wants to add publisher.
/*
 * System.out.println("Publisher id for publisher " +input_PubName +
 * "was not found."); System.out.println(
 * "Would you like to add the publisher to the publisher table? y/n"); inputs =
 * InputHandler.InputString();
 * 
 * if (inputs.equals("y")) { // If so, add new publisher making the new
 * publisher have new max pubId AdminPublisherService serv = new
 * AdminPublisherService(); Integer new_pub_id = serv.addPublishergetId();
 * book.setPubId(new_pub_id); // Set the book with this pubId
 * bdao.addToEnd(book); // add this book to db } else if (inputs.equals("n")) {
 * bdao.add(book); //incremenet bookId and pubId by one, add new book to db. }
 * 
 */

// Book is added to db now.