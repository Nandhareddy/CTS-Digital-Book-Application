package com.digitalbook.modal;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reader_pyment_info")
public class ReaderPaymentInfo implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private UUID id;
	private String readerName;
	private String readerEmail;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	
	public ReaderPaymentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReaderPaymentInfo(UUID id, String readerName, String readerEmail ,Book book ) {
		super();
		this.id = id;
		this.readerName = readerName;
		this.readerEmail = readerEmail;
		this.book=book;
	}
	public ReaderPaymentInfo(String readerName, String readerEmail,Book book) {
		super();
		this.readerName = readerName;
		this.readerEmail = readerEmail;
		this.book=book;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getReaderEmail() {
		return readerEmail;
	}
	public void setReaderEmail(String readerEmail) {
		this.readerEmail = readerEmail;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "ReaderPaymentInfo [id=" + id + ", readerName=" + readerName + ", readerEmail=" + readerEmail + "]";
	}
	

}
