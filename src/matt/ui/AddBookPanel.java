package matt.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
// this is kind of two panels in one but whatever
public class AddBookPanel extends JPanel {
	
	// book
	protected JPanel bookPanel;
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
	protected JButton addBookButton;
	protected ActionListener addBook;
	
	// book copy
	protected JPanel copyPanel;
	protected JLabel callNumberLabelCopy;
	protected JTextField callNumberFieldCopy;
	protected JLabel copyNoLabel;
	protected JTextField copyNoField;
	protected JLabel statusLabel;
	protected JTextField statusField;
	protected JButton addBookCopyButton;
	protected ActionListener addCopy;
	
	public AddBookPanel() {
		super(new GridLayout(1,2));
		
		////////////////////////book//////////////////////////
		bookPanel = new JPanel(new GridBagLayout());
		
		//this is responsible for adding the book
		addBook = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String callNumber = callNumberField.getText();
				String isbn = isbnField.getText();
				String title = titleField.getText();
				String mainAuthor = mainAuthorField.getText();
				String publisher = publisherField.getText();
				String year = yearField.getText();
				System.out.println("adding book: " + callNumber + ", " + isbn + ", " + title + ", " + mainAuthor + ", " + publisher + ", " + year);
				Main.getDbAccess().addBook(callNumber, isbn, title, mainAuthor, publisher, year);
			};
		};
		
		this.add(bookPanel);
		
		
		//////////////data fields/////////
		// add text fields
		callNumberLabel = new JLabel(" call number:  ");
		callNumberField = new JTextField(20);
		callNumberField.addActionListener(addBook);
		
		isbnLabel = new JLabel(" isbn:  ");
		isbnField = new JTextField(20);
		isbnField.addActionListener(addBook);
		
		titleLabel = new JLabel(" title:  ");
		titleField = new JTextField(20);
		titleField.addActionListener(addBook);
		
		mainAuthorLabel = new JLabel(" main author:  ");
		mainAuthorField = new JTextField(20);
		mainAuthorField.addActionListener(addBook);
		
		publisherLabel = new JLabel(" publisher:  ");
		publisherField = new JTextField(20);
		publisherField.addActionListener(addBook);
		
		yearLabel = new JLabel(" year:  ");
		yearField = new JTextField(20);
		yearField.addActionListener(addBook);

		// add the add button
		addBookButton = new JButton("add book");
		addBookButton.addActionListener(addBook);

		///////////lay out components///////
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		bookPanel.add(callNumberLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.0;
		bookPanel.add(callNumberField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		bookPanel.add(isbnLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		bookPanel.add(isbnField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		bookPanel.add(titleLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 0.0;
		bookPanel.add(titleField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		bookPanel.add(mainAuthorLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1.0;
		c.weighty = 0.0;
		bookPanel.add(mainAuthorField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0.0;
		c.weighty = 0.0;
		bookPanel.add(publisherLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 1.0;
		c.weighty = 0.0;
		bookPanel.add(publisherField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0.0;
		c.weighty = 0.0;
		bookPanel.add(yearLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1.0;
		c.weighty = 0.0;
		bookPanel.add(yearField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 1.0;
		c.weighty = 0.0;
		bookPanel.add(addBookButton, c);
		
		////////////////////////book copy//////////////////////////
		copyPanel = new JPanel(new GridBagLayout());
		
		//this is responsible for adding the book
		addCopy = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String callNumber = callNumberFieldCopy.getText();
				String copyNo = copyNoField.getText();
				String status = statusField.getText();
				System.out.println("adding book copy: " + callNumber + ", " + copyNo + ", " + status);
				Main.getDbAccess().addBookCopy(callNumber, copyNo, status);
			};
		};
		
		this.add(copyPanel);
		
		///////////data fields///////////
		// add text fields
		callNumberLabelCopy = new JLabel(" call number:  ");
		callNumberFieldCopy = new JTextField(20);
		callNumberFieldCopy.addActionListener(addCopy);
		
		copyNoLabel = new JLabel(" copy number:  ");
		copyNoField = new JTextField(20);
		copyNoField.addActionListener(addCopy);
		
		statusLabel = new JLabel(" status:  ");
		statusField = new JTextField(20);
		statusField.addActionListener(addCopy);
		
		// add the add button
		addBookCopyButton = new JButton("add book copy");
		addBookCopyButton.addActionListener(addCopy);
		
		//////////lay out components//////////
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		copyPanel.add(callNumberLabelCopy, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.0;
		copyPanel.add(callNumberFieldCopy, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		copyPanel.add(copyNoLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		copyPanel.add(copyNoField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		copyPanel.add(statusLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 0.0;
		copyPanel.add(statusField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1.0;
		c.weighty = 0.0;
		copyPanel.add(addBookCopyButton, c);
	}
}