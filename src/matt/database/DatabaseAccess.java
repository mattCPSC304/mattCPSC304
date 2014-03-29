package matt.database;

public class DatabaseAccess {
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
}
