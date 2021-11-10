package lms.domain;


public class Author extends BaseDomain<Author> {
	
	private static final long serialVersionUID = -1454444774998492578L;
	
	private Integer authorId;
	private String authorName;

	public Author() {
		this.authorId = null;
		this.authorName = null;
	}

	public Author(Integer authorId, String authorName) {
		this.authorId = authorId;
		this.authorName = authorName;
	}

	public Integer getAuthorId() {
		return this.authorId;
	}

	public String getAuthorName() {
		return this.authorName;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}