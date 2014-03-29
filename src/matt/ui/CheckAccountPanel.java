package matt.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class CheckAccountPanel extends JPanel implements ActionListener { //TODO: make this do more than just have a dumb println button
	protected JLabel borrowerIdLabel;
	protected JTextField textField;
	protected JButton submitButton;
	protected Object[][] defaultTableData = new Object[][] {};
	
	//fine table
	protected ButtonEditor buttonEditor;
	protected DefaultTableModel fineDm;   	
	protected String[] fineColumnNames = { "pay fine", "call number", "ISBN", "title","main author","publisher","year"};
	protected JLabel fineTableLabel;
	protected JTable fineTable;
	protected JScrollPane fineScroll;
	
	//checked out table
	protected DefaultTableModel checkedOutDm;
	protected String[] checkedOutColumnNames = {"call number", "ISBN", "title","main author","publisher","year"};
	protected JLabel checkedOutTableLabel;
	protected JTable checkedOutTable;
	protected JScrollPane checkedOutScroll;
	
	//hold request table
	protected DefaultTableModel holdRequestDm;
	protected String[] holdRequestColumnNames = { "hold request id", "borrower id", "call number", "issued date"};
	protected JLabel holdRequestTableLabel;
	protected JTable holdRequestTable;
	protected JScrollPane holdRequestScroll;
	
	public CheckAccountPanel() {
		super(new GridBagLayout());
		
		////////////////////////search bar//////////////////////////
		//add a text field
		borrowerIdLabel = new JLabel(" borrower id ");
		textField = new JTextField(20);
        textField.addActionListener(this);
        
        //add a search button
        submitButton = new JButton("login");
        submitButton.addActionListener(this);
		
        ////////////////////////fine table//////////////////////////
		//define a table model
		final DefaultTableModel fineDm = new DefaultTableModel();
		fineDm.setDataVector(defaultTableData, fineColumnNames);

		//define a table widget
		fineTableLabel = new JLabel(" outstanding fines ");
		fineTable = new JTable(fineDm) {
	        public boolean isCellEditable(int row, int column) {                
				return column == 0 ? true : false; 
				// hack: return true for whatever column you allow a button
				// to be in, or the button is unclickable. Normally return
				// false since we don't want the user to edit our cells.
			};
	    };
		fineTable.getTableHeader().setReorderingAllowed(false); 
		
		//add a button editor column to the table
		buttonEditor = new ButtonEditor("pay fine", new DataAction() {
			public Integer act(Object data) {
				System.out.println("Paid fineid: "
						+ data.toString());
				Object[][] queryResults = Main.getDbAccess().getData("dummy");
				fineDm.setDataVector(queryResults, fineColumnNames);
				Main.outputToConsole("fine id " + data.toString() + " paid.");
				attachButtonEditorToFineTable();
				return null;
			}
		});
	    attachButtonEditorToFineTable();
	    fineScroll = new JScrollPane(fineTable);
	    
	    ////////////////////////checked out table//////////////////////////
	  	//define a table model
		final DefaultTableModel checkedOutDm = new DefaultTableModel();
		checkedOutDm.setDataVector(defaultTableData, checkedOutColumnNames);

		//define a table widget
		checkedOutTableLabel = new JLabel(" checked out books ");
		checkedOutTable = new JTable(checkedOutDm) {
	        public boolean isCellEditable(int row, int column) {                
				return false;
	        }
	    };
		checkedOutTable.getTableHeader().setReorderingAllowed(false); 
	    checkedOutScroll = new JScrollPane(checkedOutTable);
	    
	    
	    ///////////////////////hold request table//////////////////////////
	    //define a table model
		final DefaultTableModel holdRequestDm = new DefaultTableModel();
		holdRequestDm.setDataVector(defaultTableData, holdRequestColumnNames);

		//define a table widget
		holdRequestTableLabel = new JLabel(" hold requests ");
		holdRequestTable = new JTable(holdRequestDm) {
	        public boolean isCellEditable(int row, int column) {                
				return false;
	        }
	    };
	    holdRequestTable.getTableHeader().setReorderingAllowed(false); 
	    holdRequestScroll = new JScrollPane(holdRequestTable);

	    //////////////////lay out components/////////////////////
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        add(borrowerIdLabel, c);
        
        c.fill = GridBagConstraints.HORIZONTAL; // should consume horizontal space
        c.gridwidth = 8;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0; // should consume horizontal space
        c.weighty = 0.0;
        add(textField, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 9;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        add(submitButton, c);
        
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        add(fineTableLabel, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(fineScroll, c);
        
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 3;
        c.gridx = 3;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        add(checkedOutTableLabel, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 3;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(checkedOutScroll, c);
        
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 4;
        c.gridx = 6;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 0.0;
        add(holdRequestTableLabel, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 4;
        c.gridx = 6;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(holdRequestScroll, c);
	}
	
	//hack: allows the DataAction to update the table and preserve the buttons (call after table updates)
	private void attachButtonEditorToFineTable() {
		fineTable.getColumn("pay fine").setCellRenderer(buttonEditor);
	    fineTable.getColumn("pay fine").setCellEditor(buttonEditor);
	}
	
	//handles the searches
	public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        System.out.println("searching for: " + text);
        Object[][] queryResults = Main.getDbAccess().getData(text);
        fineTable.setModel(new DefaultTableModel(queryResults, fineColumnNames));
        checkedOutTable.setModel(new DefaultTableModel(queryResults, checkedOutColumnNames));
        holdRequestTable.setModel(new DefaultTableModel(queryResults, holdRequestColumnNames));
        attachButtonEditorToFineTable();
    }
}