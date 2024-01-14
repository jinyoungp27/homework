package test01;

public class BookStorage {
	public static void main(String[] args) {
		Library library = new Library(new BookManagementService());
		library.showMenu();
	}
}
