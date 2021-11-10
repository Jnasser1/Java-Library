package lms.domain;

public class LibraryBranch extends BaseDomain<LibraryBranch> {

	private static final long serialVersionUID = -6480492263759989390L;
	private Integer branchId;
	private String branchName;
	private String branchAddress;

	public LibraryBranch() {
		this.branchId = null;
		this.branchAddress = null;
		this.branchName = null;
	}

	public LibraryBranch(Integer branchId, String branchAddress, String branchName) {
		this.branchId = branchId;
		this.branchAddress = branchAddress;
		this.branchName = branchName;
	}

	public Integer getBranchId() {
		return this.branchId;

	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getBranchAddress() {
		return this.branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
