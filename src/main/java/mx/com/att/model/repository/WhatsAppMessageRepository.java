package mx.com.att.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.att.model.entity.WhatsAppMessage;

@Repository
public interface WhatsAppMessageRepository extends JpaRepository<WhatsAppMessage, String>{
	
	@Query(value = "SELECT JSON_VALUE(REQUEST_MESSAGE, '$.template.name') TEMPLATE FROM WHATSAPP_MESSAGE WHERE JSON_VALUE(REQUEST_MESSAGE, '$.to') = :mdn AND TEMPLATE_MESSAGE = 'renovaciones' order by DATE_MESSAGE desc" , nativeQuery = true)
	public List<String> getTemplateMessageSendCliente(@Param("mdn") String mdn);
}
