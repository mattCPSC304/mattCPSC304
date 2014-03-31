package matt.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

//main tutorial used: previous, plus:
// for the useful/horrifying gridbag layouts: http://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html

@SuppressWarnings("serial")
public class UIWindow extends JFrame {
	
	ImportDeleteMenubar importDeleteMenubar;
	JTabbedPane mainTabs;
	JTabbedPane clerkTabs;
 	JPanel newBorrowerPanel;
 	JPanel checkoutPanel;
 	JPanel returnPanel;
 	JPanel checkOverduePanel;
 	JTabbedPane borrowerTabs;
 	JPanel searchPanel;
 	JPanel checkAccountPanel;
 	JPanel placeHoldPanel;
 	JTabbedPane librarianTabs;
 	JPanel addBookPanel;
 	JPanel checkedOutPanel;
 	JPanel mostPopularPanel;
 	JTextArea textArea;
 	
    public UIWindow() {
    	super("Main Class");

    	//initialize the menu
    	importDeleteMenubar = new ImportDeleteMenubar(this);
        this.setJMenuBar(importDeleteMenubar);
    	
    	// create the tab hierarchy. Leaf tabs are custom panels we define.
        mainTabs = new JTabbedPane(); getContentPane().add(mainTabs, BorderLayout.CENTER);
	        clerkTabs = new JTabbedPane(); mainTabs.addTab("clerk", clerkTabs);
	        	newBorrowerPanel = new NewBorrowerPanel(); clerkTabs.addTab("new borrower", newBorrowerPanel);
	        	checkoutPanel = new CheckoutPanel(); clerkTabs.addTab("checkout", checkoutPanel);
	        	returnPanel = new ReturnPanel(); clerkTabs.addTab("return", returnPanel);
	        	checkOverduePanel = new CheckOverduePanel(); clerkTabs.addTab("check overdue", checkOverduePanel);
	        borrowerTabs = new JTabbedPane(); mainTabs.addTab("borrower", borrowerTabs);
		        searchPanel = new SearchPanel(); borrowerTabs.addTab("search", searchPanel);
	        	checkAccountPanel = new CheckAccountPanel(); borrowerTabs.addTab("check account", checkAccountPanel);
	        	placeHoldPanel = new PlaceHoldPanel(); borrowerTabs.addTab("place hold", placeHoldPanel);
	        librarianTabs = new JTabbedPane(); mainTabs.addTab("librarian", librarianTabs);
		        addBookPanel = new AddBookPanel(); librarianTabs.addTab("add book", addBookPanel);
	        	checkedOutPanel = new CheckedOutPanel(); librarianTabs.addTab("checked out", checkedOutPanel);
	        	mostPopularPanel = new MostPopularPanel(); librarianTabs.addTab("most popular", mostPopularPanel);
	    
	    textArea = new JTextArea("", 10,10);
    	getContentPane().add(textArea, BorderLayout.EAST);
        
        setTitle("CPSC 304 DB browser");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void LogMessage(String message) {
    	textArea.append(message + "\n");
    }
}