package lms.domain;

import java.sql.Timestamp;

public class BookLoan extends BaseDomain<BookLoan> {

	private static final long serialVersionUID = 3057269855435945593L;

	private Integer bookId;
	private Integer branchId;
	private Integer cardNo;
	private Timestamp dateOut;
	private Timestamp dateIn;
	private Timestamp dueDate;

	public BookLoan() {
		this.bookId = null;
		this.branchId = null;
		this.cardNo = null;
		this.dateOut = null;
		this.dateIn = null;
		this.dueDate = null;
	}

	public BookLoan(Integer bookId, Integer branchId, Integer cardNo, Timestamp dateOut, Timestamp dateIn,
			Timestamp dueDate) {
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
		this.dateOut = dateOut;
		this.dateIn = dateIn;
		this.dueDate = dueDate;
	}

	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBranchId() {
		return this.branchId;

	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Timestamp getDateOut() {
		return this.dateOut;
	}

	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}

	public Timestamp getDateIn() {
		return this.dateIn;
	}

	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}

	public Timestamp getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

}
