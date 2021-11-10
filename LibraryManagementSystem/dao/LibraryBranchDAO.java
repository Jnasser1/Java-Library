package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {

	public LibraryBranchDAO(Connection conn) {
		super(conn);
	}

	public void add(LibraryBranch branch) throws SQLException, ClassNotFoundException {
		this.save("INSERT INTO tbl_library_branch VALUES (?, ?, ?)",
				new Object[] { branch.getBranchId(), branch.getBranchName(), branch.getBranchAddress() });
	}

	// Add another library branch to table with a new max branchid
	public void addToEnd(LibraryBranch branch) throws SQLException, ClassNotFoundException {
		save("SET @max_id = (SELECT MAX(branchId) FROM `tbl_library_branch`)", null);
		save("insert into tbl_library_branch values (@max_id + 1, ?, ?)",
				new Object[] { branch.getBranchName(), branch.getBranchAddress() });
	}

	public void update(LibraryBranch branch) throws SQLException, ClassNotFoundException {
		this.save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId() });
	}

	public void delete(LibraryBranch branch) throws SQLException, ClassNotFoundException {
		this.save("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[] { branch.getBranchId() });
	}

	public List<LibraryBranch> readEverythingBranches() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_library_branch", null);
	}

	public List<LibraryBranch> readAllBranchNames() throws SQLException, ClassNotFoundException {
		return read("select branchName from tbl_library_branch", null);
	}

	public LibraryBranch readBranchFromId(Integer id) throws SQLException, ClassNotFoundException {
		return readSingle("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[] { id });
	}

	public List<LibraryBranch> readBrancheNamesForCardNo(Integer cardNo) throws SQLException, ClassNotFoundException {
		return read(
				"select LB.branchName from" + "tbl_borrower as B, tbl_book_loans as BL," + "tbl_library_branch as LB"
						+ "WHERE B.cardNo=BL.cardNo" + "AND BL.branchId=LB.branchId" + "AND B.cardNo=?",
				new Object[] { cardNo });
	}

	@Override
	List<LibraryBranch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<LibraryBranch> branches = new ArrayList<>();
		while (rs.next()) {
			LibraryBranch branch = new LibraryBranch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			branches.add(branch);
		}
		return branches;
	}

	@Override
	LibraryBranch extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
		if (rs.next()) {
			LibraryBranch branch = new LibraryBranch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			return branch;
		}
		return null;
	}

}