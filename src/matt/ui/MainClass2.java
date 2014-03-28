package matt.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

//main tutorial used: previous, plus tabs: http://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
//here I made a totally useless menu in ViewSwitchMenubar and also learned more about panels and how tabs work

@SuppressWarnings("serial")
public class MainClass2 extends JFrame {
	
	private ViewSwitchMenubar viewSwitchMenubar;
	
    public MainClass2() {
    	super("Main Class");
    	viewSwitchMenubar = new ViewSwitchMenubar(this);
        this.setJMenuBar(viewSwitchMenubar);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setToolTipText("A Panel container 1");
        JButton quitButton = new JButton("Test button");
        quitButton.setBounds(50, 60, 160, 30);
        quitButton.setToolTipText("A Button component");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("I am a button");
           }
        });
        panel.add(quitButton,BorderLayout.CENTER);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setToolTipText("A Panel container 2");
        JButton quitButton2 = new JButton("Test button2");
        quitButton2.setBounds(50, 20, 160, 30);
        quitButton2.setToolTipText("A Button2 component");
        quitButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("I am a button2");
           }
        });
        
        JButton quitButton3 = new JButton("Test button3");
        quitButton3.setBounds(50, 60, 160, 30);
        quitButton3.setToolTipText("A Button3 component");
        quitButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("I am a button3");
           }
        });
        
        panel2.add(quitButton2,BorderLayout.PAGE_START);
        panel2.add(quitButton3,BorderLayout.PAGE_END);
        
        /////////////////////////////
        
        JTabbedPane subTabs = new JTabbedPane();
        JPanel panel4 = new JPanel();
        panel4.setLayout(null);
        panel4.setToolTipText("A Panel container 4");
        JButton quitButton4 = new JButton("Test button4");
        quitButton4.setBounds(50, 60, 160, 30);
        quitButton4.setToolTipText("A Button3 component");
        quitButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("I am a button4");
           }
        });
        panel4.add(quitButton4,BorderLayout.CENTER);
        
        JPanel panel5 = new JPanel();
        panel5.setLayout(null);
        panel5.setToolTipText("A Panel container 5");
        JButton quitButton5 = new JButton("Test button5");
        quitButton5.setBounds(50, 60, 160, 30);
        quitButton5.setToolTipText("A Button5 component");
        quitButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("I am a button5");
           }
        });
        panel5.add(quitButton5,BorderLayout.CENTER);
        
        subTabs.addTab("buttonfourpanel",panel4);
        subTabs.addTab("buttonfivepanel",panel5);
        
        tabbedPane.addTab("buttonpanel", panel);
        tabbedPane.addTab("buttontwopanel", panel2);
        tabbedPane.addTab("subtabpanel", subTabs);
        
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        setTitle("Simple menu, tabs");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainClass2 mc = new MainClass2();
                mc.setVisible(true);
            }
        });
    }
}