package matt.database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseAccess {
	Database db;
	public DatabaseAccess() {
		db = new Database();
	}
	
	public void initDb() {
		db.importTables();
	}
	
	public void purgeDb() {
		db.deleteTables();
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
	
	@SuppressWarnings("deprecation")
	public void addBorrower(String bid, String password, String name, //simple addition of borrower by fields
			String address, String phone, String emailAddress,
			String sinOrStNo, String expiryDateYear, String expiryDateMonth, String expiryDateDay, String type) {
		java.sql.Date sqlExpiryDate = new java.sql.Date(Integer.parseInt(expiryDateYear), Integer.parseInt(expiryDateMonth), Integer.parseInt(expiryDateDay));
		db.insertBorrower((int) Integer.parseInt(bid), password, name, address, phone, emailAddress,
				sinOrStNo, sqlExpiryDate, type);
		System.out.println("adding borrower: " + bid + ", " + password + ", " + name + ", " + address + ", " + phone + ", " + emailAddress + ", " + sinOrStNo + ", " + sqlExpiryDate.getYear() + ", " + sqlExpiryDate.getMonth() + ", " + sqlExpiryDate.getDay() + ", " + type); //TODO: REMOVE DEBUG CODE
	}
	
	public void checkout(String bid, String callNumbers) {
		db.checkoutItems((int) Integer.parseInt(bid), callNumbers.split(","));
		System.out.println("checking out books: " + bid + ", " + callNumbers);
	}
	
	public void processReturn(String borid) {
		db.processReturn((int) Integer.parseInt(borid));
		System.out.println("returning books from borrowing id: " + borid);
	}
	
	public Object[][] checkOverdue(){
		List<Borrowing> overdue = new ArrayList<Borrowing>();
		overdue = db.checkOverdueItems();
		Object[][] data = new Object[overdue.size()][6];
		System.out.println("checking overdue items"); //TODO: REMOVE DEBUG CODE
		for (int i = 0; i < overdue.size(); i++) {
			Object[] row = new Object[7];
			Borrowing b = overdue.get(i);
			row[0] = b.borid;
			row[1] = b.bid;
			row[2] = b.email;
			row[3] = b.callNumber;
			row[4] = b.copyNo;
			row[5] = b.outDate;
			row[6] = b.dueDate;
			data[i] = row;
		}
		return data;
	}
	
	public Object[][] getBooks(String title, String author, String subject) {
    	List<Book> books = new ArrayList<Book>();
    	books = db.search(title, author, subject);
    	Object[][] data = new Object[books.size()][6];
    	System.out.println("a search for books with title, author, subject: [" + title + ", " + author + ", " + subject + "] found " + books.size() + " books."); //TODO: REMOVE DEBUG CODE
    	for (int i = 0; i < books.size(); i++) {
    		Object[] row = new Object[10];
    		Book book = books.get(i);
    		row[0] = book.callNumber;
    		row[1] = book.isbn;
    		row[2] = book.title;
    		row[3] = book.mainAuthor;
    		row[4] = book.publisher;
    		row[5] = book.year;
    		row[6] = Arrays.deepToString(book.secondaryAuthors.toArray()); //TODO: properly regex the square brackets off
    		row[7] = Arrays.deepToString(book.subjects.toArray());
    		row[8] = book.in;
    		row[9] = book.out;
    		data[i] = row;
    	}
    	return data;
    }
	
	public void payFine(String fid) {
		db.payFine(Integer.parseInt(fid));
	}
	
	public Object[][] getFines(String bid) {
		List<Fine> fines = new ArrayList<Fine>();
		fines = db.getFines(Integer.parseInt(bid));
		Object[][]data = new Object[fines.size()][6];
		for (int i = 0; i < fines.size(); i++) {
    		Object[] row = new Object[3];
    		Fine fine = fines.get(i);
    		row[0] = fine.fid;
    		row[1] = fine.amount;
    		row[2] = fine.issuedDate;
    		data[i] = row;
    	}
		return data;
	}
	
	public Object[][] getCheckedOut(String bid) {
		List<BorrowedBook> books = new ArrayList<BorrowedBook>();
		books = db.getBorrowedBooks(Integer.parseInt(bid));
		System.out.println("checking " + bid + "'s borrowed books. # found: " + books.size()); //TODO: REMOVE DEBUG CODE
		Object[][]data = new Object[books.size()][6];
		for (int i = 0; i < books.size(); i++) {
    		Object[] row = new Object[4];
    		BorrowedBook book = books.get(i);
    		row[0] = book.callNumber;
    		row[1] = book.isbn;
    		row[2] = book.title;
    		row[3] = book.mainAuthor;
    		data[i] = row;
    	}
		return data;
	}
	
	public Object[][] getHoldRequests(String bid) {
		List<HoldRequest> holdRequests = new ArrayList<HoldRequest>();
		holdRequests = db.getHoldRequests(Integer.parseInt(bid));
		Object[][]data = new Object[holdRequests.size()][3];
		for (int i = 0; i < holdRequests.size(); i++) {
    		Object[] row = new Object[10];
    		HoldRequest holdRequest = holdRequests.get(i);
    		row[0] = holdRequest.callNumber;
    		row[1] = holdRequest.issuedDate;
    		//row[2] = holdRequest.hid;
    		data[i] = row;
    	}
		return data;
	}
	
	public void holdRequest(String bid, String callNumber, Date issuedDate) {
		db.placeHoldRequest((int) Integer.parseInt(bid), callNumber, issuedDate);
		System.out.println("hold request placed for borrower" + bid + " on book " + callNumber);
	}
	
	public void addBook(String callNumber, String isbn, String title,
			String mainAuthor, String publisher, String year, String[] secondaryAuthors, String[] subjects) { // a simple addition of a book by fields
		db.insertBook(callNumber, isbn, title, mainAuthor, publisher, year, secondaryAuthors, subjects);
		System.out.println("book added: isbn: " + isbn); //TODO: REMOVE DEBUG CODE
	}
	
	public void addBookCopy(String callNumber, String copyNo, String status) { // a simple addition of a book copy by fields
		db.insertBookCopy(callNumber, copyNo, status);
		System.out.println("book copy added: callNumber: " + callNumber); //TODO: REMOVE DEBUG CODE
	}
	
	public Object[][] bookReport() { 
    	List<BookReportItem> books = new ArrayList<BookReportItem>();
    	books = db.outBookReport();
    	Object[][] data = new Object[books.size()][7];
    	System.out.println("book report no subject: " + " found " + books.size() + " books."); //TODO: REMOVE DEBUG CODE
    	for (int i = 0; i < books.size(); i++) {
    		Object[] row = new Object[7];
    		BookReportItem book = books.get(i);
    		row[0] = book.callNumber;
    		row[1] = book.copyNo;
    		row[2] = book.title;
    		row[3] = book.subject;
    		row[4] = book.outDate;
    		row[5] = book.dueDate;
    		row[6] = book.overdue;
    		data[i] = row;
    	}
    	return data;
    }
	
	public Object[][] bookReport(String subject) { 
    	List<BookReportItem> books = new ArrayList<BookReportItem>();
    	books = db.outBookReport(subject);
    	Object[][] data = new Object[books.size()][7];
    	System.out.println("book report on subject: " + subject + " found " + books.size() + " books."); //TODO: REMOVE DEBUG CODE
    	for (int i = 0; i < books.size(); i++) {
    		Object[] row = new Object[7];
    		BookReportItem book = books.get(i);
    		row[0] = book.callNumber;
    		row[1] = book.copyNo;
    		row[2] = book.title;
    		row[3] = book.subject;
    		row[4] = book.outDate;
    		row[5] = book.dueDate;
    		row[6] = book.overdue;
    		data[i] = row;
    	}
    	return data;
    }
	
	public Object[][] getPopularItems(String year, String n) { //TODO: PROPERLY SEARCH FOR AUTHOR AND SUBJECT
    	List<Book> books = new ArrayList<Book>();
    	books = db.popularItems((int) Integer.parseInt(year), (int) Integer.parseInt(n));
    	Object[][] data = new Object[books.size()][6];
    	System.out.println("searching " + n + " popular items borrowed in year: " + year); //TODO: REMOVE DEBUG CODE
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

}
