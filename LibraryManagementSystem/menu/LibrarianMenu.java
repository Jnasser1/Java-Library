package lms.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Book;
import lms.domain.BookAuthor;
import lms.domain.BookCopy;
import lms.domain.Author;
import lms.domain.LibraryBranch;
import lms.service.InputHandler;
import lms.service.LibrarianService;

public class LibrarianMenu {

	private static LibrarianService service = new LibrarianService();

	public static void menuLib1() throws SQLException {
		System.out.println("1) Enter Branch you manage"); // Will then display list of branches to choose from.
		System.out.println("2) Quit to previous");
		System.out.println();

		int ans = InputHandler.InputInt(1, 2);

		if (ans == 1) {
			menuLib2();
		} else {
			MainMenu.menuMain();
		}
	}

	private static void menuLib2() throws SQLException {
		List<LibraryBranch> branches = new ArrayList<>();

		branches = service.readAllBranches();

		int index = 1;
		for (LibraryBranch b : branches) {
			System.out.println(index + ") " + b.getBranchName() + ", " + b.getBranchAddress());
			index++;
		}

		System.out.println(index + ") Quit to previous");

		int rowinput_branch = InputHandler.InputInt(1, index);

		if (rowinput_branch == index) {
			menuLib1();
		} else { // If not quitting, pull the branch from chosen spot and use it for menu3.
			menuLib3(branches.get(rowinput_branch - 1));
		}
	}

	private static void menuLib3(LibraryBranch branch) throws SQLException {
		System.out.println("1) Update the details of the library");
		System.out.println("2) Add copies of Book to the Branch");
		System.out.println("3) Quit to previous");

		int ans = InputHandler.InputInt(1, 3);

		if (ans == 1) {
			updateBranch(branch);
		} else if (ans == 2) {
			updateBooks(branch);
		} else {
			menuLib2();
		}
	}

	private static void updateBranch(LibraryBranch branch) throws SQLException {

		System.out.println(
				"You have chosen to update the Branch with branch Id: " + branch.getBranchId() + " and Branch Name: "
						+ branch.getBranchName() + ". \nEnter ‘quit’ at any prompt to cancel operation.");

		System.out.println();
		System.out.println("Please enter new branch name or enter N/A for no change:");
		String input_name = InputHandler.InputString();

		if (input_name.compareToIgnoreCase("quit") == 0) {
			System.out.println("Operation Cancelled");
			menuLib3(branch);
			return;
		}

		System.out.println("Please enter new branch address or enter N/A for no change:");
		String input_branchaddress = InputHandler.InputString();

		if (input_branchaddress.compareToIgnoreCase("quit") == 0) {
			System.out.println("Operation Cancelled");
			menuLib3(branch);
			return;
		}

		// If the user has not enterd "N/A" then update the fields and commit
		if (input_name.compareToIgnoreCase("N/A") != 0) {
			service.updateBranchName(branch.getBranchId(), input_name);
		}
		if (input_branchaddress.compareToIgnoreCase("N/A") != 0) {
			LibrarianService.updateBranchAddress(branch.getBranchId(), input_branchaddress);
		}

		System.out.println("Successfully updated branch");
		menuLib3(branch);
		return;
	}

	private static void updateBooks(LibraryBranch branch) throws SQLException {
		List<Book> books = new ArrayList<>();
		BookCopy bookCopy = new BookCopy();

		books = service.readAllBooks();
		System.out.println("Pick the book you want to add copies, of to your branch:");
		int index = 1;

		BookAuthor book_author = new BookAuthor();
		Author author = new Author();

		for (Book b : books) {
			book_author = service.readBookAuthorFromBookId(b.getBookId());
			author = service.readAuthorFromId(book_author.getAuthorId());
			System.out.println(index + ") " + b.getTitle() + " by " + author.getAuthorName());
			index++;
		}
		System.out.println(index + ") Quit to cancel operation\n");

		int rowinput = InputHandler.InputInt(1, index);

		// If user chooses to quit to previous.
		if (rowinput == index) {
			menuLib3(branch);
			return;
		} else {

			// System.out.println( "The chosen bookId is: " + (books.get(rowinput -
			// 1)).getBookId() + " + "title: " +
			// (books.get(rowinput - 1)).getTitle() + "and branchId:"
			// +branch.getBranchId());

			Integer bookIdc = (books.get(rowinput - 1)).getBookId();
			Integer branchIdc = branch.getBranchId();

			bookCopy = service.getBookCopy((books.get(rowinput - 1)).getBookId(), branch.getBranchId());
			int copies = 0;

			// If there is already a copy of the chosen book at the chosen branch:
			if (bookCopy != null) {
				copies = bookCopy.getNoOfCopies();
				System.out.println("Existing Number of copies: " + copies);
				System.out.println("Enter new number of copies\n");
				Integer updated_noOfCopies = InputHandler.InputInt(1, 10000);

				service.updateNoOfCopies(bookCopy, updated_noOfCopies);
				menuLib3(branch);
			}

			// If there is not already a copy of the chosen book at the chosen branch:
			else if (bookCopy == null) {

				copies = 0;

				System.out.println("Existing Number of copies: " + copies);

				System.out.println("Enter new number of copies\n");
				Integer updated_noOfCopies = InputHandler.InputInt(1, 10000);

				bookCopy = new BookCopy(bookIdc, branchIdc, updated_noOfCopies);
				service.AddBookCopy(bookCopy);

				menuLib3(branch);
			}

		}
		return;
	}
}