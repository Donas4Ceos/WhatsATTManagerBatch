package mx.com.att.dispatcher;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.att.business.ResponseMessageBusiness;
import mx.com.att.model.entity.WhatsAppMessageWebhook;
import mx.com.att.model.repository.WhatsAppMessageRepository;
import mx.com.att.model.repository.WhatsAppMessageWebhookRepository;

@Component
public class ResponseMessageDispatcher implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger( ResponseMessageDispatcher.class );

	@Autowired
	WhatsAppMessageWebhookRepository repositoryWebhook;

	@Autowired
	WhatsAppMessageRepository repositoryMessage;

	private static int MAX_THREAD_POOLING = 10000;

	public void run() {

		List<WhatsAppMessageWebhook> chatResponseCliente = new ArrayList<WhatsAppMessageWebhook>();

		final ThreadGroup threadGroup = new ThreadGroup("ResponseMessage");

		while(true)
		{
			try
			{
				System.out.println("threadGroup.activeCount() ->>> " + threadGroup.activeCount());
				if(!chatResponseCliente.isEmpty() && threadGroup.activeCount() < 5)
				{
					final WhatsAppMessageWebhook objectEntry = (WhatsAppMessageWebhook) chatResponseCliente.get(0);
					chatResponseCliente.remove(0);
					try {
						final ResponseMessageBusiness objBusiness = new ResponseMessageBusiness();
						objBusiness.setWhatsAppEntity(objectEntry);
						objBusiness.setRepositoryWebhook(repositoryWebhook);
						objBusiness.setRepositoryMessage(repositoryMessage);
						final Thread thread = new Thread(threadGroup, objBusiness);
						thread.start();
						System.out.println("Inicia ejecucion de thread ---->>");
					}   
					catch(Exception exception)
					{
						LOGGER.error("error en ordenesPendientesList :: " + exception.getMessage(), exception);

					}
				}
				else
				{
					Thread.sleep(MAX_THREAD_POOLING);
					if(chatResponseCliente.isEmpty()) {
						chatResponseCliente = repositoryWebhook.getMessageResponseCliente();
						
						LOGGER.info("tamaño de lista chatResponseCliente -> " + chatResponseCliente.size());
					}
				}
			}
			catch (Exception exception)
			{

				try {
					Thread.sleep(MAX_THREAD_POOLING);
				} catch (IllegalArgumentException ignored) {
				}
				catch (InterruptedException interruptedException)
				{
				} 
			}

		}
	}

}
