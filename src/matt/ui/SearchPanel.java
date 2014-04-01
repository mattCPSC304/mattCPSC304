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
public class SearchPanel extends JPanel implements ActionListener {
	// search
	protected JLabel titleLabel;
	protected JTextField titleField;
	protected JLabel authorLabel;
	protected JTextField authorField;
	protected JLabel subjectLabel;
	protected JTextField subjectField;
	protected JButton searchButton;
	
	// results table
	protected DefaultTableModel resultsDm;
	protected Object[][] defaultTableData = new Object[][] {};
	protected String[] resultsColumnNames = 
		{ "call number", "ISBN", "title", "main author", "publisher", "year" };
	protected JLabel resultsTableLabel;
	protected JTable resultsTable;
	protected JScrollPane resultsScroll;
	
	public SearchPanel() {
		super(new GridBagLayout());

		// //////////////////////search bar//////////////////////////
		//add search fields
		titleLabel = new JLabel(" title:  ");
		titleField = new JTextField(20);
		titleField.addActionListener(this);

		authorLabel = new JLabel(" author:  ");
		authorField = new JTextField(20);
		authorField.addActionListener(this);

		subjectLabel = new JLabel(" subject:  ");
		subjectField = new JTextField(20);
		subjectField.addActionListener(this);
		
		//add search button
		searchButton = new JButton("search");
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
		add(titleLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(titleField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(authorLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 5;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(authorField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 8;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(subjectLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 9;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(subjectField, c);

		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 12;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(searchButton, c);
		
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 13;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(resultsTableLabel, c);

		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 13;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(resultsScroll, c);
	}

	// handles the searches
	public void actionPerformed(ActionEvent evt) {
		String title = titleField.getText();
		String author = authorField.getText();
		String subject = subjectField.getText();
		System.out.println("searching for text, author, subject: " + title + ", " + author + ", " + subject);
		Object[][] queryResults = Main.getDbAccess().getBooks(title, author, subject);
		resultsTable.setModel(new DefaultTableModel(queryResults, resultsColumnNames));
	}
}