package matt.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ReturnPanel extends JPanel implements ActionListener{
	protected JLabel boridLabel;
	protected JTextField boridField;
	protected JButton returnButton;
	
	public ReturnPanel() {
		super(new GridBagLayout());
		
		///////////data fields///////////
		// add text fields
		boridLabel = new JLabel(" borrowing id:  ");
		boridField = new JTextField(20);
		boridField.addActionListener(this);
		
		// add the add button
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		
		//////////lay out components//////////
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		this.add(boridLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(boridField, c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(returnButton, c);
	}
	
	public void actionPerformed(ActionEvent evt) {
		String borid = boridField.getText();
		System.out.println("checking out books: " + borid);
		Main.getDbAccess().p_Return(borid);
	}
}