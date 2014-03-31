package matt.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NewBorrowerPanel extends JPanel implements ActionListener{
	protected JLabel bidLabel;
	protected JTextField bidField;
	protected JLabel passwordLabel;
	protected JTextField passwordField;
	protected JLabel nameLabel;
	protected JTextField nameField;
	protected JLabel addressLabel;
	protected JTextField addressField;
	protected JLabel phoneLabel;
	protected JTextField phoneField;
	protected JLabel emailAddressLabel;
	protected JTextField emailAddressField;
	protected JLabel sinOrStNoLabel;
	protected JTextField sinOrStNoField;
	protected JLabel expiryDateYearLabel;
	protected JTextField expiryDateYearField;
	protected JLabel expiryDateMonthLabel;
	protected JTextField expiryDateMonthField;
	protected JLabel expiryDateDayLabel;
	protected JTextField expiryDateDayField;
	protected JLabel typeLabel;
	protected JTextField typeField;
	protected JButton addBorrowerButton;
	
	public NewBorrowerPanel() {
		super(new GridBagLayout());
		
		///////////data fields///////////
		// add text fields
		bidLabel = new JLabel(" borrower id:  ");
		bidField = new JTextField(20);
		bidField.addActionListener(this);
		
		passwordLabel = new JLabel(" password:  ");
		passwordField = new JTextField(20);
		passwordField.addActionListener(this);
		
		nameLabel = new JLabel(" name:  ");
		nameField = new JTextField(20);
		nameField.addActionListener(this);
		
		addressLabel = new JLabel(" address: ");
		addressField = new JTextField(20);
		addressField.addActionListener(this);
		
		phoneLabel = new JLabel(" phone number: ");
		phoneField = new JTextField(20);
		phoneField.addActionListener(this);
		
		emailAddressLabel = new JLabel(" email address: ");
		emailAddressField = new JTextField(20);
		emailAddressField.addActionListener(this);
		
		sinOrStNoLabel = new JLabel(" SIN/student number ");
		sinOrStNoField = new JTextField(20);
		sinOrStNoField.addActionListener(this);
		
		expiryDateYearLabel = new JLabel(" expiry date year");
		expiryDateYearField = new JTextField(20);
		expiryDateYearField.addActionListener(this);
		
		expiryDateMonthLabel = new JLabel(" expiry date month");
		expiryDateMonthField = new JTextField(20);
		expiryDateMonthField.addActionListener(this);
		
		expiryDateDayLabel = new JLabel(" expiry date day");
		expiryDateDayField = new JTextField(20);
		expiryDateDayField.addActionListener(this);
		
		typeLabel = new JLabel(" type ");
		typeField = new JTextField(20);
		typeField.addActionListener(this);
		
		// add the add button
		addBorrowerButton = new JButton("add borrower");
		addBorrowerButton.addActionListener(this);
		
		//////////lay out components//////////
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(bidLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(bidField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(passwordLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(passwordField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(nameLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(nameField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(addressLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(addressField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(phoneLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(phoneField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(emailAddressLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(emailAddressField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(sinOrStNoLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 6;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(sinOrStNoField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 7;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(expiryDateYearLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 7;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(expiryDateYearField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 8;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(expiryDateMonthLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 8;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(expiryDateMonthField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 9;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(expiryDateDayLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 9;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(expiryDateDayField, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 10;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(typeLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 10;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(typeField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 11;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(addBorrowerButton, c);
	}
	
	public void actionPerformed(ActionEvent evt) {
		String bid = bidField.getText();
		String password =  passwordField.getText();
		String name =  nameField.getText();
		String address =  addressField.getText();
		String phone =  phoneField.getText();
		String emailAddress =  emailAddressField.getText();
		String sinOrStNo =  sinOrStNoField.getText();
		String expiryDateYear =  expiryDateYearField.getText();
		String expiryDateMonth =  expiryDateMonthField.getText();
		String expiryDateDay =  expiryDateDayField.getText();
		String type =  typeField.getText();
		System.out.println("adding borrower: " + bid + ", " + password + ", " + name + ", " + address + ", " + phone + ", " + emailAddress + ", " + sinOrStNo + ", " + expiryDateYear + ", " + expiryDateMonth + ", " + expiryDateDay + ", " + type);
		Main.getDbAccess().addBorrower(bid, password, name, address, phone, emailAddress, sinOrStNo, expiryDateYear, expiryDateMonth, expiryDateDay, type);
	}
}