package test01;

public interface BookManagement { //주석3: 인터페이스
	void addBook(Book book);
    Book searchBookByTitle(String title); //제목으로 검색 후 대출 가능 여부 파악
    void borrowBook(String title) throws Exception; //주석4: 예외 처리  //제목을 기준으로 대출 
    void returnBook(String title) throws Exception; //주석4: 예외 처리  //제목을 기준으로 반납 
    void saveBooksToFile(); //도서의 정보를 입력해서 파일에 저장
    void loadBooksFromFile(); //도서 목록을 불러옴
}
