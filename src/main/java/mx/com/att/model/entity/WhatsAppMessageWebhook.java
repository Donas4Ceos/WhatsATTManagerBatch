package mx.com.att.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="WHATSAPP_MESSAGE_WEBHOOK")
public class WhatsAppMessageWebhook implements Serializable{
	
//	@Column(name="ID_MESSAGE_2")
//	private String idMessage2;
	@Column(name="STATUS_MESSAGE")
	private String statusMessage;
	@Column(name="FECHA_MESSAGE")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS", locale = "es-MX", timezone = "Mexico/General")
	private Date fechaMessage;
	@Column(name="TEXTO_MESSAGE")
	private String textoMessage;
	@Column(name="JSON_WEBHOOK")
	private String jsonWebhook;
	
	@EmbeddedId
	private WhatsAppMessageWebhookPK primaryKey;
	
    public WhatsAppMessageWebhookPK getPrimaryKey() {
        return primaryKey;
    }
 
    public void setPrimaryKey(WhatsAppMessageWebhookPK pk) {
        primaryKey = pk;
    }

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Date getFechaMessage() {
		return fechaMessage;
	}

	public void setFechaMessage(Date fechaMessage) {
		this.fechaMessage = fechaMessage;
	}

	public String getTextoMessage() {
		return textoMessage;
	}

	public void setTextoMessage(String textoMessage) {
		this.textoMessage = textoMessage;
	}
	
	public String getJsonWebhook() {
		return jsonWebhook;
	}

	public void setJsonWebhook(String jsonWebhook) {
		this.jsonWebhook = jsonWebhook;
	}

//	public String getIdMessage2() {
//		return idMessage2;
//	}
//
//	public void setIdMessage2(String idMessage2) {
//		this.idMessage2 = idMessage2;
//	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
