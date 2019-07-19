package message.controllers;

import java.io.Serializable;

public class MessageDTO_list implements Serializable{
	private long message_no;
	private String user_id;
	private	String other_id;
	private	String content;
	private String reg_date;
	
	
	
	public MessageDTO_list() {
		super();
		// TODO Auto-generated constructor stub
	}



	public MessageDTO_list(long message_no, String user_id, String other_id, String content, String reg_date) {
		super();
		this.message_no = message_no;
		this.user_id = user_id;
		this.other_id = other_id;
		this.content = content;
		this.reg_date = reg_date;
	}



	public long getMessage_no() {
		return message_no;
	}



	public void setMessage_no(long message_no) {
		this.message_no = message_no;
	}



	public String getUser_id() {
		return user_id;
	}



	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getOther_id() {
		return other_id;
	}



	public void setOther_id(String other_id) {
		this.other_id = other_id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getReg_date() {
		return reg_date;
	}



	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}



	@Override
	public String toString() {
		return "MessageDTO_dateString [message_no=" + message_no + ", user_id=" + user_id + ", other_id=" + other_id
				+ ", content=" + content + ", reg_date=" + reg_date + "]";
	}
	
	

	
}
