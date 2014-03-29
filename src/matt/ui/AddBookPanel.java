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
public class AddBookPanel extends JPanel implements ActionListener {
	// search
	protected JLabel callNumberLabel;
	protected JTextField callNumberField;
	protected JLabel isbnLabel;
	protected JTextField isbnField;
	protected JLabel titleLabel;
	protected JTextField titleField;
	protected JLabel mainAuthorLabel;
	protected JTextField mainAuthorField;
	protected JLabel publisherLabel;
	protected JTextField publisherField;
	protected JLabel yearLabel;
	protected JTextField yearField;
	
	protected JButton addButton;
	
	public AddBookPanel() {
		super(new GridBagLayout());

		// //////////////////////search bar//////////////////////////
		// add text fields
		callNumberLabel = new JLabel(" call number:  ");
		callNumberField = new JTextField(20);
		callNumberField.addActionListener(this);
		
		isbnLabel = new JLabel(" isbn:  ");
		isbnField = new JTextField(20);
		isbnField.addActionListener(this);
		
		titleLabel = new JLabel(" title:  ");
		titleField = new JTextField(20);
		titleField.addActionListener(this);
		
		mainAuthorLabel = new JLabel(" main author:  ");
		mainAuthorField = new JTextField(20);
		mainAuthorField.addActionListener(this);
		
		publisherLabel = new JLabel(" publisher:  ");
		publisherField = new JTextField(20);
		publisherField.addActionListener(this);
		
		yearLabel = new JLabel(" year:  ");
		yearField = new JTextField(20);
		yearField.addActionListener(this);

		// add the add button
		addButton = new JButton("add book");
		addButton.addActionListener(this);

		// ////////////////lay out components/////////////////////
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(callNumberLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(callNumberField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(isbnLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(isbnField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(titleLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(titleField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(mainAuthorLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(mainAuthorField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(publisherLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(publisherField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0.0;
		c.weighty = 0.0;
		add(yearLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(yearField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(addButton, c);
	}

	// handles the searches
	public void actionPerformed(ActionEvent evt) {
		String callNumber = callNumberField.getText();
		String isbn = isbnField.getText();
		String title = titleField.getText();
		String mainAuthor = mainAuthorField.getText();
		String publisher = publisherField.getText();
		String year = yearField.getText();
		System.out.println("adding book: " + callNumber + ", " + isbn + ", " + title + ", " + mainAuthor + ", " + publisher + ", " + year);
		Main.getDbAccess().addBook(callNumber, isbn, title, mainAuthor, publisher, year);
	}
}