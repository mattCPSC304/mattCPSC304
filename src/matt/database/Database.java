package matt.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import matt.ui.Main;

public class Database {

	private Connection con;
	int borid = 0;

	public Database() {
		loadJDBCDriver();
		// connect("ora_t6e7", "a62970082"); //Lu's
		connect("ora_o3s7", "a82417106"); //Matt's
		// connect("ora_h7a8", "a29146115"); //Michael's
		// connect(<ora_c#c#>, <a########>); //Alborz's
		// connect(helpful remember: use your own database!);
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

	public void importTables() {
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
			sql = "INSERT INTO BORROWER(bid, password, name, address, phone, emailAddress, sinOrStNo, expiryDate, type) " +
					"VALUES (1,'password','John Doe','1111 One St.','604-111-1111','john@gmail.com','12345678',TO_DATE('20130515', 'YYYYMMDD'),'student')";
			stmt.executeUpdate(sql); //TODO: REMOVE DEBUG ENTRIES
			sql = "INSERT INTO BORROWER(bid, password, name, address, phone, emailAddress, sinOrStNo, expiryDate, type) " +
					"VALUES (2,'password','John Late','1111 One St.','604-111-1111','late@gmail.com','12345678',TO_DATE('20130515', 'YYYYMMDD'),'late')";
			stmt.executeUpdate(sql); //TODO: REMOVE DEBUG ENTRIES
			System.out.println("Table BORROWER created.");

			// BORROWERTYPE
			// BorrowerType (type, bookTimeLimit)
			sql = "CREATE TABLE BORROWERTYPE " + "(type VARCHAR(32) not NULL, "
					+ " bookTimeLimit long not NULL, "
					+ " PRIMARY KEY ( type ))";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO BORROWERTYPE(bookTimeLimit, type) " +
					"VALUES (1209600000, 'student')"; // dates are in milliseconds since 1970. so 14 days in ms is
														// 14 days * 24 hours/day * 60 minutes/hour * 60 seconds/minute * 1000ms/second = 1209600000 //TODO: ADD THIS
			stmt.executeUpdate(sql);
			sql = "INSERT INTO BORROWERTYPE(bookTimeLimit, type) " +
					"VALUES (7257600000, 'faculty')"; 
			stmt.executeUpdate(sql);
			sql = "INSERT INTO BORROWERTYPE(bookTimeLimit, type) " +
					"VALUES (3628800000, 'staff')"; 
			stmt.executeUpdate(sql);
			sql = "INSERT INTO BORROWERTYPE(bookTimeLimit, type) " +
					"VALUES (10, 'late')"; // for illustrating late handling: this borrower type is essentially always late
			stmt.executeUpdate(sql);
			System.out.println("Table BORROWERTYPE created.");

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
			sql = "INSERT INTO BOOK(callNumber, isbn, title, mainAuthor, publisher, year) " +
					"VALUES ('1','1','1','1','1','1')";
			stmt.executeUpdate(sql); //TODO: REMOVE DEBUG ENTRIES
			System.out.println("Table BOOK created.");

			// HASAUTHOR
			// HasAuthor (callNumber, name)
			sql = "CREATE TABLE HASAUTHOR "
					+ "(callNumber VARCHAR(32) not NULL, "
					+ " name VARCHAR(32) not NULL, "
					+ " PRIMARY KEY ( callNumber, name ))";
			stmt.executeUpdate(sql);
			System.out.println("Table HASAUTHOR created.");

			// HASSUBJECT
			// HasSubject (callNumber, subject)
			sql = "CREATE TABLE HASSUBJECT "
					+ "(callNumber VARCHAR(32) not NULL, "
					+ " subject VARCHAR(32) not NULL, "
					+ " PRIMARY KEY ( callNumber, subject ))";
			stmt.executeUpdate(sql);
			System.out.println("Table HASSUBJECT created.");

			// BOOKCOPY
			// BookCopy (callNumber, copyNo, status)
			sql = "CREATE TABLE BOOKCOPY "
					+ "(callNumber VARCHAR(32) not NULL, "
					+ " copyNo VARCHAR(32) not NULL, "
					+ " status VARCHAR(32) not NULL, "
					+ " PRIMARY KEY ( callNumber, copyNo ))";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO BOOKCOPY(callNumber, copyNo, status) " +
					"VALUES ('1','1','in')"; //TODO: REMOVE DEBUG ENTRIES
			stmt.executeUpdate(sql);
			System.out.println("Table BOOKCOPY created.");

			// HOLDREQUEST
			// HoldRequest(hid, bid, callNumber, issuedDate)
			sql = "CREATE TABLE HOLDREQUEST " + "(hid INTEGER not NULL, "
					+ " bid INTEGER not NULL, "
					+ " callNumber VARCHAR(32) not NULL, "
					+ " issueDate date not NULL, " + " PRIMARY KEY ( hid ))";
			stmt.executeUpdate(sql);
			System.out.println("Table HOLDREQUEST created.");

			stmt.execute("CREATE SEQUENCE hid_counter");
			System.out.println("hid_counter initiated");

			// BORROWING
			// Borrowing(borid, bid, callNumber, copyNo, outDate, inDate)
			sql = "CREATE TABLE BORROWING " + "(borid INTEGER not NULL, "
					+ " bid INTEGER not NULL, "
					+ " callNumber VARCHAR(32) not NULL, "
					+ " copyNo VARCHAR(32) not NULL, "
					+ " outDate date not NULL, " + " inDate date not NULL, "
					+ " PRIMARY KEY ( borid ))";
			stmt.executeUpdate(sql);
			System.out.println("Table BORROWING created.");

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
			System.out.println("Table FINE created.");

			stmt.execute("CREATE SEQUENCE fid_counter");
			displayMessage("fid_counter initiated");

		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
	}

	public void deleteTables() {
		String sql;
		try {
			Statement stmt = con.createStatement();
			sql = "DROP TABLE BORROWER";
			stmt.execute(sql);
			System.out.println("Table BORROWER dropped.");
			
			sql = "DROP TABLE BORROWERTYPE";
			stmt.execute(sql);
			System.out.println("Tables BORROWERTYPE dropped.");
			
			sql = "DROP TABLE BOOK";
			stmt.execute(sql);
			System.out.println("Tables BOOK dropped.");
			
			sql = "DROP TABLE HASAUTHOR";
			stmt.execute(sql);
			System.out.println("Tables HASAUTHOR dropped.");
			
			sql = "DROP TABLE HASSUBJECT";
			stmt.execute(sql);
			System.out.println("Tables HASSUBJECT dropped.");
			
			sql = "DROP TABLE BOOKCOPY";
			stmt.execute(sql);
			System.out.println("Tables BOOKCOPY dropped.");
			
			sql = "DROP TABLE HOLDREQUEST";
			stmt.execute(sql);
			System.out.println("Tables HOLDREQUEST dropped.");
			
			sql = "DROP SEQUENCE hid_counter";
			stmt.execute(sql);
			System.out.println("Sequence hid_counter dropped");
			
			sql = "DROP TABLE BORROWING";
			stmt.execute(sql);
			System.out.println("Tables TABLE dropped.");
			
			sql = "DROP SEQUENCE borid_counter";
			stmt.execute(sql);
			System.out.println("Sequence borid_counter dropped");
			
			sql = "DROP TABLE FINE";
			stmt.execute(sql);
			System.out.println("Tables TABLE dropped.");
			
			sql = "DROP SEQUENCE fid_counter";
			stmt.execute(sql);
			System.out.println("Sequence fid_counter dropped");
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
	}

	// BORROWER
	// Borrower (bid, password, name, address, phone, emailAddress, sinOrStNo,
	// expiryDate, type)
	public void insertBorrower(int bid, String password, String name,
			String address, String phone, String email, String sinOrStNo, Date expiryDate,
			String type) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO BORROWER VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, bid);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, address);
			ps.setString(5, phone);
			ps.setString(6, email);
			ps.setString(7, sinOrStNo);
			ps.setDate(8, expiryDate);
			ps.setString(9, type);
			ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
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
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
	}

	// BOOK
	// Book (callNumber, isbn, title, mainAuthor, publisher, year )
	public void insertBook(String callNumber, String isbn, String title,
			String mainAuthor, String publisher, String year, String[] secondaryAuthors, String[] subjects) {
		PreparedStatement ps;
		try {
			// add the book
			System.out.println("1");
			ps = con.prepareStatement("INSERT INTO BOOK VALUES (?,?,?,?,?,?)");
			ps.setString(1, callNumber);
			ps.setString(2, isbn);
			ps.setString(3, title);
			ps.setString(4, mainAuthor);
			ps.setString(5, publisher);
			ps.setString(6, year);
			ps.executeUpdate();
			
			// add the main author
			System.out.println("2");
			ps = con.prepareStatement("INSERT INTO HASAUTHOR VALUES (?,?)");
			ps.setString(1, callNumber);
			ps.setString(2, mainAuthor);
			ps.executeUpdate();
			
			// add the secondary authors
			System.out.println("3");
			for (int i = 0; i<secondaryAuthors.length; i++) {
				// TODO: check for duplicate somehow (esp against main author)
				ps = con.prepareStatement("INSERT INTO HASAUTHOR VALUES (?,?)");
				ps.setString(1, callNumber);
				ps.setString(2, secondaryAuthors[i]);
				ps.executeUpdate();
			}
			
			// add the subjects
			System.out.println("4");
			for (int i = 0; i<subjects.length; i++) {
				// TODO: check for duplicate somehow
				ps = con.prepareStatement("INSERT INTO HASSUBJECT VALUES (?,?)");
				ps.setString(1, callNumber);
				ps.setString(2, subjects[i]);
				ps.executeUpdate();
			}
			
			System.out.println("5");
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
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
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
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
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
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
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
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
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
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
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
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
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
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
	// The following method takes care of borrower type and booktime limit by
	// adding a tuple into the BORROWERTYPE table
	// otherwise we get no tuples when we join borrower and borrowertype
	public void addBorrower(int bid, String password, String name,
			String address, String phone, String sinOrStNo, Date expiryDate,
			String type) {
		PreparedStatement ps;
		ResultSet rs;
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

			ps = con.prepareStatement("SELECT * FROM BORROWERTYPE WHERE type=?");
			ps.setString(1, type);
			rs = ps.executeQuery();
			if (rs.next()) {// type exist in the table already
				// do nothing
			} else {
				ps = con.prepareStatement("INSERT INTO BORROWERTYPE VALUES (?,?)");
				ps.setString(1, type);
				ps.setLong(2, 1000 * 60 * 60 * 24 * 7 * 2);// bookTimeLimit,
															// measured in
															// milliseconds,
															// default 2 weeks.
				ps.executeUpdate();
			}
			con.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
	}

	// Checkout items
	public List<Borrowing> checkoutItems(int bid, String[] callNumbers) {
		List<Borrowing> checkoutList = new ArrayList<Borrowing>();
		Statement stmt;
		ResultSet rs;
		PreparedStatement ps;
		try {
			stmt = con.createStatement();
			ps = con.prepareStatement("NULL");// This statement needs to be
											// initialized here to be closed at
											// the end.
			// check bid is valid
			rs = stmt.executeQuery("SELECT * FROM BORROWER WHERE bid="
					+ String.valueOf(bid));
			if (rs.next() == false) {
				displayMessage("invalid bid.");
				return checkoutList;
			}
			String borrowerType = rs.getString("type");
			rs = stmt.executeQuery("SELECT * FROM BORROWERTYPE WHERE type='"
					+ borrowerType + "'");
			if (rs.next() == false) {
				displayMessage("invalid borrower type.");
				return checkoutList;
			}
			long bookTimeLimit = rs.getLong("bookTimeLimit");
			for (int i = 0; i < callNumbers.length; i++) {
				// check items available for borrowing
				rs = stmt.executeQuery("SELECT * FROM BOOKCOPY WHERE callNumber='"
								+ callNumbers[i] + "' AND status='in'");
				if (rs.next() == false) {
					displayMessage("The callNumber " + callNumbers[i]
							+ " does not have copies in database");
					Borrowing item = new Borrowing();
					item.borid = -1;
					item.bid = bid;
					item.callNumber = callNumbers[i];
					item.copyNo = "No such callNumber, or No more copies available";
				} else {
					// set bookCopy status to "out"
					String copyNo = rs.getString("copyNo");
					stmt.executeUpdate("UPDATE BOOKCOPY SET status='out' WHERE callNumber="
							+ callNumbers[i] + " AND copyNo=" + copyNo);
					// create borrowing tuples
					java.util.Date javaDate = new java.util.Date();
					Date now = new Date(javaDate.getTime());
					Date dueDate = new Date(javaDate.getTime() + bookTimeLimit);
					ps = con.prepareStatement("INSERT INTO BORROWING VALUES (borid_counter.nextval,?,?,?,?,?)",new String[]{"BORID"});
					ps.setInt(1, bid);
					ps.setString(2, callNumbers[i]);
					ps.setString(3, copyNo);
					ps.setDate(4, now);
					ps.setDate(5, new Date(0));// inDate is unknown yet, so set
												// it to 0;
					
					ps.executeUpdate();
					rs = ps.getGeneratedKeys();
				    rs.next();
					
					Borrowing item = new Borrowing();
					item.borid = rs.getInt(1);
					item.bid = bid;
					item.callNumber = callNumbers[i];
					item.copyNo = copyNo;
					item.outDate = now;
					item.inDate = new Date(0);
					item.dueDate = dueDate;
					checkoutList.add(item);
					// prepare note
					displayMessage("borrower with id " + item.bid + " borrowed copy " + item.copyNo + " of book with call number " + item.callNumber + " and got id " + item.borid + ".");
				}
			}
			con.commit();
			rs.close();
			ps.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		// return a note with items and due date
		return checkoutList;
	}

	/*
	 * Process a return. Return -1 if on time. Return -2 if borid is invalid.
	 * Return fid of a newly created fine if item is overdue.
	 */
	public int processReturn(int borid) {
		displayMessage("process return started for borid: " + borid); //TODO: REMOVE DEBUG PRINTLN
		int fid = -1;
		Statement stmt;
		ResultSet rs;
		PreparedStatement ps;
		String callNumber;
		String copyNo;
		java.util.Date javaDate = new java.util.Date();
		Date now = new Date(javaDate.getTime());
		try {
			stmt = con.createStatement();
			// get callNumber and copyNo first
			rs = stmt.executeQuery("SELECT * FROM BORROWING WHERE borid="
					+ String.valueOf(borid));
			if (!rs.next()) {
				displayMessage("Supplied borid does not exist in the database");
				return -2;
			}
			callNumber = rs.getString("callNumber");
			copyNo = rs.getString("copyNo");
			Date outdate = rs.getDate("outDate"); //TODO: REMOVE DEBUG STATEMENT
			Date indate = rs.getDate("inDate"); //TODO: REMOVE DEBUG STATEMENT
			displayMessage("process return associates callnumber " + callNumber + " and copyNo " + copyNo + " with this borid."); //TODO: REMOVE DEBUG PRINTLN
			displayMessage("process return found outdate, indate: " + outdate + indate + "and updated indate to" + now); //TODO: REMOVE DEBUG PRINTLN
			// set inDate = now
			ps = con.prepareStatement("UPDATE BORROWING SET inDate=? WHERE borid=?");
			ps.setDate(1, now);
			ps.setInt(2, borid);
			ps.executeUpdate();
			displayMessage("process return updated this borrowing's date with " + now.toString()); //TODO: REMOVE DEBUG PRINTLN
			// set BOOKCOPY.status="in"
			ps = con.prepareStatement("UPDATE BOOKCOPY SET status='in' WHERE callNumber=? AND copyNo=?");
			ps.setString(1, callNumber);
			ps.setString(2, copyNo);
			ps.executeUpdate();
			displayMessage("process return set status on book copy to in"); //TODO: REMOVE DEBUG PRINTLN
			// check to see if the item is overdue. If overdue, Insert a fine
			// tuple.
			// rs=stmt.executeQuery("SELECT * FROM BORROWING where borid=");
			ps = con.prepareStatement("SELECT * FROM BORROWING, BORROWER, BORROWERTYPE where borid=? AND "
					+ "BORROWING.bid=BORROWER.bid AND BORROWER.type=BORROWERTYPE.type");
			ps.setInt(1, borid);
			rs = ps.executeQuery();
			rs.next();
			Date checkoutDate = rs.getDate("outDate");
			long checkoutTime = checkoutDate.getTime();
			long timeLimit = rs.getLong("bookTimeLimit");
			Date shouldaReturned = new Date(timeLimit+checkoutTime);
			Date didReturned = new Date(new java.util.Date().getTime());
			
			displayMessage("return checking for overdue: book was borrowed on " + checkoutDate + "...");
			displayMessage("by someone with time limit " + timeLimit/86400000.0 + " days...");
			displayMessage("it should have been returned on " + shouldaReturned + "...");
			displayMessage("and it was returned today (" + didReturned + ")."); //TODO: REMOVE DEBUG PRINTLNS
			
			if (shouldaReturned.before(didReturned)) {
				// item is overdue
				ps = con.prepareStatement("INSERT INTO FINE VALUES (fid_counter.nextval,?,?,?,?)",new String[]{"fid"});
				ps.setString(1, "100");
				ps.setDate(2, now);
				ps.setDate(3, new Date(0));
				ps.setInt(4, borid);
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				rs.next();
				fid = rs.getInt(1);
				displayMessage("Item was returned overdue, so $100 fine of id " + fid + " given."); //TODO: REMOVE DEBUG PRINTLNS
			}
			// check for holdRequest. If the item is requested, set status to "onHold".
			ps = con.prepareStatement("SELECT * FROM HOLDREQUEST WHERE callNumber=?"); 
			ps.setString(1, callNumber);
			rs = ps.executeQuery();
			if (rs.next()) { //TODO: check that this works when a hold request is in place
				int bid_waiter = rs.getInt("bid");
				displayMessage("Yo bid "
						+ String.valueOf(bid_waiter)
						+ ", you reserved an item with call number "
						+ callNumber + " whose copy " + copyNo + " just came in!");
				//TODO: set status to "onHold"
			}
			con.commit();
			ps.close();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return fid;
	}

	public List<Borrowing> checkOverdueItems() { //TODO: ONLY RETURN ITEMS THAT ARE OVERDUE AND OUT
		List<Borrowing> overdue = new ArrayList<Borrowing>();
		java.util.Date javaDate = new java.util.Date();
		Date now=new Date(javaDate.getTime());
		Statement stmt;
		ResultSet rs;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM BORROWING, BORROWER, BORROWERTYPE "
							+ "WHERE BORROWING.inDate <= to_date('19710101','YYYYMMDD') AND BORROWING.bid=BORROWER.bid AND BORROWER.type=BORROWERTYPE.type");
			while (rs.next()) {
				long timeLimit = rs.getLong("bookTimeLimit");
				Date outDate = rs.getDate("outDate");
				long outTime = outDate.getTime();
				Date dueDate = new Date(outTime + timeLimit);
				if (now.after(dueDate)) {
					Borrowing item = new Borrowing();
					item.borid = rs.getInt("borid");
					item.bid = rs.getInt("bid");
					item.email = rs.getString("emailAddress");
					item.callNumber = rs.getString("callNumber");
					item.copyNo = rs.getString("copyNo");
					item.outDate = outDate;
					item.dueDate = dueDate;
					overdue.add(item);
					displayMessage("overdue: bid: " + rs.getInt("borid")); //TODO: REMOVE DEBUG PRINTLNS
				} else {
					displayMessage("not overdue: bid: " + rs.getInt("borid")); //TODO: REMOVE DEBUG PRINTLNS
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return overdue;
	}

	public List<Book> search(String title, String author, String subject) { //TODO: do all of this in one query if possible
		Statement stmt;
		ResultSet rs;
		List<Book> books = new ArrayList<Book>();
		try {
			stmt = con.createStatement();
			
			//find book tuples matching search criteria
			rs = stmt.executeQuery("SELECT * FROM BOOK WHERE TITLE LIKE '%" + title + "%'" +
					"AND (CALLNUMBER IN (" +
						"SELECT CALLNUMBER FROM HASAUTHOR " +
						"WHERE BOOK.CALLNUMBER=HASAUTHOR.CALLNUMBER " +
						"AND HASAUTHOR.name LIKE '%" + author + "%')" +
						"OR '%" + author + "%'='%%')" + //unlike with title, if author or subject are blank, we have to cancel the check
					"AND (CALLNUMBER IN (" +
						"SELECT CALLNUMBER FROM HASSUBJECT " +
						"WHERE BOOK.CALLNUMBER=HASSUBJECT.CALLNUMBER " +
						"AND HASSUBJECT.subject LIKE '%" + subject + "%')" +
						"OR '%" + subject + "%'='%%')");
			
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
			
			//compute additional book data
			for (Book book: books) {
				rs = stmt.executeQuery("SELECT COUNT(*) FROM BOOK,BOOKCOPY WHERE BOOK.CALLNUMBER='"+ book.callNumber + "' AND BOOK.CALLNUMBER=BOOKCOPY.CALLNUMBER AND BOOKCOPY.STATUS='in'");
				rs.next();
				book.in = rs.getInt(1);
				
				rs = stmt.executeQuery("SELECT COUNT(*) FROM BOOK,BOOKCOPY WHERE BOOK.CALLNUMBER='" + book.callNumber + "' AND BOOK.CALLNUMBER=BOOKCOPY.CALLNUMBER AND BOOKCOPY.STATUS='out'");
				rs.next();
				book.out = rs.getInt(1);

				rs = stmt.executeQuery("SELECT NAME FROM BOOK,HASAUTHOR WHERE BOOK.CALLNUMBER='"+ book.callNumber + "' AND BOOK.CALLNUMBER=HASAUTHOR.CALLNUMBER");
				while(rs.next()) {
					book.secondaryAuthors.add(rs.getString(1));
				}
				
				rs = stmt.executeQuery("SELECT SUBJECT FROM BOOK,HASSUBJECT WHERE BOOK.CALLNUMBER='"+ book.callNumber + "' AND BOOK.CALLNUMBER=HASSUBJECT.CALLNUMBER");
				while(rs.next()) {
					book.subjects.add(rs.getString(1));
				}
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return books;
	}

	public List<Fine> getFines(int bid) {
		Statement stmt;
		ResultSet rs;
		List<Fine> fines = new ArrayList<Fine>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM FINE,BORROWING WHERE FINE.BORID=BORROWING.BORID AND FINE.PAIDDATE <= to_date('19710101','YYYYMMDD') AND BORROWING.BID=" + bid); //this filters only unpaid fines by finding fines where paid date was set to the default ~1970 value
			while (rs.next()) {
				Fine fine = new Fine();
				fine.fid = rs.getInt("fid");
				fine.amount = rs.getString("amount");
				fine.issuedDate = rs.getDate("issuedDate");
				fine.paidDate = rs.getDate("paidDate");
				fine.borid = rs.getInt("borid");
				fines.add(fine);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return fines;
	}
	
	public List<BorrowedBook> getBorrowedBooks(int bid) {
		Statement stmt;
		ResultSet rs;
		List<BorrowedBook> books = new ArrayList<BorrowedBook>();
		try {
			stmt = con.createStatement();
			//get borrowed books for borrower
			rs = stmt.executeQuery("SELECT * FROM BOOK,BORROWING WHERE BOOK.CALLNUMBER=BORROWING.CALLNUMBER AND BORROWING.INDATE <= to_date('19710101','YYYYMMDD') AND BORROWING.BID=" + bid);
			while (rs.next()) {
				BorrowedBook book = new BorrowedBook();
				book.callNumber = rs.getString("callNumber");
				book.isbn = rs.getString("isbn");
				book.mainAuthor = rs.getString("mainAuthor");
				book.publisher = rs.getString("publisher");
				book.title = rs.getString("title");
				book.year = rs.getString("year");
				book.bid = bid;
				books.add(book);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return books;
	}
	
	public List<HoldRequest> getHoldRequests(int bid) {
		Statement stmt;
		ResultSet rs;
		List<HoldRequest> holdRequests = new ArrayList<HoldRequest>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM HOLDREQUEST WHERE bid=" + bid);
			while (rs.next()) {
				HoldRequest holdRequest = new HoldRequest();
				holdRequest.hid = rs.getInt("hid");
				holdRequest.bid = rs.getInt("bid");
				holdRequest.callNumber = rs.getString("callNumber");
				holdRequests.add(holdRequest);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return holdRequests;
	}
	
	public HoldRequest placeHoldRequest(int bid, String callNumber,
			Date issuedDate) {
		HoldRequest holdRequest = new HoldRequest();
		ResultSet rs;
		ResultSet rk;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO HOLDREQUEST VALUES (hid_counter.nextval,?,?,?)",new String[]{"hid"}); //TODO: test, was never tested
			ps.setInt(1, bid);
			ps.setString(2, callNumber);
			ps.setDate(3, issuedDate);
			ps.executeUpdate();
			rs = ps.getResultSet();
			rs = ps.getGeneratedKeys();
			rs.next();
			rk = ps.getGeneratedKeys();
			holdRequest.hid = rk.getInt(1);
			holdRequest.bid = bid;
			holdRequest.callNumber = callNumber;
			holdRequest.issuedDate = issuedDate;
			con.commit();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return holdRequest;
	}

	public void payFine(int fid) {
		Statement stmt;
		PreparedStatement ps;
		java.util.Date javaDate = new java.util.Date();
		Date now = new Date(javaDate.getTime());
		try {
			stmt = con.createStatement();
			ps = con.prepareStatement("UPDATE FINE SET paidDate = ? WHERE fid = ? ");
			ps.setDate(1, now);
			ps.setInt(2, fid);
			ps.execute();
			con.commit();
			stmt.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
	}

	// AddNewBook - > See InsertBook
	public Book addBook(String callNumber, String isbn, String title,
			String mainAuthor, String publisher, String year) {
		Book book = new Book();
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
			book.callNumber = callNumber;
			book.isbn = isbn;
			book.title = title;
			book.mainAuthor = mainAuthor;
			book.publisher = publisher;
			book.year = year;
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return book;
	}

	// add a bookcopy and set status to be "in"
	public BookCopy addBookCopy(String callNumber, String copyNo) {
		BookCopy bookCopy = new BookCopy();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO BOOKCOPY VALUES (?,?,?)");
			ps.setString(1, callNumber);
			ps.setString(2, copyNo);
			ps.setString(3, "in");
			ps.executeUpdate();
			con.commit();
			ps.close();
			bookCopy.callNumber = callNumber;
			bookCopy.copyNo = copyNo;
			bookCopy.status = "in";
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return bookCopy;
	}

	public List<BookReportItem> outBookReport() {
		List<BookReportItem> report = new ArrayList<BookReportItem>();
		PreparedStatement ps;
		ResultSet rs;
		java.util.Date javaDate = new java.util.Date();
		Date now = new Date(javaDate.getTime());
		try {
			ps = con.prepareStatement("SELECT bk.callNumber, bc.copyNo, br.outDate, bk.title, bt.bookTimeLimit "
					+ "FROM BOOK bk, BOOKCOPY bc, BORROWING br, BORROWER b, BORROWERTYPE bt "
					+ "WHERE bk.callNumber=bc.callNumber AND bc.callNumber=br.callNumber AND "
					+ "b.type=bt.type AND br.bid=b.bid AND "
					+ "bc.status='out' "
					+ "ORDER BY bk.callNumber");
			rs = ps.executeQuery();
			while (rs.next()) {
				BookReportItem book = new BookReportItem();
				book.callNumber = rs.getString("callNumber");
				book.copyNo = rs.getString("copyNo");
				book.title = rs.getString("title");
				book.subject = "not requested";
				book.outDate = rs.getDate("outDate");
				book.dueDate = new Date(rs.getDate("outDate").getTime()
						+ rs.getLong("bookTimeLimit"));
				if (now.after(book.dueDate)) {
					book.overdue = true;
				} else {
					book.overdue = false;
				}
				report.add(book);
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return report;
	}

	public List<BookReportItem> outBookReport(String subject) {
		List<BookReportItem> report = new ArrayList<BookReportItem>();
		PreparedStatement ps;
		ResultSet rs;
		java.util.Date javaDate = new java.util.Date();
		Date now = new Date(javaDate.getTime());
		try {
			ps = con.prepareStatement("SELECT bk.callNumber, bc.copyNo, hs.subject, br.outDate, bk.title, bt.bookTimeLimit "
					+ "FROM BOOK bk, BOOKCOPY bc, BORROWING br, HASSUBJECT hs, BORROWER b, BORROWERTYPE bt "
					+ "WHERE bk.callNumber=bc.callnumber AND bc.callNumber=br.callNumber AND "
					+ "bc.callNumber=hs.callNumber AND b.type=bt.type AND br.bid=b.bid AND "
					+ "bc.status='out' AND hs.subject LIKE '%" + subject + "%'"
					+ "ORDER BY bk.callNumber");
			rs = ps.executeQuery();
			while (rs.next()) {
				BookReportItem book = new BookReportItem();
				book.callNumber = rs.getString("callNumber");
				book.copyNo = rs.getString("copyNo");
				book.title = rs.getString("title");
				book.subject = rs.getString("subject");
				book.outDate = rs.getDate("outDate");
				book.dueDate = new Date(rs.getDate("outDate").getTime()
						+ rs.getLong("bookTimeLimit"));
				if (now.after(book.dueDate)) {
					book.overdue = true;
				} else {
					book.overdue = false;
				}
				report.add(book);
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
		}
		return report;
	}
	
	
	public List<Book> popularItems(int year, int n) {
		List<Book> books = new ArrayList<Book>();
		Statement stmt;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT call, isbn, mainAuthor, publisher, title, year, " + //TODO: this query's timesborrowed thing is repeated thrice. This query can probably be simplified.
										"(SELECT COUNT(*) FROM BORROWING WHERE BORROWING.CALLNUMBER=call AND EXTRACT(YEAR FROM BORROWING.OUTDATE) =" + year + ") AS timesBorrowed " + 
										"FROM (SELECT CALLNUMBER AS call, isbn, mainAuthor, publisher, title, year " +
												"FROM BOOK " +
												"ORDER BY (SELECT COUNT(*) FROM BORROWING WHERE BORROWING.CALLNUMBER=BOOK.CALLNUMBER AND EXTRACT(YEAR FROM BORROWING.OUTDATE) =" + year + ") DESC" + 
										")" +
										"WHERE ROWNUM <="+n+" AND (SELECT COUNT(*) FROM BORROWING WHERE BORROWING.CALLNUMBER=call AND EXTRACT(YEAR FROM BORROWING.OUTDATE) =" + year + ") > 0");
			
			while (rs.next()) {
				Book book = new Book();
				book.callNumber = rs.getString("call");
				book.isbn = rs.getString("isbn");
				book.mainAuthor = rs.getString("mainAuthor");
				book.publisher = rs.getString("publisher");
				book.title = rs.getString("title");
				book.year = rs.getString("year");
				book.timesBorrowed = rs.getInt("timesBorrowed");
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace(); displayMessage("error: please try entering correct information.");
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

	// to be rewritten for GUI
	public void displayMessage(String msg) {
		System.out.println(msg);
		Main.outputToConsole(msg);
	}

	public static void main(String args[]) {
		Database d = new Database();
	}

}
