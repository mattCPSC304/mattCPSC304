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
public class MostPopularPanel extends JPanel implements ActionListener {
	// search
	protected JLabel yearLabel;
	protected JTextField yearField;
	protected JLabel nLabel;
	protected JTextField nField;
	protected JButton searchButton;
	
	// results table
	protected DefaultTableModel resultsDm;
	protected Object[][] defaultTableData = new Object[][] {};
	protected String[] resultsColumnNames = 
		{ "call number", "ISBN", "title", "main author", "publisher", "year published", "times borrowed" };
	protected JLabel resultsTableLabel;
	protected JTable resultsTable;
	protected JScrollPane resultsScroll;
	
	public MostPopularPanel() {
		super(new GridBagLayout());

		// //////////////////////search bar//////////////////////////
		// add a text field
		yearLabel = new JLabel(" Year:  ");
		yearField = new JTextField(20);
		yearField.addActionListener(this);
		
		nLabel = new JLabel(" Number of Items:  ");
		nField = new JTextField(20);
		nField.addActionListener(this);

		// add a search button
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);

		// //////////////////////checked out table//////////////////////////
		// define a table model
		final DefaultTableModel checkedOutDm = new DefaultTableModel();
		checkedOutDm.setDataVector(defaultTableData, resultsColumnNames);

		// define a table widget
		resultsTableLabel = new JLabel(" results: ");
		resultsTable = new JTable(checkedOutDm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		resultsTable.getTableHeader().setReorderingAllowed(false);
		resultsScroll = new JScrollPane(resultsTable);

		// ////////////////lay out components/////////////////////
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(yearLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL; // should consume horizontal
												// space
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0; // should consume horizontal space
		c.weighty = 0.0;
		add(yearField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(nLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL; // should consume horizontal
												// space
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0; // should consume horizontal space
		c.weighty = 0.0;
		add(nField, c);

		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(searchButton, c);

		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 2;
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

	// handles the searches
	public void actionPerformed(ActionEvent evt) {
		String year = yearField.getText();
		String n = nField.getText();
		System.out.println("searching for " + n + " items in year " + year);
		Object[][] queryResults = Main.getDbAccess().getPopularItems(year, n);
		resultsTable.setModel(new DefaultTableModel(queryResults, resultsColumnNames));
	}
}