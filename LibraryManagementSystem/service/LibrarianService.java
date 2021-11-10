package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.dao.AuthorDAO;
import lms.dao.BookAuthorDAO;
import lms.dao.BookCopyDAO;
import lms.dao.BookDAO;

import lms.dao.LibraryBranchDAO;
import lms.domain.Author;
import lms.domain.Book;
import lms.domain.BookAuthor;
import lms.domain.BookCopy;

import lms.domain.LibraryBranch;

public class LibrarianService {

	private LibraryBranchDAO lbdao;
	private BookCopyDAO bcdao;
	private static Utility util = new Utility();

	public LibrarianService(BookCopyDAO bcdao, LibraryBranchDAO dao) {
		this.lbdao = dao;
		this.bcdao = bcdao;
	}

	public LibrarianService() {
		this.bcdao = null;
		this.lbdao = null;

	}

	public void setBookDAO(BookCopyDAO bcdao, LibraryBranchDAO lbdao) {
		this.bcdao = bcdao;

	}

	public void setLibraryBranchDAO(BookCopyDAO bcdao, LibraryBranchDAO lbdao) {
		this.lbdao = lbdao;

	}

	public List<LibraryBranch> readAllBranches() throws SQLException {
		Connection conn = null;
		List<LibraryBranch> branches = new ArrayList<>();
		try {
			conn = util.getConnection();
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			branches = ldao.readEverythingBranches();

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("Could not retrieve list of branches.");

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return branches;
	}

	public String updateBranchName(Integer branchId, String new_name) throws SQLException {
		Connection conn = null;
		String ReturnStr;
		try {
			conn = util.getConnection();
			// Instantiate the data access object
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			// Initalize the chosen branch.
			LibraryBranch branch = ldao.readBranchFromId(branchId);
			// Update the branch field within java
			branch.setBranchName(new_name);
			// Pass this update to the database.
			ldao.update(branch);
			// Commit the transaction if no exceptions have occured.
			conn.commit();
			ReturnStr = "Successfully updated branch name";
		} catch (Exception e) {
			conn.rollback();
			ReturnStr = "Failed to update branch name.";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return ReturnStr;
	}

	public static String updateBranchAddress(Integer branchId, String new_address) throws SQLException {
		Connection conn = null;
		String ReturnStr;
		try {
			conn = util.getConnection();
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			LibraryBranch branch = ldao.readBranchFromId(branchId);
			branch.setBranchAddress(new_address);
			ldao.update(branch);
			conn.commit();
			ReturnStr = "Successfully updated branch address";
		} catch (Exception e) {
			conn.rollback();
			ReturnStr = "Failed to update branch address.";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return ReturnStr;
	}

	public List<Book> readAllBooks() throws SQLException {
		Connection conn = null;
		List<Book> books = new ArrayList<>();
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			// return whole table of books.
			books = bdao.readAllBooks();
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return books;
	}

	public Author readAuthorFromId(Integer authorId) throws SQLException {
		Connection conn = null;
		Author author = new Author();
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			author = adao.readAuthorById(authorId);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return author;
	}

	public BookCopy getBookCopy(Integer bookId, Integer branchId) throws SQLException {
		Connection conn = null;
		BookCopy bc = null;
		try {
			conn = util.getConnection();
			BookCopyDAO bcdao = new BookCopyDAO(conn);
			bc = bcdao.readBookCopiesByBookIdandBranchId(bookId, branchId);
			if (bc == null) {
				bc.setNoOfCopies(0);
				bc.setBookId(bookId);
				bc.setBranchId(branchId);
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return bc;
	}

	public void AddBookCopy(BookCopy bookcopy) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopyDAO bcdao = new BookCopyDAO(conn);
			bcdao.add(bookcopy);
			conn.commit();
			System.out.println("Bookcopy was successfully added.");
		} catch (Exception e) {
			System.out.println("The book copy could not be added.");
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public BookAuthor readBookAuthorFromBookId(Integer bookId) throws SQLException {
		Connection conn = null;
		BookAuthor book_author = new BookAuthor();
		try {
			conn = util.getConnection();
			BookAuthorDAO badao = new BookAuthorDAO(conn);
			book_author = badao.readBookAuthorsByBookId(bookId);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return book_author;
	}

	public void updateNoOfCopies(BookCopy bookCopy, Integer new_NoOfCopies) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopyDAO bcdao = new BookCopyDAO(conn);
			// Change the noOfCopies of the passed bookcopy
			bookCopy.setNoOfCopies(new_NoOfCopies);
			// Update in the db
			bcdao.updateNoOfCopiesBC(bookCopy);
			System.out.println("Updated copies");
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}