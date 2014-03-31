package matt.ui;

import java.awt.BorderLayout;
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
public class CheckOverduePanel extends JPanel implements ActionListener{
	protected JButton checkoverdueButton;
	
	// results table
		protected DefaultTableModel resultsDm;
		protected Object[][] defaultTableData = new Object[][] {};
		protected String[] resultsColumnNames = 
			{ "borrow id", "book id", "call number", "copy number", "out date", "in date" };
		protected JLabel resultsTableLabel;
		protected JTable resultsTable;
		protected JScrollPane resultsScroll;
	
	public CheckOverduePanel() {
		super(new GridBagLayout());
		
		///////////data fields///////////
		// add text fields
		
		// add the add button
		checkoverdueButton = new JButton("Check all overdue items");
		checkoverdueButton.addActionListener(this);
		
		
		// //////////////////////Check overdue table//////////////////////////
		// define a table model
		final DefaultTableModel checkOverdueDm = new DefaultTableModel();
		checkOverdueDm.setDataVector(defaultTableData, resultsColumnNames);

		// define a table widget
		resultsTableLabel = new JLabel(" results: ");
		resultsTable = new JTable(checkOverdueDm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		resultsTable.getTableHeader().setReorderingAllowed(false);
		resultsScroll = new JScrollPane(resultsTable);		
		
		//////////lay out components//////////
		GridBagConstraints c = new GridBagConstraints();	
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(checkoverdueButton, c);
		
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(resultsTableLabel, c);

		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(resultsScroll, c);
	}
	
	public void actionPerformed(ActionEvent evt) {
		System.out.println("check overdue button pressed");
		Main.getDbAccess().checkOverdue();
	}
}