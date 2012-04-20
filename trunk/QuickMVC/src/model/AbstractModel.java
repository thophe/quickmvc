package model;
import java.util.Observable;

import javax.swing.SwingUtilities;


import controller.AbstractController;
/**
 * An Observable model. Please use addController and notifyControllers to
 * send out messages to the controllers; they are Swing thread-safe
 * @author krm
 *
 */
public class AbstractModel extends Observable {

	public AbstractModel() {
		super();
	}

	public void addController(AbstractController controller) {
		this.addObserver(controller);
		
	}

	public void notifyControllers() {
		notifyControllers(null);
	}

	public void notifyControllers(final ModelMessage arg) {

		if (!SwingUtilities.isEventDispatchThread()) {
			Runnable r = new Runnable() {
				@Override
				public void run() {
					notifyControllers(arg);
				}
			};
			SwingUtilities.invokeLater(r);
			return;
		}
		setChanged();
		this.notifyObservers(arg);
	}

}