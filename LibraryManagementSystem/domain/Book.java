package lms.domain;

public class Book extends BaseDomain<Book> {

	private static final long serialVersionUID = -2412538381563580846L;

	private Integer bookId;
	private String title;
	private Integer pubId;

	public Book() {
		this.bookId = null;
		this.title = null;
		this.pubId = null;
	}

	public Book(Integer bookId, String title, Integer pubId) {
		this.bookId = bookId;
		this.title = title;
		this.pubId = pubId;
	}

	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return this.title;

	}

	public void setTitle(String title) {
		this.title = title;

	}

	public Integer getPubId() {
		return this.pubId;
	}

	public void setPubId(Integer pubId) {
		this.pubId = pubId;
	}

}
