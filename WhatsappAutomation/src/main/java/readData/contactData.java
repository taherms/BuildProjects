package readData;

public class contactData {
	private String contactNumber;
	private String message;
	public contactData(String contactNumber, String message) {
		super();
		this.contactNumber = contactNumber;
		this.message = message;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
