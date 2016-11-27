package seroter.demo;

import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.ServiceBusService;
import com.microsoft.windowsazure.services.servicebus.models.CreateQueueResult;
import com.microsoft.windowsazure.services.servicebus.models.ListQueuesResult;
import com.microsoft.windowsazure.services.servicebus.models.QueueInfo;

@Configuration
public class SbConfig {
	
	@Bean
	ServiceBusContract serviceBusContract() {
		
		//grab env variable that comes from binding CF app to the Azure service
		String vcap = System.getenv("VCAP_SERVICES");
		
		//parse the JSON in the environment variable
		JsonParser jsonParser = JsonParserFactory.getJsonParser();
		Map<String, Object> jsonMap = jsonParser.parseMap(vcap);
		
		//create map of values for service bus creds
		Map<String,Object> creds = (Map<String,Object>)((List<Map<String, Object>>)jsonMap.get("seroter-azureservicebus")).get(0).get("credentials");
		
		//create service bus config object
		com.microsoft.windowsazure.Configuration config = 
			ServiceBusConfiguration.configureWithSASAuthentication(
					creds.get("namespace_name").toString(), 
					creds.get("shared_access_key_name").toString(), 
					creds.get("shared_access_key_value").toString(), 
					".servicebus.windows.net");
		
		//create object used for interacting with service bus
		ServiceBusContract svc = ServiceBusService.create(config);
		System.out.println("created service bus contract ...");
		
		//check if queue exists
		try {
			ListQueuesResult r = svc.listQueues();
			List<QueueInfo> qi = r.getItems();
			boolean hasQueue = false;
			
			for (QueueInfo queueInfo : qi) {
				
				System.out.println("queue is " + queueInfo.getPath());
				
				//queue exist already?
				if(queueInfo.getPath().equals("demoqueue"))  {
					System.out.println("Queue already exists");
					hasQueue = true;
					break;
				}
			}
			
			if(!hasQueue) {
				//create queue because we didn't find it
				try {
					QueueInfo q = new QueueInfo("demoqueue");
					
					CreateQueueResult result = svc.createQueue(q);
					System.out.println("queue created");
				}
				catch(ServiceException createException) {
					System.out.println("Error: " + createException.getMessage());
				}
			}
			
		} catch (ServiceException findException) {
			System.out.println("Error: " + findException.getMessage());
		}
		
		return svc;
	}

}
