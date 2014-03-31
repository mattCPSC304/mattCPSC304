package matt.database;

import java.sql.Date;


public class HoldRequest {
	//HoldRequest(hid, bid, callNumber, issuedDate)
	int hid;
	int bid;
	String callNumber = new String();
	Date issuedDate = new Date(0);
}
