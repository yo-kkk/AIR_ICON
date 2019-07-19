package profile.models;

public class Bookmark_profileDTO {
	
	private long bookmark_no;
	private	long acom_no;
	private String user_id;
	private String host_name;
	private long price;
	private String title;
	private String address;
	public long getBookmark_no() {
		return bookmark_no;
	}
	public void setBookmark_no(long bookmark_no) {
		this.bookmark_no = bookmark_no;
	}
	public long getAcom_no() {
		return acom_no;
	}
	public void setAcom_no(long acom_no) {
		this.acom_no = acom_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Bookmark_profileDTO [bookmark_no=" + bookmark_no + ", acom_no=" + acom_no + ", user_id=" + user_id
				+ ", host_name=" + host_name + ", price=" + price + ", title=" + title + ", address=" + address + "]";
	}
	
	public Bookmark_profileDTO(long bookmark_no, long acom_no, String user_id, String host_name, long price,
			String title, String address) {
		super();
		this.bookmark_no = bookmark_no;
		this.acom_no = acom_no;
		this.user_id = user_id;
		this.host_name = host_name;
		this.price = price;
		this.title = title;
		this.address = address;
	}
	public Bookmark_profileDTO() {
		
	}

	
	
}
