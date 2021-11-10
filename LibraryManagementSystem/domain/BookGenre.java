package lms.domain;

public class BookGenre extends BaseDomain<BookGenre> {

	private static final long serialVersionUID = 1447668607776211549L;
	private Integer genre_id;
	private Integer bookId;

	public BookGenre() {
		this.genre_id = null;
		this.bookId = null;
	}

	public BookGenre(Integer genre_id, Integer bookId) {
		this.genre_id = genre_id;
		this.bookId = bookId;
	}

	public Integer getGenreId() {
		return this.genre_id;
	}

	public void setGenreId(Integer genre_id) {
		this.genre_id = genre_id;
	}

	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
}