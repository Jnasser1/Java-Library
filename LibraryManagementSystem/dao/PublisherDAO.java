package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> {

	public PublisherDAO(Connection conn) {
		super(conn);
	}

	public void add(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_publisher VALUES (?, ?, ?, ?)", new Object[] { publisher.getPublisherId(),
				publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone() });
	}

	// Adds another publisher row to tbl_publisher with pubId = max(pubId) + 1
	public void addToEnd(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("SET @max_id = (SELECT MAX(publisherId) FROM `tbl_publisher`)", null);
		save("insert into tbl_publisher values (@max_id + 1, ?, ?, ?)", new Object[] { publisher.getPublisherName(),
				publisher.getPublisherAddress(), publisher.getPublisherPhone() });
	}

	// Update based on tbl_publisher pk
	public void update(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
						publisher.getPublisherPhone(), publisher.getPublisherId() });
	}

	public void delete(Publisher publisher) throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_publisher where publisherId = ?", new Object[] { publisher.getPublisherId() });
	}

	public List<Publisher> readAllPublishers() throws SQLException, ClassNotFoundException {
		return this.read("select * from tbl_publisher", null);
	}

	public Publisher readPublisherById(Integer pubId) throws SQLException, ClassNotFoundException {
		return readSingle("select * from tbl_publisher where publisherId = ?", new Object[] { pubId });
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Publisher> pubList = new ArrayList<>();
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			pubList.add(publisher);
		}

		return pubList;
	}

	@Override
	public Publisher extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

		if (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			return publisher;
		}

		return null;
	}

}