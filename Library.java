/*
[도서관 도서 관리 프로그램]

학과: 컴퓨터공학과
학번: 202104404
이름: 박진영

과제 주제: 도서 정보를 저장하고 도서관 이용자로부터 검색을 통해 도서의 대출 가능 여부를 확인하고,
		 대출과 반납이 이루어진 도서의 상태를 업데이트하여 저장하는 등 도서 관리 기능을 하는 프로그램입니다.
 */



package test01;

import java.util.*;
import java.io.*;

public class Library {
	private final BookManagement bookManagement;
    private final Scanner scanner;
	public Library(BookManagement bookManagement) {
        this.bookManagement = bookManagement;
        this.scanner = new Scanner(System.in);
    }
	
	public void showMenu() {
		while(true) {
			System.out.println("--------------------------------------------");
			System.out.println("1.등록  |  2.검색  |  3.대출  |  4.반납  |  5.종료");
			System.out.println("--------------------------------------------");
			
			System.out.print("선택: ");
			String selectNo = scanner.nextLine();
			try {
				switch(selectNo) {
				case "1": registerBook(); break;
				case "2": searchBook(); break;
				case "3": borrowBook(); break;
				case "4": returnBook(); break;
				case "5": return;
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void registerBook() { // 새로운 도서 등록
		try {
			Book book = new Book();
			
			System.out.print("제목: ");
			book.setTitle(scanner.nextLine());
			
			System.out.print("저자: ");
			book.setAuthor(scanner.nextLine());
			
			System.out.print("isbn: ");
			book.setIsbn(scanner.nextLine());
			
			bookManagement.addBook(book); //새로 등록한 Book 객체를 리스트에 추가
			bookManagement.saveBooksToFile(); //등록한 도서들을 파일에 저장
		} catch(Exception e) {
			System.out.println("도서 등록 오류: " + e.getMessage());
		}
	}
	
	public void searchBook() {
		bookManagement.loadBooksFromFile(); //파일에서 도서 목록 불러오기
		System.out.print("검색할 책 이름을 입력하세요: ");
	    String searchTerm = scanner.nextLine();
		
	    Book foundBook = bookManagement.searchBookByTitle(searchTerm);
	    if(foundBook != null) { //검색한 책이 파일에 있다면
	    	System.out.println(foundBook.getBorrowedStatus()); //대출가능 상태
	    } else { //검색한 책이 파일에 없다면
	    	System.out.println("검색한 책을 찾을 수 없습니다."); //대출중이거나 책이 없는 상태
	    }
	}
	
	private void borrowBook() {
        System.out.print("대출할 책의 제목을 입력하세요: ");
        String title = scanner.nextLine();
        
        try {
            bookManagement.borrowBook(title);
            bookManagement.saveBooksToFile(); //해당 책을 "대출중" 상태로 업데이트
            System.out.println("책이 대출되었습니다.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
	private void returnBook() {
        System.out.print("반납할 책의 제목을 입력하세요: ");
        String title = scanner.nextLine();
        
        try {
            bookManagement.returnBook(title);
            bookManagement.saveBooksToFile(); //해당 책을 "대출가능" 상태로 업데이트
            System.out.println("책이 반납되었습니다.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
	private void saveBooksToFile() {
		bookManagement.saveBooksToFile(); //현재 도서 목록을 파일에 저장
    }
	
	private void loadBooksFromFile() {
		bookManagement.loadBooksFromFile(); //파일에서 도서 목록을 불러옴
	}
}

