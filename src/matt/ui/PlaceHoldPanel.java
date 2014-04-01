package matt.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PlaceHoldPanel extends JPanel implements ActionListener{
	protected JLabel bidLabel;
	protected JTextField bidField;
	protected JLabel callNumberLabel;
	protected JTextField callNumberField;
	protected JButton returnButton;
	
	public PlaceHoldPanel() {
		super(new GridBagLayout());
		
		///////////data fields///////////
		// add text fields
		bidLabel = new JLabel("Borrower id:  ");
		bidField = new JTextField(20);
		bidField.addActionListener(this);
		
		callNumberLabel = new JLabel("Call number:  ");
		callNumberField = new JTextField(20);
		callNumberField.addActionListener(this);
		
		// add the add button
		returnButton = new JButton("Place Hold");
		returnButton.addActionListener(this);
		
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
		this.add(callNumberLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(callNumberField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(returnButton, c);
	}
	
	public void actionPerformed(ActionEvent evt) {
		String bid = bidField.getText();
		String callNumber = callNumberField.getText();
		Date issuedDate = new Date(new java.util.Date().getTime());
		System.out.println("placing hold request: " + bid + " for book " + callNumber);
		Main.getDbAccess().holdRequest(bid, callNumber, issuedDate);
	}
}