package mx.com.att.model.vo;

import java.io.Serializable;

public class RequestMessageResponse implements Serializable{
	
	private String wam_id;
	private String mdn;
	private String text_message_chat;
	private String phone_number_id;
	
	public String getWam_id() {
		return wam_id;
	}



	public void setWam_id(String wam_id) {
		this.wam_id = wam_id;
	}



	public String getMdn() {
		return mdn;
	}



	public void setMdn(String mdn) {
		this.mdn = mdn;
	}



	public String getText_message_chat() {
		return text_message_chat;
	}



	public void setText_message_chat(String text_message_chat) {
		this.text_message_chat = text_message_chat;
	}



	public String getPhone_number_id() {
		return phone_number_id;
	}



	public void setPhone_number_id(String phone_number_id) {
		this.phone_number_id = phone_number_id;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
