package matt.ui;

import javax.swing.SwingUtilities;

import matt.database.DatabaseAccess;

public class Main {
	static private DatabaseAccess dbAccess;
	static UIWindow window;
    /**
     * displays a message in the console on the side of the UI.
     */
    public static void outputToConsole(String outputString) { //TODO: make this output to the widget I mad
    	window.LogMessage(outputString);
    }
    
    public static DatabaseAccess getDbAccess() {
    	return dbAccess;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                window = new UIWindow();
                dbAccess = new DatabaseAccess();
                window.setVisible(true);
            }
        });
    }
}
