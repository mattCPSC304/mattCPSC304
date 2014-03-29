package matt.database;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
	Database db;
	public DatabaseAccess() {
		db = new Database();
	}
	
	/**
	 * given a search string, returns a hardcoded two-dimensional array of objects.
	 * y'know, for simulating queries.
	 */
	public Object[][] getData(String searchString) { //TODO: make this return something not-hardcoded
    	Object[][] data = {
    			{new Integer(5), "Kathy", "Smith", "Snowboarding", new Boolean(false), "ipsum", "lorum"},
    			{new Integer(3), "John", "Doe",	"Rowing", new Boolean(true), "ipsum", "lorum"},
    			{new Integer(2), "Sue", "Black", "Knitting", new Boolean(false), "ipsum", "lorum"},
    			{new Integer(20), "Jane", "White", "Speed reading", new Boolean(true), "ipsum", "lorum"},
    			{new Integer(10), "Joe", "Brown", "Pool", new Boolean(false), "ipsum", "lorum"}
    			};
    	return data;
    }
	
	public Object[][] getBooks(String searchString) { //a simple search through the books for titles
    	List<Book> books = new ArrayList<Book>();
    	books = db.search(searchString, "title");
    	Object[][] data = new Object[books.size()][6];
    	System.out.println("getbooks on title string: " + searchString + " found " + books.size() + " books."); //TODO: REMOVE DEBUG CODE
    	for (int i = 0; i < books.size(); i++) {
    		Object[] row = new Object[6];
    		Book book = books.get(i);
    		row[0] = book.callNumber;
    		row[1] = book.isbn;
    		row[2] = book.title;
    		row[3] = book.mainAuthor;
    		row[4] = book.publisher;
    		row[5] = book.year;
    		data[i] = row;
    	}
    	return data;
    }
	
	public void addBook(String callNumber, String isbn, String title,
			String mainAuthor, String publisher, String year) { // a simple addition of a book by fields
		db.insertBook(callNumber, isbn, title, mainAuthor, publisher, year);
		System.out.println("book added: isbn: " + isbn); //TODO: REMOVE DEBUG CODE
	}
	
	public void initDb() {
		db.importTables();
	}
	
	public void purgeDb() {
		db.deleteTables();
	}
}
