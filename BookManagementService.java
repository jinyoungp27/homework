package test01;

import java.util.*;
import java.io.*;

public class BookManagementService implements BookManagement { //주석3: 인터페이스 구현
	private List<Book> list = new ArrayList<>(); //주석7: List컬렉션 프레임워크
    private String filename = "listofbooks.dat"; //파일 이름 저장->새로 만들기

    @Override //새로 등록한 Book 객체를 리스트에 추가
    public void addBook(Book book) {
        list.add(book); //주석7: List컬렉션 프레임워크
    }

    @Override //제목으로 도서 검색
    public Book searchBookByTitle(String title) {
        return list.stream()
                   .filter(b -> b.getTitle().equalsIgnoreCase(title)) //list에 찾고자 하는 제목과 일치하는 첫번째 Book객체를 찾아 반환
                   .findFirst()
                   .orElse(null); //찾는 도서가 없으면 null 반환
    }

    @Override //제목으로 도서 대출
    public void borrowBook(String title) throws Exception {
        Book book = searchBookByTitle(title);
        if (book != null && !book.isBorrowed()) { //도서가 있고 대출가능 상태이면
            book.borrowBook(); //대출중 상태로 변경
        } else { //도서가 없거나 대출중인 상태
            throw new Exception("검색한 책은 대출이 불가합니다."); //주석4: 예외 처리
        }
    }

    @Override //제목으로 도서 반납
    public void returnBook(String title) throws Exception {
        Book book = searchBookByTitle(title);
        if (book != null && book.isBorrowed()) { //도서가 대출중 상태이면
            book.returnBook(); //반납 상태로 변경(=대출가능)_
        } else { //도서가 이미 반납된 상태
            throw new Exception("이미 반납된 도서입니다."); //주석4: 예외 처리
        }
    }

    @Override //등록한 도서들을 파일에 저장
    public void saveBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Temp\\listofbooks.dat"))) { //주석8: 파일 출력 스트림
            oos.writeObject(list);
        } catch (IOException e) {
            System.err.println("파일에 저장하는 중 오류 발생.: " + e.getMessage());
        }
    }

    @Override //파일에서 도서 목록 불러오기
    public void loadBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Temp\\listofbooks.dat"))) { //주석8: 파일 입력 스트림
            list = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("파일을 찾을 수 없습니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("파일에서 로드중 오류 발생: " + e.getMessage());
        }
    }
}
