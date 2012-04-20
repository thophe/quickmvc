package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
/**
 * A TimedAction is an action that's run after a given delay (in ms)
 * @author krm
 */
public class TimedAction implements ActionListener{
	/**
	 * Constructs a TimedAction from a delay and a Runnable action
	 * @param delay - how long to wait after start() is called before executing the action
	 * @param action - the action to perform at the end of the delay
	 */
	public TimedAction(int delay, Runnable action) {
		this.delay = delay;
		this.action = action;
	}
	
	private Timer timer;
	private int delay;
	private Runnable action;
	
	/**
	 * Start the delayed action
	 */
	public void start() {
		timer = new Timer(delay, this);
		timer.start();
	}

	/**
	 * Call this if you only wish to execute the action, no delay
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		action.run();
		if (timer != null && timer.isRunning())
			timer.stop();
	}
	
	
	
}
