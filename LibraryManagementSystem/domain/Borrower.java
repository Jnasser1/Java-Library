package lms.domain;

public class Borrower extends BaseDomain<Borrower> {

	private static final long serialVersionUID = 5726163349606416989L;

	private Integer cardNo;
	private String name;
	private String address;
	private String phone;

	public Borrower() {
		this.cardNo = null;
		this.name = null;
		this.address = null;
		this.phone = null;
	}

	public Borrower(Integer cardNo, String name, String address, String phone) {
		this.cardNo = cardNo;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public Integer getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
