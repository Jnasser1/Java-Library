package lms.domain;

public class Genre extends BaseDomain<Genre> {

	private static final long serialVersionUID = -8742805682122046991L;

	private Integer genre_id;
	private String genre_name;

	public Genre() {
		this.genre_id = null;
		this.genre_name = null;
	}

	public Genre(Integer genre_id, String genre_name) {
		this.genre_id = genre_id;
		this.genre_name = genre_name;
	}

	public Integer getGenreId() {
		return this.genre_id;
	}

	public void setGenreId(Integer genre_id) {
		this.genre_id = genre_id;
	}

	public String getGenreName() {
		return this.genre_name;
	}

	public void setGenreName(String genre_name) {
		this.genre_name = genre_name;
	}
}
