package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.BookLoan;

public class BookLoanDAO extends BaseDAO<BookLoan> {

	public BookLoanDAO(Connection conn) {
		super(conn);
	}

	public void add(BookLoan bookLoan) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book_loans VALUES (?, ?, ?, ?, ?, ?)",
				new Object[] { bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo(),
						bookLoan.getDateOut(), bookLoan.getDueDate(), bookLoan.getDateIn() });
	}

	public void update(BookLoan bookLoan) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_book_loans SET dateOut = ?, dueDate = ?, dateIn = ? WHERE bookId = ? AND branchId = ? AND cardNo = ?",
				new Object[] { bookLoan.getDateOut(), bookLoan.getDueDate(), bookLoan.getDateIn(), bookLoan.getBookId(),
						bookLoan.getBranchId(), bookLoan.getCardNo() });
	}

	// Adminstator over-riding DueDate.
	public void updateOverRideDueDate(BookLoan bookLoan) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_book_loans SET dueDate = ? WHERE dateOut = ? AND bookId = ? AND branchId = ? AND cardNo = ?, AND dateIn= ?",
				new Object[] { bookLoan.getDueDate(), bookLoan.getDateOut(), bookLoan.getBookId(),
						bookLoan.getBranchId(), bookLoan.getCardNo(), bookLoan.getDateIn() });
	}

	// Change the date a book was checked out and the date its due. dateOut=today,
	// dueDate=one week from dateOut in functionality.
	// Change bookLoan.dateOut to today, set dueDate to one week from today then
	// call this function to update table..
	public void updateOneWeek(BookLoan bookLoan) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_book_loans SET dateOut = ?, dueDate = ? WHERE bookId = ? AND branchId = ? AND cardNo = ?",
				new Object[] { bookLoan.getDateOut(), bookLoan.getDueDate(), bookLoan.getBookId(),
						bookLoan.getBranchId(), bookLoan.getCardNo() });
	}

	// Set bookLoan.dateIn to today, call this function.
	public void updateBookIsReturned(BookLoan bookLoan) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_book_loans SET dateIn = ? WHERE dateOut = ? AND dueDate = ? AND bookId = ? AND branchId = ? AND cardNo = ?",
				new Object[] { bookLoan.getDateIn(), bookLoan.getDateOut(), bookLoan.getDueDate(), bookLoan.getBookId(),
						bookLoan.getBranchId(), bookLoan.getCardNo() });
	}

	// (bookId, branchId, cardNo) is composite key.
	public void delete(BookLoan bookLoan) throws SQLException, ClassNotFoundException {
		save("delete from tbl_book_loans where bookId = ? AND branchId = ? AND cardNo = ?",
				new Object[] { bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo() });
	}

	// Using abstract parent class method read()
	public List<BookLoan> readAllBookLoans() throws SQLException, ClassNotFoundException {
		return this.read("select * from tbl_book_loans ORDER BY dueDate asc", null);
	}

	public List<BookLoan> readBookLoansByCardNoAndBranchId(Integer cardNo, Integer branchId)
			throws SQLException, ClassNotFoundException {
		return this.read("select * from tbl_book_loans, tbl_book " + "where cardNo = ?" + " AND branchId = ? "
				+ "AND tbl_book.bookId=tbl_book_loans.bookId", new Object[] { cardNo, branchId });
	}

	// Used line 61 fo AdminBookLoanService.java, to override due date.
	public List<BookLoan> readLoansByCardNo(Integer cardNo) throws SQLException, ClassNotFoundException {
		return this.read("select * from tbl_book_loans where cardNo = ? ORDER BY dueDate asc", new Object[] { cardNo });
	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookLoan> BookLoanList = new ArrayList<>();
		while (rs.next()) {
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBookId(rs.getInt("bookId"));
			bookLoan.setBranchId(rs.getInt("branchId"));
			bookLoan.setCardNo(rs.getInt("cardNo"));
			bookLoan.setDateOut(rs.getTimestamp("dateOut"));
			bookLoan.setDueDate(rs.getTimestamp("dueDate"));
			bookLoan.setDateIn(rs.getTimestamp("dateIn"));
			BookLoanList.add(bookLoan);
		}

		return BookLoanList;
	}

	@Override
	public BookLoan extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

		if (rs.next()) {
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBookId(rs.getInt("bookId"));
			bookLoan.setBranchId(rs.getInt("branchId"));
			bookLoan.setCardNo(rs.getInt("cardNo"));
			bookLoan.setDateOut(rs.getTimestamp("dateOut"));
			bookLoan.setDueDate(rs.getTimestamp("dueDate"));
			bookLoan.setDateIn(rs.getTimestamp("dateIn"));
			return bookLoan;
		}

		return null;
	}
}