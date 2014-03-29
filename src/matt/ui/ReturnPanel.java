package matt.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ReturnPanel extends JPanel { //TODO: make this do more than just have a dumb println button
	public ReturnPanel() {
		super();
		this.setLayout(null);
		this.setToolTipText("A Panel container");
        JButton quitButton = new JButton("Test button");
        quitButton.setBounds(50, 60, 160, 30);
        quitButton.setToolTipText("A Button component");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("I am a button");
           }
        });
        this.add(quitButton,BorderLayout.CENTER);
	}
}