package matt.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

	private Connection con;
	int borid = 0;

	public Database() {
		loadJDBCDriver();
		connect("ora_t6e7", "a62970082");
		importTables();
		deleteTables();
	}

	public void loadJDBCDriver() {
		try {
			// Load the Oracle JDBC driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
			System.exit(-1);
		}
	}

	/*
	 * connects to Oracle database named ug using user supplied username and
	 * password
	 */
	private boolean connect(String username, String password) {
		String connectURL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";

		try {
			con = DriverManager.getConnection(connectURL, username, password);

			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
			return false;
		}
	}

	private void importTables() {
		String sql;
		try {
			Statement stmt = con.createStatement();
			
			// BORROWER
			// Borrower (bid, password, name, address, phone, emailAddress,
			// sinOrStNo, expiryDate, type)
			sql = "CREATE TABLE BORROWER " + "(bid INTEGER not NULL, "
					+ " password VARCHAR(32) not NULL, "
					+ " name VARCHAR(32) not NULL, "
					+ " address VARCHAR(64) not NULL, "
					+ " phone VARCHAR(32) not NULL, "
					+ " emailAddress VARCHAR(64) not NULL, "
					+ " sinOrStNo VARCHAR(32) not NULL, "
					+ " expiryDate date not NULL, "
					+ " type VARCHAR(32) not NULL, " + " PRIMARY KEY ( bid ))";
			stmt.executeUpdate(sql);
			System.out.println("Table created.");

			// BORROWERTYPE
			// BorrowerType (type, bookTimeLimit)
			sql = "CREATE TABLE BORROWERTYPE " + "(type VARCHAR(32) not NULL, "
					+ " bookTimeLimit long not NULL, "
					+ " PRIMARY KEY ( type ))";
			stmt.executeUpdate(sql);
			System.out.println("Table created.");

			// BOOK
			// Book (callNumber, isbn, title, mainAuthor, publisher, year )
			sql = "CREATE TABLE BOOK " + "(callNumber VARCHAR(32) not NULL, "
					+ " isbn VARCHAR(32) not NULL, "
					+ " title VARCHAR(32) not NULL, "
					+ " mainAuthor VARCHAR(32) not NULL, "
					+ " publisher VARCHAR(32) not NULL, "
					+ " year VARCHAR(32) not NULL, "
					+ " PRIMARY KEY ( callNumber ))";
			stmt.executeUpdate(sql);
			System.out.println("Table created.");

			// HASAUTHOR
			// HasAuthor (callNumber, name)
			sql = "CREATE TABLE HASAUTHOR "
					+ "(callNumber VARCHAR(32) not NULL, "
					+ " name VARCHAR(32) not NULL, "
					+ " PRIMARY KEY ( callNumber, name ))";
			stmt.executeUpdate(sql);
			System.out.println("Table created.");

			// HASSUBJECT
			// HasSubject (callNumber, subject)
			sql = "CREATE TABLE HASSUBJECT "
					+ "(callNumber VARCHAR(32) not NULL, "
					+ " subject VARCHAR(32) not NULL, "
					+ " PRIMARY KEY ( callNumber, subject ))";
			stmt.executeUpdate(sql);
			System.out.println("Table created.");

			// BOOKCOPY
			// BookCopy (callNumber, copyNo, status)
			sql = "CREATE TABLE BOOKCOPY "
					+ "(callNumber VARCHAR(32) not NULL, "
					+ " copyNo VARCHAR(32) not NULL, "
					+ " status VARCHAR(32) not NULL, "
					+ " PRIMARY KEY ( callNumber, copyNo ))";
			stmt.executeUpdate(sql);
			System.out.println("Table created.");

			// HOLDREQUEST
			// HoldRequest(hid, bid, callNumber, issuedDate)
			sql = "CREATE TABLE HOLDREQUEST " + "(hid INTEGER not NULL, "
					+ " bid INTEGER not NULL, "
					+ " callNumber VARCHAR(32) not NULL, "
					+ " issueDate date not NULL, " + " PRIMARY KEY ( hid ))";
			stmt.executeUpdate(sql);
			System.out.println("Table created.");

			// BORROWING
			// Borrowing(borid, bid, callNumber, copyNo, outDate, inDate)
			sql = "CREATE TABLE BORROWING " + "(borid INTEGER not NULL, "
					+ " bid INTEGER not NULL, "
					+ " callNumber VARCHAR(32) not NULL, "
					+ " copyNo VARCHAR(32) not NULL, "
					+ " outDate date not NULL, " + " inDate date not NULL, "
					+ " PRIMARY KEY ( borid ))";
			stmt.executeUpdate(sql);
			System.out.println("Table created.");

			stmt.execute("CREATE SEQUENCE borid_counter");
			displayMessage("borid_counter initiated");

			// FINE
			// Fine (fid, amount, issuedDate, paidDate, borid)
			sql = "CREATE TABLE FINE " + "(fid INTEGER not NULL, "
					+ " amount VARCHAR(32) not NULL, "
					+ " issuedDate date not NULL, "
					+ " paidDate date not NULL, " + " borid INTEGER not NULL, "
					+ " PRIMARY KEY ( fid ))";
			stmt.executeUpdate(sql);
			System.out.println("Table created.");

			stmt.execute("CREATE SEQUENCE fid_counter");
			displayMessage("fid_counter initiated");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteTables() {
		String sql;
		try {
			Statement stmt = con.createStatement();
			sql = "DROP TABLE BORROWER";
			stmt.execute(sql);
			System.out.println("Tables dropped.");
			sql = "DROP TABLE BORROWERTYPE";
			stmt.execute(sql);
			System.out.println("Tables dropped.");
			sql = "DROP TABLE BOOK";
			stmt.execute(sql);
			System.out.println("Tables dropped.");
			sql = "DROP TABLE HASAUTHOR";
			stmt.execute(sql);
			System.out.println("Tables dropped.");
			sql = "DROP TABLE HASSUBJECT";
			stmt.execute(sql);
			System.out.println("Tables dropped.");
			sql = "DROP TABLE BOOKCOPY";
			stmt.execute(sql);
			System.out.println("Tables dropped.");
			sql = "DROP TABLE HOLDREQUEST";
			stmt.execute(sql);
			System.out.println("Tables dropped.");
			sql = "DROP TABLE BORROWING";
			stmt.execute(sql);
			System.out.println("Tables dropped.");
			stmt.execute("DROP SEQUENCE borid_counter");
			System.out.println("Sequence dropped");
			sql = "DROP TABLE FINE";
			stmt.execute(sql);
			System.out.println("Tables dropped.");
			stmt.execute("DROP SEQUENCE fid_counter");
			System.out.println("Sequence dropped");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// BORROWER
	// Borrower (bid, password, name, address, phone, emailAddress, sinOrStNo,
	// expiryDate, type)
	public void insertBorrower(int bid, String password, String name,
			String address, String phone, String sinOrStNo, Date expiryDate,
			String type) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO BORROWER VALUES (?,?,?,?,?,?,?,?)");
			ps.setInt(1, bid);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, address);
			ps.setString(5, phone);
			ps.setString(6, sinOrStNo);
			ps.setDate(7, expiryDate);
			ps.setString(8, type);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// BORROWERTYPE
	// BorrowerType (type, bookTimeLimit)
	public void insertBorrowerType(String type, String bookTimeLimit) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO BORROWERTYPE VALUES (?,?)");
			ps.setString(1, type);
			ps.setString(2, bookTimeLimit);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// BOOK
	// Book (callNumber, isbn, title, mainAuthor, publisher, year )
	public void insertBook(String callNumber, String isbn, String title,
			String mainAuthor, String publisher, String year) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO BOOK VALUES (?,?,?,?,?,?)");
			ps.setString(1, callNumber);
			ps.setString(2, isbn);
			ps.setString(3, title);
			ps.setString(4, mainAuthor);
			ps.setString(5, publisher);
			ps.setString(6, year);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// HASAUTHOR
	// HasAuthor (callNumber, name)
	public void insertHasAuthor(String callNumber, String name) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO HASAUTHOR VALUES (?,?)");
			ps.setString(1, callNumber);
			ps.setString(2, name);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// HASSUBJECT
	// HasSubject (callNumber, subject)
	public void insertHasSubject(String callNumber, String subject) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO HASSUBJECT VALUES (?,?)");
			ps.setString(1, callNumber);
			ps.setString(2, subject);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// BOOKCOPY
	// BookCopy (callNumber, copyNo, status)
	public void insertBookCopy(String callNumber, String copyNo, String status) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO BOOKCOPY VALUES (?,?,?)");
			ps.setString(1, callNumber);
			ps.setString(2, copyNo);
			ps.setString(3, status);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// HOLDREQUEST
	// HoldRequest(hid, bid, callNumber, issuedDate)
	public void insertHoldRequest(int hid, int bid, String callNumber,
			Date issuedDate) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO HOLDREQUEST VALUES (?,?,?,?)");
			ps.setInt(1, hid);
			ps.setInt(2, bid);
			ps.setString(3, callNumber);
			ps.setDate(4, issuedDate);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// BORROWING
	// Borrowing(borid, bid, callNumber, copyNo, outDate, inDate)
	public void insertBorrowing(int borid, int bid, String callNumber,
			String copyNo, Date outDate, Date inDate) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO BORROWING VALUES (?,?,?,?,?,?)");
			ps.setInt(1, borid);
			ps.setInt(2, bid);
			ps.setString(3, callNumber);
			ps.setString(4, copyNo);
			ps.setDate(5, outDate);
			ps.setDate(6, inDate);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// FINE
	// Fine (fid, amount, issuedDate, paidDate, borid)
	public void insertFINE(int fid, String amount, Date issuedDate,
			Date paidDate, int borid) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO FINE VALUES (?,?,?,?,?)");
			ps.setInt(1, fid);
			ps.setString(2, amount);
			ps.setDate(3, issuedDate);
			ps.setDate(4, paidDate);
			ps.setInt(5, borid);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Borrower (bid, password, name, address, phone, emailAddress, sinOrStNo,
	// expiryDate, type)
	public void deleteBorrower(int bid) {

	}

	// BorrowerType (type, bookTimeLimit)
	public void deleteBorrowerType(String type) {

	}

	// Book (callNumber, isbn, title, mainAuthor, publisher, year )
	public void deleteBook(String callNumber) {

	}

	// HasAuthor (callNumber, name)
	public void deleteHasAuthor(String callNumber, String name) {

	}

	// HasSubject (callNumber, subject)
	public void deleteHasSubject(String callNumber, String subject) {

	}

	// BookCopy (callNumber, copyNo, status)
	public void deleteBookCopy(String callNumber, String copyNo) {

	}

	// HoldRequest(hid, bid, callNumber, issuedDate)
	public void deleteHoldRequest(int hid) {

	}

	// Borrowing(borid, bid, callNumber, copyNo, outDate, inDate)
	public void deleteBorrowing(int borid) {

	}

	// Fine (fid, amount, issuedDate, paidDate, borid)
	public void deleteFine(int fid) {

	}

	// Borrower (bid, password, name, address, phone, emailAddress, sinOrStNo,
	// expiryDate, type)
	// BorrowerType (type, bookTimeLimit)
	// Book (callNumber, isbn, title, mainAuthor, publisher, year )
	// HasAuthor (callNumber, name)
	// HasSubject (callNumber, subject)
	// BookCopy (callNumber, copyNo, status)
	// HoldRequest(hid, bid, callNumber, issuedDate)
	// Borrowing(borid, bid, callNumber, copyNo, outDate, inDate)
	// Fine (fid, amount, issuedDate, paidDate, borid)

	// transactions performed by a clerk
	// Add a new borrower -> see insertBorrower(...) and
	// insertBorrowerType(...);
	// Checkout items
	public String[][] checkoutItems(int bid, String[] callNumbers) {

		Statement stmt;
		ResultSet rs;
		PreparedStatement ps;
		// String sql;
		String[][] note = new String[callNumbers.length][2];
		try {
			stmt = con.createStatement();
			// check bid is valid
			rs = stmt.executeQuery("SELECT * FROM BORROWER WHERE bid="
					+ String.valueOf(bid));
			// ResultSetMetaData rsmd = rs.getMetaData();
			if (rs.next() == false) {
				displayMessage("invalid bid.");
				note[0][0] = "invalid bid.";
				return note;
			}
			String borrowerType = rs.getString("type");
			rs = stmt.executeQuery("SELECT * FROM BORROWERTYPE WHERE type="
					+ borrowerType);
			long bookTimeLimit = rs.getLong("bookTimeLimit");
			for (int i = 0; i < callNumbers.length; i++) {
				// check items available for borrowing
				rs = stmt
						.executeQuery("SELECT * FROM BOOKCOPY WHERE callNumber="
								+ callNumbers[i] + " AND status=in");
				if (rs.next() == false) {
					displayMessage("The callNumber " + callNumbers[i]
							+ " does not have copies in database");
					note[i][0] = callNumbers[i];
					note[i][1] = "Item Not available";
				} else {
					// set bookCopy status to "out"
					String copyNo = rs.getString("copyNo");
					stmt.executeUpdate("UPDATE BOOKCOPY SET status=out WHERE callNumber="
							+ callNumbers[i] + " AND copyNo=" + copyNo);
					// create borrowing tuples
					java.util.Date javaDate = new java.util.Date();
					Date now = new Date(javaDate.getTime());
					Date expiry = new Date(javaDate.getTime() + bookTimeLimit);
					// insertBorrowing(getBorid(), bid, callNumbers[i], copyNo,
					// now, expiry);
					ps = con.prepareStatement("INSERT INTO BORROWING VALUES (borid_counter.nextval,?,?,?,?,?)");
					ps.setInt(1, bid);
					ps.setString(2, callNumbers[i]);
					ps.setString(3, copyNo);
					ps.setDate(4, now);
					ps.setDate(5, expiry);
					ps.executeUpdate();
					// rs=stmt.executeQuery("SELECT borid FROM ");
					// prepare note
					note[i][0] = callNumbers[i];
					note[i][1] = expiry.toString();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return a note with items and due date
		return note;
	}

	public void processReturn(int bid, String callNumber, String copyNo,
			int borid) {
		Statement stmt;
		ResultSet rs;
		PreparedStatement ps;
		try {
			stmt = con.createStatement();
			// set BOOKCOPY.status="in"
			ps = con.prepareStatement("UPDATE BOOKCOPY SET status=in WHERE callNumber=? AND copyNo=?");
			ps.setString(1, callNumber);
			ps.setString(2, copyNo);
			ps.executeUpdate();
			// check to see if the item is overdue. If overdue, Insert a fine
			// tuple.
			// rs=stmt.executeQuery("SELECT * FROM BORROWING where borid=");
			ps = con.prepareStatement("SELECT * FROM BORROWING where borid=?");
			ps.setInt(1, borid);
			rs = ps.executeQuery();
			rs.next();
			if (rs.getDate("exipry").before(
					new Date(new java.util.Date().getTime()))) {
				// item is overdue
				ps = con.prepareStatement("INSERT INTO FINE VALUES (fid_counter.nextval,?,?,?,?");
				ps.setString(1, "100");
				java.util.Date javaDate = new java.util.Date();
				Date now = new Date(javaDate.getTime());
				ps.setDate(2, now);
				ps.setDate(3, new Date(0));
				ps.setInt(4, borid);
				ps.executeUpdate();
			}
			// check for holdRequest. If the item is requested, set status to
			// "onHold".
			ps = con.prepareStatement("SELECT * FROM HOLDREQUEST WHERE callNumber=?");
			ps.setString(1, callNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				int bid_waiter = rs.getInt("bid");
				// TODO: notify the guy that his reservation is ready
			}
			stmt.close();
			ps.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> checkOverdueItems() {
		List<String> overdue = new ArrayList<String>();
		java.util.Date javaDate = new java.util.Date();
		Date now = new Date(javaDate.getTime());
		Statement stmt;
		ResultSet rs;
		PreparedStatement ps;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM BORROWING");
			while (rs.next()) {
				if (rs.getDate("inDate").before(now)) {
					overdue.add("bid: " + String.valueOf(rs.getInt("bid")));
					overdue.add("callNumber: " + rs.getString("callNumber"));
					overdue.add("copyNo: " + rs.getString("copyNo"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return overdue;
	}

	public List<Book> search(String keyword, String type) {
		Statement stmt;
		ResultSet rs;
		List<Book> books = new ArrayList<Book>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM BOOK WHERE " + type
					+ " LIKE '%" + keyword + "%' ");
			while (rs.next()) {
				Book book = new Book();
				book.callNumber = rs.getString("callNumber");
				book.isbn = rs.getString("isbn");
				book.mainAuthor = rs.getString("mainAuthor");
				book.publisher = rs.getString("publisher");
				book.title = rs.getString("title");
				book.year = rs.getString("year");
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	// Borrower (bid, password, name, address, phone, emailAddress, sinOrStNo,
	// expiryDate, type)
	// BorrowerType (type, bookTimeLimit)
	// Book (callNumber, isbn, title, mainAuthor, publisher, year )
	// HasAuthor (callNumber, name)
	// HasSubject (callNumber, subject)
	// BookCopy (callNumber, copyNo, status)
	// HoldRequest(hid, bid, callNumber, issuedDate)
	// Borrowing(borid, bid, callNumber, copyNo, outDate, inDate)
	// Fine (fid, amount, issuedDate, paidDate, borid)

	private int getBorid() {
		borid++;
		return borid;
	}

	// to be rewritten for GUI
	public void displayMessage(String msg) {
		System.out.println(msg);
	}

	public static void main(String args[]) {
		Database d = new Database();
	}

}
