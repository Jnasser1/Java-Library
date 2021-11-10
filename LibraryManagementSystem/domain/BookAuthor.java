package lms.domain;

public class BookAuthor extends BaseDomain<BookAuthor> {

	
	
	private static final long serialVersionUID = -4327170178812163600L;
	private Integer bookId;
	private Integer authorId;
	
	
	public BookAuthor() {
		this.bookId = null;
		this.authorId=null;
	}

	public BookAuthor(Integer bookId, Integer authorId) {
		this.bookId = bookId;
		this.authorId=authorId;
	}

	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getAuthorId() {
		return this.authorId;

	}

	public void setAuthorId(Integer authorId) {
		this.authorId=authorId;

	}
}