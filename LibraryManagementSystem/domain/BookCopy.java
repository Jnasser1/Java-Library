package lms.domain;

public class BookCopy extends BaseDomain<BookCopy>{
	
	private static final long serialVersionUID = -1702332765866813559L;
	private Integer  bookId;
	private Integer branchId;
	private Integer noOfCopies;

	public BookCopy() {
		this.bookId = null;
		this.branchId=null;
		this.noOfCopies=null;
	}

	public BookCopy(Integer bookId,Integer branchId, Integer noOfCopies) {
		this.bookId=bookId;
		this.branchId=branchId;
		this.noOfCopies=noOfCopies;
		
	}

	public Integer getBookId() {
		return this.bookId;
	}
	
	public Integer getBranchId() {
		return this.branchId;
		
	}
	
	public Integer getNoOfCopies() {
		return this.noOfCopies;
	}
	
	
	
	public void setBookId(Integer bookId) {
		this.bookId=bookId;
	}
	
	public void setBranchId(Integer branchId) {
		 this.branchId=branchId;
		
	}
	
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies=noOfCopies;
	}
	
	
}
