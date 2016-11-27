package seroter.demo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.models.BrokeredMessage;

@SpringBootApplication
@Controller
public class SpringbootAzureConcourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAzureConcourseApplication.class, args);
	}
	
	//pull in autowired bean with service bus connection
	@Autowired
	ServiceBusContract serviceBusContract;
	
	@GetMapping("/")
	public String showPaymentForm(Model m) {
		
		//add webpayment object to view
		m.addAttribute("webpayment", new WebPayment());
		
		//return view name
		return "webpayment";
	}
	
	@PostMapping("/")
	public String paymentSubmit(@ModelAttribute WebPayment webpayment) {
		
		//add submit date
		webpayment.setSubmitDate(LocalDateTime.now().toString());
		
		try {
			//convert webpayment object to JSON to send to queue
			ObjectMapper om = new ObjectMapper();
			String jsonPayload = om.writeValueAsString(webpayment);
			
			//create brokered message wrapper used by service bus
			BrokeredMessage m = new BrokeredMessage(jsonPayload);
			//send to queue
			serviceBusContract.sendMessage("demoqueue", m);
			System.out.println("message sent");
			
		} catch (ServiceException e) {
			System.out.println("error sending to queue - " + e.getMessage());
		} catch (JsonProcessingException e) {
			System.out.println("error converting payload - " + e.getMessage());
		}
		
		return "paymentconfirm";
		
	}
}
