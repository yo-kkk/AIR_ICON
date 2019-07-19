package models.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable{
	private long message_no;
	private String sender_id;
	private	String getter_id;
	private	String content;
	private String reg_date;
	
	public MessageDTO() {}

	public MessageDTO(long message_no, String sender_id, String getter_id, String content, String reg_date) {
		super();
		this.message_no = message_no;
		this.sender_id = sender_id;
		this.getter_id = getter_id;
		this.content = content;
		this.reg_date = reg_date;
	}

	public long getMessage_no() {
		return message_no;
	}

	public void setMessage_no(long message_no) {
		this.message_no = message_no;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getGetter_id() {
		return getter_id;
	}

	public void setGetter_id(String getter_id) {
		this.getter_id = getter_id;
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
		return "MessageDTO [message_no=" + message_no + ", sender_id=" + sender_id + ", getter_id=" + getter_id
				+ ", content=" + content + ", reg_date=" + reg_date + "]";
	}
	
	
	
}
