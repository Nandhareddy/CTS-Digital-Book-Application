package com.digitalbook.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



import com.digitalbook.modal.ReaderPaymentInfo;
import com.digitalbook.repository.SubscriptionWithPaymentReository;

@Service
public class ReaderService {
	
	
	@Autowired
	private SubscriptionWithPaymentReository repo;
	
	public String saveSubscibtionBook(ReaderPaymentInfo payInfo) throws IOException {
		ReaderPaymentInfo paymentIno=repo.save(payInfo);
		return "Payment is Completed. Payment Id : "+paymentIno.getId();
	}
	public List<ReaderPaymentInfo> getSubscribtionBooks(String email){
		
		return repo.findByReaderEmail(email);
		
	}
	
	 @KafkaListener(topics ="author-bookupdate-topic", 
	            groupId = "group-id")
	    public void consume(String message) {
		 
	        System.out.println("Message recived from Book Service After Update :"+message);
	    }
}
