package view;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Iterator;

import controller.AbstractController;
import controller.EventType;

public abstract class AbstractView {
	private AbstractController controller;
	private Container parentContainer;
	private static ArrayList<AbstractView> allViews = new ArrayList<AbstractView>();

	public AbstractView(AbstractController controller) {
		allViews.add(this);
		this.controller = controller;
		createGUI();
		setStatusBarListeners();
		controller.setView(this);
	}

	public void setParentContainer(Container c) {
		this.parentContainer = c;
	}

	public Container getParentContainer() {
		return parentContainer;
	}
	
	/**
	 * Statically fires an event, first from the source view, and then from every other view as long
	 * as the event has not been handled
	 * @param source - the view the event originated from
	 * @param type - the type of event
	 * @param message - any message you wish to pass
	 * @return true if the event was handled, false otherwise
	 */
	public static boolean fireStaticViewEvent(AbstractView source, EventType type, Object message){
		
		boolean handled = source.fireShallowViewEvent(source, type, message);
		if (handled)
			return true;
		
		for (AbstractView v : allViews){
			if (source != v)
				handled = v.fireShallowViewEvent(source, type, message);
			
			if (handled)
				return true;
		}
		return false;
	}

	/**
	 * Non-statically fires a view event, first to our controller, then to everyone else's as 
	 * long as the event has not been handled
	 * @param source - the view the event originated from
	 * @param type - the type of event
	 * @param message - any message you wish to pass
	 * @return true if the event was handled, false otherwise
	 */
	public boolean fireViewEvent(AbstractView source, EventType type, Object message) {
		if (!controller.handleEvent(source, type, message)) {
			
			// if our controller can't handle it, ask around
			boolean handled = false;
			Iterator<AbstractView> i = allViews.iterator();

			while ((!handled) && i.hasNext()) {
				AbstractView v = i.next();

				if (!v.equals(this))
					handled = v.fireShallowViewEvent(source, type, message);
				
			}

			if (handled)
				return true;

		} else {
			return true;
		}
		return false;
	}

	private boolean fireShallowViewEvent(AbstractView source, EventType type,
			Object message) {
		
		return controller.handleEvent(source, type, message);
	}

	public void setController(AbstractController controller) {
		this.controller = controller;
	}

	public AbstractController getController() {
		return controller;
	}

	public abstract void createGUI();
	public abstract void setStatusBarListeners();
}
