package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import lms.domain.Author;
import lms.dao.AuthorDAO;

public class AdminAuthorService {

	Utility util = new Utility();
	private AuthorDAO adao;

	public String DisplayAllAuthors() throws SQLException {
		Connection conn = null;
		String ReturnStr;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);

			System.out.println("All authors: ");
			System.out.println("Select Author you wish to update: ");
			List<Author> authors = adao.readAllAuthors();
			int authorIndex = 1;
			for (Author a : authors) {
				System.out.println(authorIndex + ") " + a.getAuthorName());
				authorIndex++;
			}

			conn.commit();
			ReturnStr = "Successfully displayed authors";
		} catch (Exception e) {
			conn.rollback();
			ReturnStr = "Problem has occured";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return ReturnStr;
	}

	public String addAuthor() throws SQLException {
		Connection conn = null;
		String ReturnStr;
		Author author = new Author();
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);

			System.out.print("Enter Author name or 'quit' to cancel ");
			String authorName = InputHandler.InputString();
			if (authorName.equals("quit")) {
				return "Transaction cancelled";
			}

			author.setAuthorName(authorName);
			// Adds an author entry with new authorId = max(authorId) + 1 an specified
			// author name
			adao.addToEnd(author);
			conn.commit();
			ReturnStr = "Successfully added " + author.getAuthorName() + " as an" + " author with authorId = "
					+ author.getAuthorId();
		} catch (Exception e) {
			conn.rollback();
			ReturnStr = "Author not added";
			e.printStackTrace();
			;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return ReturnStr;
	}

	public String updateAuthor() throws SQLException {
		Connection conn = null;
		String ReturnStr;
		Author author = new Author();
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);

			System.out.println("Select Author you wish to update: ");
			List<Author> authors = adao.readAllAuthors();
			int authorIndex = 1;
			for (Author a : authors) {
				System.out.println(authorIndex + ") " + a.getAuthorName());
				authorIndex++;
			}
			System.out.println(authorIndex + ") Cancel Transaction");
			int authorChoice = InputHandler.InputInt(1, authorIndex);
			if (authorChoice == authorIndex) {
				return "Transaction Cancelled";
			}

			System.out.print("Enter new name ('quit' to cancel): ");
			String authorName = InputHandler.InputString();
			if (authorName.equals("quit")) {
				return "Transaction cancelled";
			}

			author.setAuthorId(authors.get(--authorChoice).getAuthorId());
			author.setAuthorName(authorName);
			adao.update(author);
			conn.commit();
			ReturnStr = "Successfully updated author " + author.getAuthorName();
		} catch (Exception e) {
			conn.rollback();
			ReturnStr = "Could not update author table.";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return ReturnStr;
	}

	public String deleteAuthor() throws SQLException {
		Connection conn = null;
		String ReturnStr;
		Author author = new Author();
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);

			System.out.println("Select Author you wish to delete: ");
			List<Author> authors = adao.readAllAuthors();
			int authorIndex = 1;
			for (Author a : authors) {
				System.out.println(authorIndex + ") " + a.getAuthorName());
				authorIndex++;
			}
			System.out.println(authorIndex + ") Cancel Transaction");
			int authorChoice = InputHandler.InputInt(1, authorIndex);
			if (authorChoice == authorIndex) {
				return "Transaction Cancelled";
			}

			author.setAuthorId(authors.get(--authorChoice).getAuthorId());
			adao.delete(author);

			conn.commit();
			ReturnStr = "Successfully deleted author";
		} catch (Exception e) {
			conn.rollback();
			ReturnStr = "An exception has occured, could not delete author.";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return ReturnStr;
	}

}