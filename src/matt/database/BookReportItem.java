package matt.database;

import java.sql.Date;


public class BookReportItem {
	public String callNumber=new String();
	public String copyNo=new String();
	public String title=new String();
	public String subject=new String();
	public Date outDate=new Date(0);
	public Date dueDate=new Date(0);
	boolean overdue;
}
