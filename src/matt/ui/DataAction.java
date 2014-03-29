package matt.ui;

/**
 * A quick hack. You can create a new object which extends this interface to
 * pass data behavior into a ButtonEditor table button.
 */
public interface DataAction {
	public Object act(Object data);
}