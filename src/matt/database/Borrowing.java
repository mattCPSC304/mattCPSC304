package matt.database;

import java.sql.Date;


public class Borrowing {
	int borid;
	int bid;
	String callNumber = new String();
	String copyNo = new String();
	Date outDate = new Date(0);
	Date inDate = new Date(0); //inDate is initially set to Date(0) at check out time, and overwritten at return time.
	Date dueDate = new Date(0);
	String email; //a hack for the overdue items page
}
