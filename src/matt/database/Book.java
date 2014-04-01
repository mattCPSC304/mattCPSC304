package matt.database;

import java.util.ArrayList;

public class Book {
	
	//  Book (callNumber, isbn, title, mainAuthor, publisher, year )
	public String callNumber=new String();
	public String isbn=new String();
	public String title=new String();
	public String mainAuthor=new String();
	public String publisher=new String();
	public String year=new String();
	public ArrayList<String> secondaryAuthors=new ArrayList<String>();
	public ArrayList<String> subjects=new ArrayList<String>();
	public Integer in;
	public Integer out;
	/*public Book(String CallNumber, String isbn, String title, String mainAuthor){
		
	}*/
}
