package lms.menu;

import java.sql.SQLException;

import lms.service.InputHandler;

public class MainMenu {

	public static void menuMain() throws SQLException {

		System.out.println("Welcome to the SS Library Management System. Which categroy of a user are you");
		System.out.println();
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
		System.out.println();

		int input = InputHandler.InputInt(1, 3);

		if (input == 1) {
			LibrarianMenu.menuLib1();
		} else if (input == 2) {
			AdminMenu.menuAdmin1();
		} else { 
			BorrowerMenu.menuBorr0();
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		menuMain();

	}

}