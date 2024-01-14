package test01;

import java.io.*;

public class Book implements Serializable {
	private String title;
	private String author;
	private String isbn;
	private boolean isBorrowed; //true: 대출중, false: 대출가능
	
	public Book() {}
	
	public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }
	public String getIsbn() { return isbn; }
	public void setIsbn(String isbn) { this.isbn = isbn; }
	public boolean isBorrowed() { return isBorrowed; }
	public void setBorrowed(boolean isBorrowed) { this.isBorrowed = isBorrowed; }
	
	public String getBorrowedStatus() {
		return isBorrowed ? "대출중" : "대출가능";
	}
	
	public void borrowBook() { //"책이 대출되었습니다."
		if(!isBorrowed) { //대출가능 상태
			isBorrowed = true; //대출중
		} else { //대출불가능 상태
			throw new IllegalStateException("대출중"); //주석4: 예외 처리
		}
	}
	
	public void returnBook() { //"책이 반납되었습니다."
		if(isBorrowed) { //대출중 상태
			isBorrowed = false; //대출가능 상태
		} else { //대출가능 상태
			throw new IllegalStateException("대출가능"); //주석4: 예외 처리
		}
	}
}
