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
public class CheckoutPanel extends JPanel implements ActionListener{
	protected JLabel bidLabel;
	protected JTextField bidField;
	protected JLabel callNumbersLabel;
	protected JTextField callNumbersField;
	protected JButton checkoutButton;
	
	public CheckoutPanel() {
		super(new GridBagLayout());
		
		///////////data fields///////////
		// add text fields
		bidLabel = new JLabel(" borrower id / card number:  ");
		bidField = new JTextField(20);
		bidField.addActionListener(this);
		
		callNumbersLabel = new JLabel(" call numbers:  ");
		callNumbersField = new JTextField(20);
		callNumbersField.addActionListener(this);
		
		// add the add button
		checkoutButton = new JButton("check out");
		checkoutButton.addActionListener(this);
		
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
		this.add(callNumbersLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(callNumbersField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(checkoutButton, c);
	}
	
	public void actionPerformed(ActionEvent evt) {
		String bid = bidField.getText();
		String callNumbers =  callNumbersField.getText();
		System.out.println("checking out books: " + bid + ", " + callNumbers);
		Main.getDbAccess().checkout(bid, callNumbers);
	}
}