package matt.oldui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

// see MainClass2.java

@SuppressWarnings("serial")
public class ViewSwitchMenubar extends JMenuBar {
	public ViewSwitchMenubar(JFrame parent) {
		////////////////////////
		////define File menu////
		////////////////////////
        JMenu file = new JMenu("System");
        file.setMnemonic(KeyEvent.VK_S);
        
        //define exit button
        ImageIcon icon = new ImageIcon("closeButtonRed.png");
        JMenuItem eMenuItem = new JMenuItem("Exit", icon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
                ActionEvent.CTRL_MASK));
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        file.add(eMenuItem);

        this.add(file);
        
        /////////////////////////
        ////define clerk menu////
        /////////////////////////
        JMenu clerk = new JMenu("Clerk");
        clerk.setMnemonic(KeyEvent.VK_C);
        
        //define new borrower button
        ImageIcon cnbIcon = new ImageIcon("clerkNewBorrower.png");
        JMenuItem cnbMenuItem = new JMenuItem("New Borrower", cnbIcon);
        cnbMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Clerk New Borrower menu pressed"); //TODO: do something other than print
            }
        });
        clerk.add(cnbMenuItem);

        //define checkout button
        ImageIcon ccIcon = new ImageIcon("clerkCheckout.png");
        JMenuItem ccMenuItem = new JMenuItem("Checkout", ccIcon);
        ccMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Clerk Checkout menu pressed"); //TODO: do something other than print
            }
        });
        clerk.add(ccMenuItem);
        
        //define return button
        ImageIcon crIcon = new ImageIcon("clerkReturn.png");
        JMenuItem crMenuItem = new JMenuItem("Return", crIcon);
        crMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Clerk Return menu pressed"); //TODO: do something other than print
            }
        });
        clerk.add(crMenuItem);
        
        //define overdue button
        ImageIcon coIcon = new ImageIcon("clerkOverdue.png");
        JMenuItem coMenuItem = new JMenuItem("Overdue", coIcon);
        coMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Clerk Overdue menu pressed"); //TODO: do something other than print
            }
        });
        clerk.add(coMenuItem);
        
        this.add(clerk);
        
        ////////////////////////////
        ////define borrower menu////
        ////////////////////////////
        JMenu borrower = new JMenu("Borrower");
        borrower.setMnemonic(KeyEvent.VK_B);
        
        //define search button
        ImageIcon bsIcon = new ImageIcon("borrowerSearch.png");
        JMenuItem bsMenuItem = new JMenuItem("Search", bsIcon);
        bsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Borrower Search menu pressed"); //TODO: do something other than print
            }
        });
        borrower.add(bsMenuItem);
        
        //define check account button
        ImageIcon bcaIcon = new ImageIcon("borrowerCheckAccount.png");
        JMenuItem bcaMenuItem = new JMenuItem("Check Account", bcaIcon);
        bcaMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Borrower Check Account menu pressed"); //TODO: do something other than print
            }
        });
        borrower.add(bcaMenuItem);
        
        //define place hold button
        ImageIcon bphIcon = new ImageIcon("borrowerPlaceHold.png");
        JMenuItem bphMenuItem = new JMenuItem("Place Hold", bphIcon);
        bphMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Borrower Place Hold menu pressed"); //TODO: do something other than print
            }
        });
        borrower.add(bphMenuItem);
        
        //define pay fine button
        ImageIcon bpfIcon = new ImageIcon("borrowerPayFine.png");
        JMenuItem bpfMenuItem = new JMenuItem("Pay Fine", bpfIcon);
        bpfMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Borrower Pay Fine menu pressed"); //TODO: do something other than print
            }
        });
        borrower.add(bpfMenuItem);
        
        this.add(borrower);
        
        /////////////////////////////
        ////define librarian menu////
        /////////////////////////////
        JMenu librarian = new JMenu("Librarian");
        librarian.setMnemonic(KeyEvent.VK_L);
        
        //define add book button
        ImageIcon labIcon = new ImageIcon("librarianAddBook.png");
        JMenuItem labMenuItem = new JMenuItem("Add Book", labIcon);
        labMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Librarian Add Book menu pressed"); //TODO: do something other than print
            }
        });
        librarian.add(labMenuItem);
        
        //define checked out button
        ImageIcon lcoIcon = new ImageIcon("librarianCheckedOut.png");
        JMenuItem lcoMenuItem = new JMenuItem("Checked Out", lcoIcon);
        lcoMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	System.out.println("Librarian Checked Out menu pressed"); //TODO: do something other than print
            }
        });
        librarian.add(lcoMenuItem);
        
        //define most popular button
        ImageIcon lmpIcon = new ImageIcon("librarianMostPopular.png");
        JMenuItem lmpMenuItem = new JMenuItem("Most Popular", lmpIcon);
        lmpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Librarian Most Popular menu pressed"); //TODO: do something other than print
            }
        });
        librarian.add(lmpMenuItem);

        this.add(librarian);
	}
}
