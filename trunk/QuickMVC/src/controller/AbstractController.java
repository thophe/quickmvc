package controller;


import java.util.Observable;
import java.util.Observer;

import view.AbstractView;

import model.Model;
import model.ModelMessage;

/**
 * An abstract controller class. Observes the singleton model.
 * Controllers must implement updated() and handleEvent()
 * @author krm
 *
 */
public abstract class AbstractController implements Observer{
	protected static Model model;
	protected AbstractView view;
	
	public AbstractController(){
		model = Model.getModel();
		model.addController(this);
		
	}
	
	@Override
	public void update(Observable observable, Object message) {
		
		ModelMessage mm = (ModelMessage) message;
		if (mm == null){
			updated(EventType.UNTYPED, null);
			return;
		}
		
		updated(mm.getType(),mm.getArgument());
	}	

	public void setView(AbstractView v){
		view = v;
	}
	
	/**
	 * Fired when the model updates this controller
	 * @param type - the type of update
	 * @param arg - any optional message
	 */
	public abstract void updated(EventType type, Object arg);
	
	/**
	 * Fired by a view. Return true if you're certain you've handled this event
	 * @param source - the view that fired the event
	 * @param type - the type of event fired
	 * @param message - an optional message
	 * @return true if you have handled, false if you wish to pass on
	 */
	public abstract boolean handleEvent(AbstractView source, EventType type, Object message);
	
}
