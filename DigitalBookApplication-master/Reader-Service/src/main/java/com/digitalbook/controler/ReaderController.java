package com.digitalbook.controler;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitalbook.modal.Book;
import com.digitalbook.modal.ReaderPaymentInfo;
import com.digitalbook.response.BookResponse;
import com.digitalbook.service.BookRestConsumer;
import com.digitalbook.service.ReaderService;


@RestController
@RequestMapping("/api/v1/digitalbook/reader/")
public class ReaderController {

	@Autowired
	BookRestConsumer consumer;
	@Autowired
	ReaderService service;

	@GetMapping("book/search/{category}/{price}/{authorName}/{publisher}")
	public ResponseEntity<List<BookResponse>> getBooksBySearchCriteria(@PathVariable(name = "category") String category,
			@PathVariable(name = "price") float price, @PathVariable(name = "authorName") String authorName,
			@PathVariable(name = "publisher") String publisher) {
		return consumer.getBooksBySearchCriteria(category, price, authorName, publisher);
	}


	@PostMapping("/books/buy")
	public ResponseEntity<String> saveBook(@RequestParam String userName, @RequestParam String email, @RequestParam String tittle,
			@RequestParam String category, @RequestParam float price, @RequestParam String authorName,
			@RequestParam String publisher, @RequestParam String publisherDate, @RequestParam boolean active,
			@RequestParam String content) throws IOException  {
		Book bookdto = new Book();
		
		bookdto.setTittle(tittle);
		bookdto.setCategory(category);
		bookdto.setPrice(price);
		bookdto.setAuthorName(authorName);
		bookdto.setPublisher(publisher);
		bookdto.setPublishedDate(publisherDate);
		bookdto.setActive(active);
		bookdto.setContent(content);
		
		ReaderPaymentInfo info=new ReaderPaymentInfo();
		
		info.setReaderName(userName);
		info.setReaderEmail(email);
		info.setBook(bookdto);
		String response=service.saveSubscibtionBook(info);
		
		return new ResponseEntity<String>(response,HttpStatus.CREATED);

	}
	
	@GetMapping("{email}/books")
	public ResponseEntity<List<ReaderPaymentInfo>> getSubscibtionBooks(@PathVariable(name = "email") String email){
		List<ReaderPaymentInfo> subscibtionBooks=service.getSubscribtionBooks(email);
		return new ResponseEntity<List<ReaderPaymentInfo>>(subscibtionBooks,HttpStatus.OK);
		
	}
}
