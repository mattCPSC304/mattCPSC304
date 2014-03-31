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
public class CheckOverduePanel extends JPanel implements ActionListener{
	protected JButton checkoverdueButton;
	
	public CheckOverduePanel() {
		super(new GridBagLayout());
		
		///////////data fields///////////
		// add text fields
		
		// add the add button
		checkoverdueButton = new JButton("Check all overdue items");
		checkoverdueButton.addActionListener(this);
		
		//////////lay out components//////////
		GridBagConstraints c = new GridBagConstraints();	
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1.0;
		c.weighty = 0.0;
		this.add(checkoverdueButton, c);
	}
	
	public void actionPerformed(ActionEvent evt) {
		System.out.println("check overdue button pressed");
		Main.getDbAccess().checkOverdue();
	}
}