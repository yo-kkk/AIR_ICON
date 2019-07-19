package models.dto;

import java.sql.Date;

public class SearchDTO {
	Date startday;
	Date endday;
	int person;
	String location;
	
	public SearchDTO(){}

	public SearchDTO(Date startday, Date endday, int person, String location) {
		super();
		this.startday = startday;
		this.endday = endday;
		this.person = person;
		this.location = location;
	}



	public Date getStartday() {
		return startday;
	}

	public void setStartday(Date startday) {
		this.startday = startday;
	}

	public Date getEndday() {
		return endday;
	}

	public void setEndday(Date endday) {
		this.endday = endday;
	}

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "SearchDTO [startday=" + startday + ", endday=" + endday + ", person=" + person + ", location="
				+ location + "]";
	}
	
	
	
}
