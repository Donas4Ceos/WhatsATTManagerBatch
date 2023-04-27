package mx.com.att.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.att.model.entity.WhatsAppMessageWebhook;
import mx.com.att.model.entity.WhatsAppMessageWebhookPK;

@Repository
public interface WhatsAppMessageWebhookRepository extends JpaRepository<WhatsAppMessageWebhook, WhatsAppMessageWebhookPK>{

	@Query(value = "SELECT ID_MESSAGE, MDN_MESSAGE, STATUS_MESSAGE, FECHA_MESSAGE, TEXTO_MESSAGE, JSON_WEBHOOK FROM WHATSAPP_MESSAGE_WEBHOOK WHERE STATUS_MESSAGE like '%clientResponse%'" , nativeQuery = true)
	public List<WhatsAppMessageWebhook> getMessageResponseCliente();
	
	@Query(value = "SELECT JSON_VALUE(TEXTO_MESSAGE, '$.body') RESP FROM WHATSAPP_MESSAGE_WEBHOOK WHERE ID_MESSAGE = :wam_id" , nativeQuery = true)
	public String getTextMessageResponseCliente(@Param("wam_id") String wam_id);
}
