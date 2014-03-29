package matt.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Table cells can define cell editors and renderers. In a nutshell, this
 * provides both, normally for using on the id/PK column to work on a tuple.
 * 
 * The CellEditor part is basically an editor factory, and its
 * getTableCellEditorComponent method basically returns the button the
 * editor uses, but with fineid set to the value of the cell it's called on.
 * 
 * The CellRenderer part is basically just the inert state of any cell.
 * Doesn't have to do anything.
 * 
 * It works, trust me. In other words, we're basically cheating by making a
 * cell editor (to get it applied to each cell) that's just a button that
 * grabs cell data and does whatever it wants with it.
 * 
 * Pared down from the example at
 * http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
 */
@SuppressWarnings("serial")
public class ButtonEditor extends DefaultCellEditor implements TableCellRenderer {
	protected JButton button;
	protected JButton displayButton;
	private Object data; //the value of the cell
	private String label = "";

	/** 
	 * this initializes the factory, and the button it returns. 
	 */
	public ButtonEditor(String label, final DataAction buttonAction) {
		super(new JCheckBox()); // don't ask
		button = new JButton();
		displayButton = new JButton();
		this.label = label;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAction.act(data);
				fireEditingStopped(); // this prevents the button cell from
										// being stuck in the edit state
										// (we're technically an editor)
			}
		});
	}

	/**
	 * This method returns the inert state of a button for presentation,
	 * which doesn't have to do anything. I could return my other button,
	 * but this causes some UI bugs (the button gets stuck depressed).
	 */
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		displayButton.setText(label);
		return displayButton;
	}

	/**
	 * whenever a button is clicked, we go into "edit" mode, and just return
	 * our button with appropriate data set.
	 */
	public Component getTableCellEditorComponent(JTable table,
			Object value, boolean isSelected, int row, int column) {
		data = value;
		button.setText(label);
		return button;
	}

	/**
	 * we need this to prevent things from going haywire. Probably because
	 * this is a hack around a checkbox editor.
	 */
	public Object getCellEditorValue() {
		return data;
	}
}