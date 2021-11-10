package lms.service;
// admins can preform crud operations and see all books.

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import lms.dao.PublisherDAO;
import lms.domain.Publisher;

public class AdminPublisherService {

	Utility util = new Utility();

	public String DisplayAllPublishers() throws SQLException {
		Connection conn = null;
		String str = "";
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			System.out.println("List of publishers: ");
			List<Publisher> pub_list = pdao.readAllPublishers();
			int pubIndex = 1;
			for (Publisher p : pub_list) {
				System.out.println(pubIndex + ") " + p.getPublisherName() + ", " + p.getPublisherPhone());
				pubIndex++;
			}
			conn.commit();
			str = "Successfully displayed publishers.";
		} catch (Exception e) {
			conn.rollback();
			str = "Problem has occured could not display publishers";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return str;
	}

	public String addPublisher() throws SQLException {
		Connection conn = null;
		String str;
		Publisher pub = new Publisher();
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);

			System.out.print("Enter Name ('quit' to cancel): ");
			String publisherName = InputHandler.InputString();
			if (publisherName.equals("quit")) {
				return "Transaction cancelled";
			}

			System.out.print("Enter Address ('quit' to cancel): ");
			String publisherAddress = InputHandler.InputString();
			if (publisherAddress.equals("quit")) {
				return "Transaction cancelled";
			}

			System.out.print("Enter Phone xxx-xxx-xxxx ('quit' to cancel): ");
			String publisherPhone = InputHandler.InputString();
			if (publisherName.equals("quit")) {
				return "Transaction cancelled";
			}

			pub.setPublisherName(publisherName);
			pub.setPublisherAddress(publisherAddress);
			pub.setPublisherPhone(publisherPhone);
			pdao.addToEnd(pub); // increments pubid by 1 and adds to the db.
			conn.commit();
			str = "Successfully added " + pub.getPublisherName();
		} catch (Exception e) {
			conn.rollback();
			str = "Publisher could not be added";
			e.printStackTrace();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return str;
	}

	public Integer addPublishergetId() throws SQLException {
		Connection conn = null;
		String str;
		Publisher pub = new Publisher();
		int pubid = 0;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);

			System.out.print("Enter Name ('quit' to cancel): ");
			String publisherName = InputHandler.InputString();
			if (publisherName.equals("quit")) {
				System.out.println("Transaction cancelled");
			}

			System.out.print("Enter Address ('quit' to cancel): ");
			String publisherAddress = InputHandler.InputString();
			if (publisherAddress.equals("quit")) {
				System.out.println("Transaction cancelled");
			}

			System.out.print("Enter Phone xxx-xxx-xxxx ('quit' to cancel): ");
			String publisherPhone = InputHandler.InputString();
			if (publisherName.equals("quit")) {
				System.out.println("Transaction cancelled");
			}

			pub.setPublisherName(publisherName);
			pub.setPublisherAddress(publisherAddress);
			pub.setPublisherPhone(publisherPhone);
			pdao.addToEnd(pub); // increments pubid by 1 and adds to the db.
			pubid = pub.getPublisherId();
			conn.commit();
			str = "Successfully added " + pub.getPublisherName();
		} catch (Exception e) {
			conn.rollback();
			str = "Publisher could not be added";
			e.printStackTrace();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return pubid;
	}

	public String updatePublisher() throws SQLException {
		Connection conn = null;
		String str;
		Publisher pub = new Publisher();
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);

			System.out.println("Select Publisher you wish to update: ");
			List<Publisher> pubs = pdao.readAllPublishers();
			int pubIndex = 1;
			for (Publisher p : pubs) {
				System.out.println(pubIndex + ") " + p.getPublisherName() + ", " + p.getPublisherAddress());
				pubIndex++;
			}
			System.out.println(pubIndex + ") Cancel Transaction");
			int pubChoice = InputHandler.InputInt(1, pubIndex);
			if (pubChoice == pubIndex) {
				return "Transaction Cancelled";
			}

			System.out.print("Enter Name ('quit' to cancel): ");
			String publisherName = InputHandler.InputString();
			if (publisherName.equals("quit")) {
				return "Transaction cancelled";
			}

			System.out.print("Enter Address ('quit' to cancel): ");
			String publisherAddress = InputHandler.InputString();
			if (publisherAddress.equals("quit")) {
				return "Transaction cancelled";
			}

			System.out.print("Enter Phone xxx-xxx-xxxx ('quit' to cancel): ");
			String publisherPhone = InputHandler.InputString();
			if (publisherName.equals("quit")) {
				return "Transaction cancelled";
			}

			pub.setPublisherId(pubs.get(--pubChoice).getPublisherId());
			pub.setPublisherName(publisherName);
			pub.setPublisherAddress(publisherAddress);
			pub.setPublisherPhone(publisherPhone);
			pdao.update(pub);
			conn.commit();
			str = "Successfully updated " + pub.getPublisherName();
		} catch (Exception e) {
			conn.rollback();
			str = "Problem has occured, publisher not updated";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return str;
	}

	public String deletePublisher() throws SQLException {
		Connection conn = null;
		String str;
		Publisher pub = new Publisher();
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);

			System.out.println("Select Publisher you wish to update: ");
			List<Publisher> pubs = pdao.readAllPublishers();
			int pubIndex = 1;
			for (Publisher p : pubs) {
				System.out.println(pubIndex + ") " + p.getPublisherName() + ", " + p.getPublisherAddress());
				pubIndex++;
			}
			System.out.println(pubIndex + ") Cancel Transaction");
			int pubChoice = InputHandler.InputInt(1, pubIndex);
			if (pubChoice == pubIndex) {
				return "Transaction Cancelled";
			}

			pub.setPublisherId(pubs.get(--pubChoice).getPublisherId());
			pdao.delete(pub);

			conn.commit();
			str = "Successfully deleted publisher";
		} catch (Exception e) {
			conn.rollback();
			str = "Problem has occured, publisher not deleted";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return str;
	}

}