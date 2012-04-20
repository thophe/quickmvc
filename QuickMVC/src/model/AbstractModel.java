 /* Copyright 2012 Kristofer Mitchell

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/package model;
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