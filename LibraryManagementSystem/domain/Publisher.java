package lms.domain;

public class Publisher extends BaseDomain<Publisher> {

	private static final long serialVersionUID = 2051058033187960996L;
	protected Integer publisherId;
	protected String publisherName;
	protected String publisherAddress;
	protected String publisherPhone;

	public Publisher() {
		this.publisherId = null;
		this.publisherName = null;
		this.publisherAddress = null;
		this.publisherPhone = null;
	}

	public Publisher(Integer publisherId, String publisherName, String publisherAddress, String publisherPhone) {
		this.publisherId = publisherId;
		this.publisherName = publisherName;
		this.publisherAddress = publisherAddress;
		this.publisherPhone = publisherPhone;
	}

	public Integer getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return this.publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherAddress() {
		return this.publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	public String getPublisherPhone() {
		return this.publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

}
