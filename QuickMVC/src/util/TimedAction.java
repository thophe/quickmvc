 /* Copyright 2012 Kristofer Mitchell

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/

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
