package models.dto;

import java.io.Serializable;

public class ImagesDTO implements Serializable{
	private	long acom_no;
	private	int	no;
	private	String origin_file;
	private	String save_file;
	
	public ImagesDTO() {}

	public ImagesDTO(long acom_no, int no, String origin_file, String save_file) {
		super();
		this.acom_no = acom_no;
		this.no = no;
		this.origin_file = origin_file;
		this.save_file = save_file;
	}



	@Override
	public String toString() {
		return "ImagesDTO [acom_no=" + acom_no + ", no=" + no + ", origin_file=" + origin_file + ", save_file="
				+ save_file + "]";
	}



	public long getAcom_no() {
		return acom_no;
	}

	public void setAcom_no(long acom_no) {
		this.acom_no = acom_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOrigin_file() {
		return origin_file;
	}

	public void setOrigin_file(String origin_file) {
		this.origin_file = origin_file;
	}

	public String getSave_file() {
		return save_file;
	}

	public void setSave_file(String save_file) {
		this.save_file = save_file;
	}

	
	

}
