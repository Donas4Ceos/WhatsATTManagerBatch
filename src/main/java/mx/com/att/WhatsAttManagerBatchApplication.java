package mx.com.att;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mx.com.att.dispatcher.ResponseMessageDispatcher;

@SpringBootApplication
public class WhatsAttManagerBatchApplication implements CommandLineRunner{
	
	@Autowired
	ResponseMessageDispatcher dispatcherResponseMessage;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WhatsAttManagerBatchApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		startResponseMessage();
	}
	
	private void startResponseMessage() {
        try{
        	final Thread thread = new Thread(dispatcherResponseMessage);
            thread.start();
        }catch(IllegalThreadStateException exception){
        }
    }

}
