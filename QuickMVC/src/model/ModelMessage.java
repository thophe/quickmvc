package model;

import controller.EventType;
/**
 * Represents an event sent by the model
 * @author krm
 *
 */
public class ModelMessage {
	
	private EventType type;
	private Object argument;
	
	public ModelMessage(EventType type, Object argument){
		this.type = type;
		this.argument = argument;
	}

	public EventType getType() {
		return type;
	}

	public Object getArgument() {
		return argument;
	}
}
