package matt.database;

import java.util.ArrayList;
import java.util.List;


public class AccountInfo {
	public List<BorrowedBook> borrowedBooks = new ArrayList<BorrowedBook>();//books not returned
	public List<Fine> fines = new ArrayList<Fine>();
	public List<HoldRequest> holdRequests = new ArrayList<HoldRequest>();
}
