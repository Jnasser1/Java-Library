package lms.menu;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import lms.domain.Author;
import lms.domain.Book;
import lms.domain.BookAuthor;
import lms.domain.BookLoan;
import lms.domain.Borrower;
import lms.domain.LibraryBranch;

import lms.service.InputHandler;
import lms.service.BorrowerService;

public class BorrowerMenu {

	private static BorrowerService service = new BorrowerService();

	public static void menuBorr0() throws SQLException {

		List<Borrower> borrower_List = service.readAllBorrowers();

		List<Integer> cardNos = borrower_List.stream().map(n -> (n.getCardNo())).collect(Collectors.toList());
		List<String> Names = borrower_List.stream().map(n -> (n.getName())).collect(Collectors.toList());

		System.out.println("Enter your card number: ");
		int id = 0;
		while (true) {
			id = InputHandler.InputCardNum();
			if (cardNos.contains(id)) {
				int user_index = cardNos.indexOf(id);
				String username = Names.get(user_index);

				System.out.println("Welcome " + username);
				break;
			} else {
				System.out.println("Card number not in system, try again");
				continue;
			}
		}
		menuBorr1(id);
	}

	private static void menuBorr1(int cardNo) throws SQLException {

		System.out.println("1) Check out a book");
		System.out.println("2) Return a book");
		System.out.println("3) Quit to previous");

		int ans = InputHandler.InputInt(1, 3);

		if (ans == 1) {
			checkOutBook(cardNo);
		} else if (ans == 2) {
			returnBook(cardNo);
		} else {
			MainMenu.menuMain();
		}
	}

	private static void checkOutBook(int cardNo) throws SQLException {
		List<LibraryBranch> branches = service.readAllBranches();

		System.out.println("Pick the Branch you want to check out from: ");
		int branchIndex = 1;
		for (LibraryBranch b : branches) {
			System.out.println(branchIndex + ") " + b.getBranchName() + ", " + b.getBranchAddress());
			branchIndex++;
		}

		System.out.println(branchIndex + ") Quit to previous");

		// User chooses the branch to check out from.
		int rowinput_branch = InputHandler.InputInt(1, branchIndex);

		if (rowinput_branch == branchIndex) {
			System.out.println("Transaction Cancelled");
		} else {

			// User chooses branch with branch id:
			int chosen_branch_id = branches.get(rowinput_branch - 1).getBranchId();

			/*
			 * Calls the service method which connects with database and reads available
			 * books from a custom query.
			 */

			System.out.println("Pick the Book you want to check out");
			System.out.println("Displaying books with physical copies at the chosen branch..");
			System.out.println("");

			List<Book> books = service.readAllBooksWithPositiveCopiesNew(chosen_branch_id);
			int bookIndex = 1;

			for (Book b : books) {

				System.out.println(bookIndex + ") " + b.getTitle());
				bookIndex++;
			}

			System.out.println(bookIndex + ") Quit to cancel operations");

			// User chooses book to check out.
			int rowinput_book = InputHandler.InputInt(1, bookIndex);

			// We present to user choices that begin at 1 but sizes in structures begin at
			// position 0; shifting needed.

			int chosen_book_id = 0;

			if (rowinput_book == bookIndex) {
				System.out.println("Transation Cancelled");
				menuBorr1(cardNo);

			} else {
				chosen_book_id = books.get(rowinput_book - 1).getBookId();
				service.CheckOutBook(chosen_book_id, chosen_branch_id, cardNo);
			}
		}
		menuBorr1(cardNo);
	}

	private static void returnBook(int cardNo) throws SQLException {

		List<LibraryBranch> branches = service.readAllBranches();

		System.out.println("Pick the Branch you want to return to: ");
		int branchIndex = 1;
		for (LibraryBranch b : branches) {
			System.out.println(branchIndex + ") " + b.getBranchName() + ", " + b.getBranchAddress());
			branchIndex++;
		}
		System.out.println(branchIndex + ") Quit to previous");
		int rowinput_branch = InputHandler.InputInt(1, branchIndex);

		if (rowinput_branch == branchIndex) {
			System.out.println("Transaction Cancelled");
		} else {
			System.out.println("Pick the Book you want to return");

			/*
			 * Returns a list of books to return, if the borrower chooses a branch he has a
			 * book checked out from
			 */

			List<BookLoan> bookLoans = service
					.readLoansFromCardBranchId((branches.get(rowinput_branch - 1)).getBranchId(), cardNo);
			int bookIndex = 1;

			for (BookLoan bl : bookLoans) {

				Book book = service.readBookFromId(bl.getBookId());

				BookAuthor book_author = service.readBookAuthorFromBookId((bl.getBookId()));

				Author author = service.readAuthorFromId(book_author.getAuthorId());

				System.out.println(bookIndex + ") " + book.getTitle() + " by " + author.getAuthorName());
				bookIndex++;
			}
			System.out.println(bookIndex + ") Quit to cancel operations");
			int rowinput_book = InputHandler.InputInt(1, bookIndex);

			if (rowinput_book == bookIndex) {
				System.out.println("Transation Cancelled");
				menuBorr1(cardNo);
			} else {
				service.returnBook(bookLoans.get(rowinput_book - 1).getBookId(),
						branches.get(rowinput_branch - 1).getBranchId(), cardNo);
			}
		}
		menuBorr1(cardNo);
	}
}