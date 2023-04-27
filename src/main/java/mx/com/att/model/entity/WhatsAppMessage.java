package mx.com.att.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="WHATSAPP_MESSAGE")
public class WhatsAppMessage implements Serializable{
	
	@Id
	@Column(name="ID_MESSAGE")
	private String idMessage;
	@Column(name="REQUEST_MESSAGE")
	private String requestMessage;
	@Column(name = "RESPONSE_MESSAGE")
	private String responseMessage;
	@Column(name="TOKEN_MESSAGE")
	private String tokenMessage;
	@Column(name="TEMPLATE_MESSAGE")
	private String templateMessage;
	@Column(name="DATE_MESSAGE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS", locale = "es-MX", timezone = "Mexico/General")
	private Date dateMessage;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getTokenMessage() {
		return tokenMessage;
	}

	public void setTokenMessage(String tokenMessage) {
		this.tokenMessage = tokenMessage;
	}

	public String getTemplateMessage() {
		return templateMessage;
	}

	public void setTemplateMessage(String templateMessage) {
		this.templateMessage = templateMessage;
	}

	public Date getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(Date dateMessage) {
		this.dateMessage = dateMessage;
	}
	
}
