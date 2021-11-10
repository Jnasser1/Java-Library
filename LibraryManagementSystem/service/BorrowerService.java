package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lms.domain.LibraryBranch;
import lms.dao.BookCopyDAO;
import lms.dao.AuthorDAO;
import lms.dao.BookAuthorDAO;
import lms.domain.Author;
import lms.dao.BookLoanDAO;
import lms.dao.BookDAO;
import lms.dao.BorrowerDAO;
import lms.dao.LibraryBranchDAO;
import lms.domain.BookLoan;
import lms.domain.Book;
import lms.domain.BookAuthor;
import lms.domain.BookCopy;
import lms.domain.Borrower;

public class BorrowerService {

	private BorrowerDAO bdao;

	Utility util = new Utility();

	public List<Borrower> readAllBorrowers() throws SQLException {
		Connection conn = null;
		List<Borrower> borrowers = new ArrayList<>();
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			borrowers = bdao.readAllBorrowers();
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return borrowers;
	}

	public List<LibraryBranch> readAllBranches() throws SQLException {
		Connection conn = null;
		List<LibraryBranch> branches = new ArrayList<>();
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			branches = lbdao.readEverythingBranches();
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return branches;
	}

	public List<BookCopy> readAllBookCopies() throws SQLException {
		Connection conn = null;
		List<BookCopy> book_copies = new ArrayList<>();
		try {
			conn = util.getConnection();
			BookCopyDAO bcdao = new BookCopyDAO(conn);
			book_copies = bcdao.readAllBookCopies();
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return book_copies;
	}

	public List<Book> readAllBooksWithPositiveCopiesNew(Integer branchId) throws SQLException {
		Connection conn = null;
		List<Book> books = new ArrayList<>();
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			books = bdao.readBookswithPositiveCopiesNew(branchId);
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
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return author;
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

	public void CheckOutBook(Integer bookId, Integer branchId, Integer cardNo) throws SQLException {
		Connection conn = null;
		BookCopy bc = new BookCopy();
		try {
			conn = util.getConnection();
			BookLoanDAO bldao = new BookLoanDAO(conn);
			BookLoan bookLoan = new BookLoan();

			bookLoan.setBookId(bookId);
			bookLoan.setBranchId(branchId);
			bookLoan.setCardNo(cardNo);
			bookLoan.setDateOut(Timestamp.valueOf(LocalDateTime.now()));
			bookLoan.setDueDate(Timestamp.valueOf(LocalDateTime.now().plusWeeks(1)));
			bookLoan.setDateIn(null); // The book has been checked out, so the date in is reset to null.
			bldao.add(bookLoan);

			BookCopyDAO bookCopyDao = new BookCopyDAO(conn);
			bc = bookCopyDao.readBookCopiesByBookIdandBranchId(bookId, branchId);

			bc.setNoOfCopies(bc.getNoOfCopies() - 1);
			bookCopyDao.updateNoOfCopiesBC(bc);

			conn.commit();
			System.out.println("Successfully checked out book, added new book_loan and updated book_copy.");
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			System.out.println("Failed to check out book");

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<BookLoan> readLoansFromCardBranchId(Integer branchId, Integer cardNo) throws SQLException {
		Connection conn = null;
		List<BookLoan> bookLoans = new ArrayList<>();
		try {
			conn = util.getConnection();
			BookLoanDAO bldao = new BookLoanDAO(conn);
			bookLoans = bldao.readBookLoansByCardNoAndBranchId(cardNo, branchId);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return bookLoans;
	}

	public Book readBookFromId(Integer bookId) throws SQLException {
		Connection conn = null;
		Book book = new Book();
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			book = bdao.readBookById(bookId);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return book;
	}

	public void returnBook(Integer bookId, Integer branchId, Integer cardNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopyDAO bcdao = new BookCopyDAO(conn);
			BookCopy bookCopy = bcdao.readBookCopiesByBookIdandBranchId(bookId, branchId);
			bookCopy.setNoOfCopies(bookCopy.getNoOfCopies() + 1);
			bcdao.updateNoOfCopiesBC(bookCopy);
			BookLoanDAO bldao = new BookLoanDAO(conn);
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBookId(bookId);
			bookLoan.setBranchId(branchId);
			bookLoan.setCardNo(cardNo);
			bldao.delete(bookLoan);
			conn.commit();
			System.out.println("Successfully returned book");

		} catch (Exception e) {
			System.out.println("Failed to return book");
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}