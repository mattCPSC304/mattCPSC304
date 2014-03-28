package matt.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
//import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

//main tutorial used: previous, plus:
// for the useful/horrifying gridbag layouts: http://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html

@SuppressWarnings("serial")
public class MainClass3 extends JFrame {
    public MainClass3() {
    	super("Main Class");
        
    	// create the tab hierarchy. Leaf tabs are custom panels we define.
        JTabbedPane mainTabs = new JTabbedPane(); getContentPane().add(mainTabs, BorderLayout.CENTER);
	        JTabbedPane clerkTabs = new JTabbedPane(); mainTabs.addTab("clerk", clerkTabs);
	        	JPanel newBorrowerPanel = new NewBorrowerPanel(); clerkTabs.addTab("new borrower", newBorrowerPanel);
	        	JPanel checkoutPanel = new CheckoutPanel(); clerkTabs.addTab("checkout", checkoutPanel);
	        	JPanel returnPanel = new ReturnPanel(); clerkTabs.addTab("return", returnPanel);
	        	JPanel checkOverduePanel = new CheckOverduePanel(); clerkTabs.addTab("check overdue", checkOverduePanel);
	        JTabbedPane borrowerTabs = new JTabbedPane(); mainTabs.addTab("borrower", borrowerTabs);
		        JPanel searchPanel = new SearchPanel(); borrowerTabs.addTab("search", searchPanel);
	        	JPanel checkAccountPanel = new CheckAccountPanel(); borrowerTabs.addTab("check account", checkAccountPanel);
	        	JPanel placeHoldPanel = new PlaceHoldPanel(); borrowerTabs.addTab("place hold", placeHoldPanel);
	        	JPanel payFinePanel = new PayFinePanel(); borrowerTabs.addTab("pay fine", payFinePanel);
	        JTabbedPane librarianTabs = new JTabbedPane(); mainTabs.addTab("librarian", librarianTabs);
		        JPanel addBookPanel = new AddBookPanel(); librarianTabs.addTab("add book", addBookPanel);
	        	JPanel checkedOutPanel = new CheckedOutPanel(); librarianTabs.addTab("checked out", checkedOutPanel);
	        	JPanel mostPopularPanel = new MostPopularPanel(); librarianTabs.addTab("most popular", mostPopularPanel);

        
        
        setTitle("Simple menu");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public class NewBorrowerPanel extends JPanel implements ActionListener { //TODO: make this actually useful
    	/*protected JTextField textField;
    	protected JButton submitButton;
        protected JTextArea textArea;
        private final static String newline = "\n";
     
        public NewBorrowerPanel() {
            super(new GridBagLayout());
     
            textField = new JTextField(20);
            textField.addActionListener(this);
            
            submitButton = new JButton("search");
            submitButton.addActionListener(this);
     
            textArea = new JTextArea(5, 20);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
     
            //Add Components to this panel.
            GridBagConstraints c = new GridBagConstraints();
            
            c.fill = GridBagConstraints.HORIZONTAL; // should consume horizontal space
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0; // should consume horizontal space
            c.weighty = 0.0;
            add(textField, c);
            
            c.fill = GridBagConstraints.NONE;
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.0;
            c.weighty = 0.0;
            add(submitButton, c);
     
            c.fill = GridBagConstraints.BOTH;
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 1.0;
            c.weighty = 1.0;
            add(scrollPane, c);
        }
     
        public void actionPerformed(ActionEvent evt) {
            String text = textField.getText();
            textArea.append(text + newline);
            textField.selectAll();
     
            //Make sure the new text is visible, even if there
            //was a selection in the text area.
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }*/
    	
    	protected JTextField textField;
    	protected JButton submitButton;
        protected JTable table;
        protected String[] columnNames = {"First Name",
						                "Last Name",
						                "Sport",
						                "# of Years",
						                "Vegetarian"};
        
        public NewBorrowerPanel() {
            super(new GridBagLayout());
     
            textField = new JTextField(20);
            textField.addActionListener(this);
            
            submitButton = new JButton("search");
            submitButton.addActionListener(this);
     
            

			Object[][] data = {
			{"First", "Results", "Aren't that good", new Integer(5), new Boolean(false)}
			};
			
			table = new JTable(data, columnNames);
			table.setPreferredScrollableViewportSize(new Dimension(500, 70));
			table.setFillsViewportHeight(true);
			
	        JScrollPane scrollPane = new JScrollPane(table);
     
            //Add Components to this panel.
            GridBagConstraints c = new GridBagConstraints();
            
            c.fill = GridBagConstraints.HORIZONTAL; // should consume horizontal space
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1.0; // should consume horizontal space
            c.weighty = 0.0;
            add(textField, c);
            
            c.fill = GridBagConstraints.NONE;
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0.0;
            c.weighty = 0.0;
            add(submitButton, c);
     
            c.fill = GridBagConstraints.BOTH;
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 1.0;
            c.weighty = 1.0;
            add(scrollPane, c);
        }
     
        public void actionPerformed(ActionEvent evt) {
            String text = textField.getText();
            System.out.println("searching for: " + text);
            Object[][] data = getData(text);
            table.setModel(new DefaultTableModel(data, columnNames));
        }
    }

    public class CheckoutPanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public CheckoutPanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }

    public class ReturnPanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public ReturnPanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }

    public class CheckOverduePanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public CheckOverduePanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }

    public class SearchPanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public SearchPanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }

    public class CheckAccountPanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public CheckAccountPanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }

    public class PlaceHoldPanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public PlaceHoldPanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }

    public class PayFinePanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public PayFinePanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }

    public class AddBookPanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public AddBookPanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }

    public class CheckedOutPanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public CheckedOutPanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }

    public class MostPopularPanel extends JPanel { //TODO: make this do more than just have a dumb println button
    	public MostPopularPanel() {
    		super();
    		this.setLayout(null);
    		this.setToolTipText("A Panel container");
            JButton quitButton = new JButton("Test button");
            quitButton.setBounds(50, 60, 160, 30);
            quitButton.setToolTipText("A Button component");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    System.out.println("I am a button");
               }
            });
            this.add(quitButton,BorderLayout.CENTER);
    	}
    }
    
    public Object[][] getData(String searchString) { //TODO: make this return something not-hardcoded
    	Object[][] data = {
    			{"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
    			{"John", "Doe",	"Rowing", new Integer(3), new Boolean(true)},
    			{"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
    			{"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
    			{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)}
    			};
    	return data;
    }
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainClass3 mc = new MainClass3();
                mc.setVisible(true);
            }
        });
    }
}