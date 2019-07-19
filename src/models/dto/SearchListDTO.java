package models.dto;

public class SearchListDTO {
	String intro_title;
	long price;
	long acom_no;
	float rate;
	private	String save_file;
	
	public SearchListDTO() {}

	public SearchListDTO(String intro_title, long price, long acom_no, float rate,
			String save_file) {
		super();
		this.intro_title = intro_title;
		this.price = price;
		this.acom_no = acom_no;
		this.rate = rate;
		this.save_file = save_file;
	}

	public String getIntro_title() {
		return intro_title;
	}

	public void setIntro_title(String intro_title) {
		this.intro_title = intro_title;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getAcom_no() {
		return acom_no;
	}

	public void setAcom_no(long acom_no) {
		this.acom_no = acom_no;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getSave_file() {
		return save_file;
	}

	public void setSave_file(String save_file) {
		this.save_file = save_file;
	}

	@Override
	public String toString() {
		return "SearchListDTO [intro_title=" + intro_title + ", price=" + price + ", acom_no=" + acom_no + ", rate="
				+ rate + ", save_file=" + save_file + "]";
	}

	
}
