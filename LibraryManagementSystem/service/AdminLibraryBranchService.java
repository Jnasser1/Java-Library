package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import lms.domain.LibraryBranch;
import lms.dao.LibraryBranchDAO;

public class AdminLibraryBranchService {

	LibraryBranchDAO lbdao;

	Utility util = new Utility();

	public String readAllBranches() throws SQLException {
		Connection conn = null;
		String s;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);

			System.out.println("List of branches: ");
			List<LibraryBranch> branches = lbdao.readEverythingBranches();
			int branchIndex = 1;
			for (LibraryBranch b : branches) {
				System.out.println(branchIndex + ") " + b.getBranchName() + ", " + b.getBranchAddress());
				branchIndex++;
			}

			conn.commit();
			s = "Successfully displayed branches";
		} catch (Exception e) {
			conn.rollback();
			s = "Problem has occured";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

	public String addBranch() throws SQLException {
		Connection conn = null;
		String s;
		LibraryBranch branch = new LibraryBranch();
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);

			System.out.print("Enter Name ('quit' to cancel): ");
			String branchName = InputHandler.InputString();
			if (branchName.equals("quit")) {
				return "Transaction cancelled";
			}

			System.out.print("Enter Address ('quit' to cancel): ");
			String branchAddress = InputHandler.InputString();
			if (branchAddress.equals("quit")) {
				return "Transaction cancelled";
			}

			branch.setBranchName(branchName);
			branch.setBranchAddress(branchAddress);
			lbdao.addToEnd(branch);
			conn.commit();
			s = "Successfully added " + branch.getBranchName();
		} catch (Exception e) {
			conn.rollback();
			s = "Branch not added";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

	public String updateBranch() throws SQLException {
		Connection conn = null;
		String s;
		LibraryBranch branch = new LibraryBranch();
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);

			System.out.println("Select Branch you wish to update: ");
			List<LibraryBranch> branches = lbdao.readEverythingBranches();
			int branchIndex = 1;
			for (LibraryBranch b : branches) {
				System.out.println(branchIndex + ") " + b.getBranchName() + ", " + b.getBranchAddress());
				branchIndex++;
			}
			System.out.println(branchIndex + ") Cancel Transaction");
			int branchChoice = InputHandler.InputInt(1, branchIndex);
			if (branchChoice == branchIndex) {
				return "Transaction Cancelled";
			}

			System.out.print("Enter Name ('quit' to cancel): ");
			String branchName = InputHandler.InputString();
			if (branchName.equals("quit")) {
				return "Transaction cancelled";
			}

			System.out.print("Enter Address ('quit' to cancel): ");
			String branchAddress = InputHandler.InputString();
			if (branchAddress.equals("quit")) {
				return "Transaction cancelled";
			}

			branch.setBranchId(branches.get(--branchChoice).getBranchId());
			branch.setBranchName(branchName);
			branch.setBranchAddress(branchAddress);
			lbdao.update(branch);
			conn.commit();
			s = "Successfully updated " + branch.getBranchName();
		} catch (Exception e) {
			conn.rollback();
			s = "Problem has occured, Branch not updated";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

	public String deleteBranch() throws SQLException {
		Connection conn = null;
		String s;
		LibraryBranch branch = new LibraryBranch();
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);

			System.out.println("Select Branch you wish to update: ");
			List<LibraryBranch> branches = lbdao.readEverythingBranches();
			int branchIndex = 1;
			for (LibraryBranch b : branches) {
				System.out.println(branchIndex + ") " + b.getBranchName() + ", " + b.getBranchAddress());
				branchIndex++;
			}
			System.out.println(branchIndex + ") Cancel Transaction");
			int branchChoice = InputHandler.InputInt(1, branchIndex);
			if (branchChoice == branchIndex) {
				return "Transaction Cancelled";
			}

			branch.setBranchId(branches.get(--branchChoice).getBranchId());
			lbdao.delete(branch);

			conn.commit();
			s = "Successfully deleted Branch";
		} catch (Exception e) {
			conn.rollback();
			s = "Problem has occured, Branch not deleted";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return s;
	}

}