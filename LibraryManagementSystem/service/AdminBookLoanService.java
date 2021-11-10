package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lms.dao.BookLoanDAO;
import lms.dao.BorrowerDAO;
import lms.dao.LibraryBranchDAO;
import lms.dao.AuthorDAO;
import lms.dao.BookAuthorDAO;
import lms.dao.BookDAO;
import lms.domain.BookAuthor;
import lms.domain.Borrower;
import lms.domain.LibraryBranch;
import lms.domain.Author;
import lms.domain.Book;
import lms.domain.BookLoan;

public class AdminBookLoanService {

	Utility util = new Utility();

	public String overrideBookLoan() throws SQLException {
		Connection conn = null; // Use same connection for all dao, by using the same constructor with
								// super(conn)
		String s;
		BookLoan book_loan = new BookLoan();
		Borrower user = null;
		try {
			conn = util.getConnection();
			BookLoanDAO bookloandao = new BookLoanDAO(conn);
			BookDAO bookdao = new BookDAO(conn);
			AuthorDAO authordao = new AuthorDAO(conn);
			LibraryBranchDAO branchdao = new LibraryBranchDAO(conn);
			BorrowerDAO borrowerdao = new BorrowerDAO(conn);
			BookAuthorDAO bookauthordao = new BookAuthorDAO(conn);

			// Get a list of all borrowers.
			List<Borrower> borrower_list = borrowerdao.readAllBorrowers();

			List<Integer> card_numbers = borrower_list.stream().map(n -> (n.getCardNo())) // filter for card numbers.
																							// [cardno,x,y,z]->[cardno]
					.collect(Collectors.toList()); // collect into a list.

			System.out.println("Enter card number: ");
			int cardNo = 0;
			while (true) {
				cardNo = InputHandler.InputCardNum();
				user = borrowerdao.readBorrowersByCardNo(cardNo);
				if (card_numbers.contains(cardNo)) {
					System.out.println("Accepted, information for user " + user.getName());
					break;
				} else {
					System.out.println("Please enter a valid library card number.");
				}
			}

			System.out.println("List of Book Loans for user: " + user.getName() + ", card number: " + cardNo + ": ");

			List<BookLoan> loansCardNo = bookloandao.readLoansByCardNo(cardNo);
			List<BookAuthor> book_authors = bookauthordao.readAllBookAuthors();

			int loanIndex = 1;
			for (BookLoan bl : loansCardNo) {

				Book book = bookdao.readBookById(bl.getBookId());
				BookAuthor book_author = bookauthordao.readBookAuthorsByBookId(bl.getBookId());
				Author author = authordao.readAuthorById(book_author.getAuthorId());
				LibraryBranch branch = branchdao.readBranchFromId(bl.getBranchId());

				System.out.println(loanIndex + ") [Book: " + book.getTitle() + " by " + author.getAuthorName()
						+ "]\n\t[Branch: " + branch.getBranchName() + ", " + branch.getBranchAddress()
						+ "]\n\t[Checkout Date: " + bl.getDateOut() + "]\n\t[Due Date: " + bl.getDueDate() + "]"
						+ "]\n\t[Date in: " + bl.getDateIn() + "]");
				loanIndex++;
			}

			System.out.println(loanIndex + ") Cancel Transaction");
			int loanChoice = InputHandler.InputInt(1, loanIndex);
			if (loanChoice == loanIndex) {
				return "Transaction cancelled";
			}

			LocalDateTime Localdate;
			LocalDateTime Localdate_in = LocalDateTime.now();
			// If the book has not been returned yet then date_in should be null or empty.
			System.out.println("Enter new Due Date ('quit' to cancel, 'auto' for 7 days from now): ");
			String dueDate = InputHandler.InputString();
			if (dueDate.equals("quit")) {
				return "Transaction cancelled";
			} else if (dueDate.equals("auto")) {
				Localdate = LocalDateTime.now().plusWeeks(1);
			} else {
				Localdate = LocalDateTime.parse(dueDate);
			}

			book_loan = loansCardNo.get(--loanChoice);
			book_loan.setDueDate(Timestamp.valueOf(Localdate));
			book_loan.setDateIn(Timestamp.valueOf(Localdate_in));
			bookloandao.update(book_loan);
			conn.commit();
			s = "Successfully changed date to " + book_loan.getDueDate();
		} catch (Exception e) {
			conn.rollback();
			s = "Due date was not changed!";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}
}