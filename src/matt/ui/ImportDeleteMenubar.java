package matt.ui;

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
public class ImportDeleteMenubar extends JMenuBar {
	public ImportDeleteMenubar(JFrame parent) {
		////////////////////////
		////define Database menu////
		////////////////////////
        JMenu database = new JMenu("Database");
        database.setMnemonic(KeyEvent.VK_D);
        
        //define import button
        JMenuItem iMenuItem = new JMenuItem("Init DB");
        iMenuItem.setMnemonic(KeyEvent.VK_I);
        iMenuItem.setToolTipText("Reinitialize db tables");
        iMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                ActionEvent.CTRL_MASK));
        iMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Main.getDbAccess().initDb();
            }
        });
        database.add(iMenuItem);
        
        //define delete button
        ImageIcon icon = new ImageIcon("closeButtonRed.png");
        JMenuItem dMenuItem = new JMenuItem("Purge DB", icon);
        dMenuItem.setMnemonic(KeyEvent.VK_P);
        dMenuItem.setToolTipText("Delete db tables");
        dMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                ActionEvent.CTRL_MASK));
        dMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	Main.getDbAccess().purgeDb();
            }
        });
        database.add(dMenuItem);

        this.add(database);
	}
}
