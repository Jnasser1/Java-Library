package lms.menu;

import java.sql.SQLException;
import java.util.List;

import lms.domain.Genre;
import lms.service.AdminAuthorService;
import lms.service.AdminBookAuthorService;
import lms.service.AdminBookService;
import lms.service.AdminBookLoanService;
import lms.service.AdminBorrowerService;
import lms.service.AdminLibraryBranchService;
import lms.service.AdminPublisherService;
import lms.service.InputHandler;
import lms.service.AdminGenreService;

public class AdminMenu {

	public static void menuAdmin1() throws SQLException {

		System.out.println("1) Add/Update/Delete/Read Books and Authors");
		System.out.println("2) Add/Update/Delete/Read Genres");
		System.out.println("3) Add/Update/Delete/Read Publishers");
		System.out.println("4) Add/Update/Delete/Read Library Branches");
		System.out.println("5) Add/Update/Delete/Read Borrowers");
		System.out.println("6) Over-ride Due Date for a Book Loan");

		System.out.println("");

		int ans = InputHandler.InputInt(1, 6);

		// Combine CRUD operations on books and author table using bookauthor
		if (ans == 1) {
			crudBookAndAuthors();
		} else if (ans == 2) {
			crudGenres();
		} else if (ans == 3) {
			crudPublishers();
		} else if (ans == 4) {
			crudBranches();
		} else if (ans == 5) {
			crudBorrowers();
		} else if (ans == 6) {
			bookLoan();
		}
		MainMenu.menuMain();
	}

	private static void crudBookAndAuthors() throws SQLException {
		AdminBookAuthorService bookauthorService = new AdminBookAuthorService();
		System.out.println("1) Add Book and Author");
		System.out.println("2) Update Book and Author");
		System.out.println("3) Delete Book and Author");
		System.out.println("4) Display all Books and Authors Available");
		System.out.println("5) Main menu");
		System.out.println("");

		int ans = InputHandler.InputInt(1, 5);

		if (ans == 1) {
			System.out.println(bookauthorService.addBookAndAuthor());
			crudBookAndAuthors();
		} else if (ans == 2) {
			System.out.println(bookauthorService.updateBookAndAuthor());
			crudBookAndAuthors();
		} else if (ans == 3) {
			System.out.println(bookauthorService.deleteBookAndAuthor());
			crudBookAndAuthors();
		} else if (ans == 4) {
			System.out.println(bookauthorService.DisplayAllBooksAndAuthors());
			crudBookAndAuthors();
		} else if (ans == 5) {
			MainMenu.menuMain();
		}
	}

	private static void crudGenres() throws SQLException {
		AdminGenreService genreService = new AdminGenreService();
		System.out.println("1) Add Genre");
		System.out.println("2) Update Genre");
		System.out.println("3) Delete Genre");
		System.out.println("4) Display all Genres");
		System.out.println("5) Main menu");
		System.out.println();

		int ans = InputHandler.InputInt(1, 5);

		if (ans == 1) {
			System.out.println(genreService.addGenre());
			crudGenres();
		} else if (ans == 2) {
			System.out.println(genreService.updateGenre());
			crudGenres();
		} else if (ans == 3) {
			System.out.println(genreService.deleteGenre());
			crudGenres();
		} else if (ans == 4) {
			List<Genre> genre_list = genreService.DisplayAllGenres();
			crudGenres();
		} else if (ans == 5) {
			MainMenu.menuMain();
		}
	}

	private static void crudPublishers() throws SQLException {
		AdminPublisherService publisherService = new AdminPublisherService();
		System.out.println("1) Add Publisher");
		System.out.println("2) Update Publisher");
		System.out.println("3) Delete Publisher");
		System.out.println("4) Display all Publishers");
		System.out.println("5) Main menu");
		System.out.println("");

		int ans = InputHandler.InputInt(1, 5);

		if (ans == 1) {
			System.out.println(publisherService.addPublisher());
			crudPublishers();
		} else if (ans == 2) {
			System.out.println(publisherService.updatePublisher());
			crudPublishers();
		} else if (ans == 3) {
			System.out.println(publisherService.deletePublisher());
			crudPublishers();
		} else if (ans == 4) {
			System.out.println(publisherService.DisplayAllPublishers());
			crudPublishers();
		} else if (ans == 5) {
			MainMenu.menuMain();
		}
	}

	private static void crudBranches() throws SQLException {
		AdminLibraryBranchService branchService = new AdminLibraryBranchService();
		System.out.println("1) Add Branch");
		System.out.println("2) Update Branch");
		System.out.println("3) Delete Branch");
		System.out.println("4) Display all Library Branches");
		System.out.println("5) Main menu");
		System.out.println("");

		int ans = InputHandler.InputInt(1, 5);

		if (ans == 1) {
			System.out.println(branchService.addBranch());
			crudBranches();
		} else if (ans == 2) {
			System.out.println(branchService.updateBranch());
			crudBranches();
		} else if (ans == 3) {
			System.out.println(branchService.deleteBranch());
			crudBranches();
		} else if (ans == 4) {
			System.out.println(branchService.readAllBranches());
			crudBranches();
		} else if (ans == 5) {
			MainMenu.menuMain();
		}
	}

	private static void crudBorrowers() throws SQLException {
		AdminBorrowerService borrowerService = new AdminBorrowerService();
		System.out.println("1) Add Borrower");
		System.out.println("2) Update Borrower");
		System.out.println("3) Delete Borrower");
		System.out.println("4) Display all Borrowers");
		System.out.println("5) Main menu");
		System.out.println("");

		int ans = InputHandler.InputInt(1, 5);

		if (ans == 1) {
			System.out.println(borrowerService.addBorrower());
		} else if (ans == 2) {
			System.out.println(borrowerService.updateBorrower());
		} else if (ans == 3) {
			System.out.println(borrowerService.deleteBorrower());
		} else if (ans == 4) {
			System.out.println(borrowerService.readAllBorrowers());
			System.out.println("");
			crudBorrowers();
		} else if (ans == 5) {
			MainMenu.menuMain();
		}
	}

	private static void bookLoan() throws SQLException {
		AdminBookLoanService bookLoanService = new AdminBookLoanService();

		System.out.println(bookLoanService.overrideBookLoan());
	}

	private static void crudBooks() throws SQLException {
		AdminBookService bookService = new AdminBookService();
		System.out.println("1) Add Book");
		System.out.println("2) Update Book");
		System.out.println("3) Delete Book");
		System.out.println("4) Display all Books Available");
		System.out.println("5) Main menu");
		System.out.println("");

		int ans = InputHandler.InputInt(1, 5);

		if (ans == 1) {
			System.out.println(bookService.addBook());
			crudBooks();
		} else if (ans == 2) {
			System.out.println(bookService.updateBook());
			crudBooks();
		} else if (ans == 3) {
			System.out.println(bookService.deleteBook());
			crudBooks();
		} else if (ans == 4) {
			System.out.println(bookService.DisplayAllBooks());
			crudBooks();
		} else if (ans == 5) {
			MainMenu.menuMain();
		}
	}

	private static void crudAuthors() throws SQLException {
		AdminAuthorService authorService = new AdminAuthorService();
		System.out.println("1) Add Author");
		System.out.println("2) Update Author");
		System.out.println("3) Delete Author");
		System.out.println("4) Display all Authors Available");
		System.out.println("5) Main menu");
		System.out.println("");

		int ans = InputHandler.InputInt(1, 5);

		if (ans == 1) {
			System.out.println(authorService.addAuthor());
			crudAuthors();
		} else if (ans == 2) {
			System.out.println(authorService.updateAuthor());
			crudAuthors();
		} else if (ans == 3) {
			System.out.println(authorService.deleteAuthor());
			crudAuthors();
		} else if (ans == 4) {
			System.out.println(authorService.DisplayAllAuthors());
			crudAuthors();
		} else if (ans == 5) {
			MainMenu.menuMain();
		}

	}

}