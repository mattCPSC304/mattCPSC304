package matt.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

//main tutorial used: http://zetcode.com/tutorials/javaswingtutorial/
//probably more modern: http://docs.oracle.com/javase/tutorial/uiswing/components/table.html
// here I got a basic feel for Swing

@SuppressWarnings("serial")
public class MainClass extends JFrame {

	JPopupMenu pmenu;
	
    public MainClass() {
        initToolbarUI();     
    }
    
    private void initToolbarUI() {
        JToolBar toolbar = new JToolBar();
        add(toolbar, BorderLayout.NORTH);
        
        ImageIcon icon = new ImageIcon("closeButtonRed.png");
        JButton exitButton = new JButton("Exit",icon);
        toolbar.add(exitButton);
        exitButton.setToolTipText("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        setTitle("Simple toolbar");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    @SuppressWarnings("unused")
	private final void initRightClickUI() {
    	pmenu = new JPopupMenu();
    	
        JMenuItem maxitem = new JMenuItem("Maximize");
        maxitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
        pmenu.add(maxitem);

        JMenuItem quititem = new JMenuItem("Quit");
        quititem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pmenu.add(quititem);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    pmenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        setTitle("JPopupMenu");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @SuppressWarnings("unused")
	private final void initMenubarUI() {

    	JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("closeButtonRed.png");

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

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

        menubar.add(file);

        setJMenuBar(menubar);

        setTitle("Simple menu");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    @SuppressWarnings("unused")
	private void initQuitButtonUI() {
    	 JPanel panel = new JPanel();
         getContentPane().add(panel);

         panel.setLayout(null);
         panel.setToolTipText("A Panel container");
         
         JButton quitButton = new JButton("Quit");
         quitButton.setBounds(50, 60, 80, 30);
         quitButton.setToolTipText("A Button component");
         
         quitButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent event) {
                 System.exit(0);
            }
         });

         panel.add(quitButton);
    	
        setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);   
    }
    

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainClass mc = new MainClass();
                mc.setVisible(true);
            }
        });
    }
}