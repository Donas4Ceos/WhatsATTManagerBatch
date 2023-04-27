package mx.com.att.business;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import mx.com.att.model.entity.WhatsAppMessageWebhook;
import mx.com.att.model.repository.WhatsAppMessageRepository;
import mx.com.att.model.repository.WhatsAppMessageWebhookRepository;
import mx.com.att.model.vo.RequestMessageResponse;

public class ResponseMessageBusiness implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger( ResponseMessageBusiness.class );

	private WhatsAppMessageWebhookRepository repositoryWebhook;

	private WhatsAppMessageRepository repositoryMessage;

	private WhatsAppMessageWebhook whatsAppEntity;

	private static final String token = "Bearer EAAKUbSZAWSPkBABpDHsbcaeGnvtL2xExdjt7SPGtO4OsaTiXw5mQwbKyrVjNJOzgoEuQ8tBTRz21Idy4JglnnplAdMtVTH1r6q05ea8Vzon6kDOEoNICZApEZBOKVc6sOk63Kj5ZBnP5PVtFm5ZC7rQ4dO7Uj3BlqH1MEgo17mFZCrW6Q1aOoTaOOnQRv4BBkK1RvS2ZCaoMQZDZD";

	@Override
	public void run() {


		String mensaje = "Recomienda y gana con ATT. Recibimos el número de tu recomendado, al terminar la portabilidad recibirán ambos sus beneficios";

		//		for(WhatsAppMessageWebhook responseWebhook: chatResponseCliente) {


//		LOGGER.info("whatsAppEntity.getPrimaryKey().getMdnMessage() - >" + whatsAppEntity.getPrimaryKey().getMdnMessage());
//		List<String> template = repositoryMessage.getTemplateMessageSendCliente(whatsAppEntity.getPrimaryKey().getMdnMessage());
//		LOGGER.info("nombre de ultimo template enviado al cliente -> " + template !=null ? template.get(0): "null");
//
//		if(!"renovaciones".equals(template !=null ? template.get(0): "null")) {

			//				String wam_id = repositoryMessage.getTextMessageSendCliente(responseWebhook.getPrimaryKey().getIdMessage());
			//				LOGGER.info("nombre de template -> " + template);


			RequestMessageResponse requestMessageResponse = new RequestMessageResponse();

			requestMessageResponse.setWam_id(whatsAppEntity.getPrimaryKey().getIdMessage());
			requestMessageResponse.setMdn(whatsAppEntity.getPrimaryKey().getMdnMessage().replaceFirst("1", ""));
			requestMessageResponse.setPhone_number_id("112370725135077");
			requestMessageResponse.setText_message_chat(mensaje);

			//			String respCliente = responseWebhook.getTextoMessage();

			String url = "http://whatsapp-messages:7051/responseMessage";

			try {
				HttpRequest request = HttpRequest.newBuilder()
						.uri(new URI(url))
						.header("Authorization", token)
						.header("Content-Type", "application/json")
						.POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(requestMessageResponse)))
						.build();
				HttpClient http = HttpClient.newHttpClient();
				HttpResponse<String> response = http.send(request,BodyHandlers.ofString());

				LOGGER.info("response.body() - > " + response.body());

				//Actualizamos estatus mensaje para no enviar dobles respuestas
				whatsAppEntity.setStatusMessage("chatResponseOK");
				repositoryWebhook.saveAndFlush(whatsAppEntity);

			} catch (URISyntaxException | IOException | InterruptedException e) {
				e.printStackTrace();
				LOGGER.info("ERROR -> " + e.getMessage());
			}
//		}
		//		}
	}

	public WhatsAppMessageWebhookRepository getRepositoryWebhook() {
		return repositoryWebhook;
	}

	public void setRepositoryWebhook(WhatsAppMessageWebhookRepository repositoryWebhook) {
		this.repositoryWebhook = repositoryWebhook;
	}

	public WhatsAppMessageRepository getRepositoryMessage() {
		return repositoryMessage;
	}

	public void setRepositoryMessage(WhatsAppMessageRepository repositoryMessage) {
		this.repositoryMessage = repositoryMessage;
	}

	public WhatsAppMessageWebhook getWhatsAppEntity() {
		return whatsAppEntity;
	}

	public void setWhatsAppEntity(WhatsAppMessageWebhook whatsAppEntity) {
		this.whatsAppEntity = whatsAppEntity;
	}

}
