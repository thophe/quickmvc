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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 * Encapsulates logic for code that you want run on a separate thread,
 * and then another bit of code that you want to run in the Event
 * Dispatch Thread when the first thread finishes. Allows for synchronous
 * and time-consuming actions to happen without consuming the UI thread.
 * @author krm
 *
 */
public class PostAction<T> {
	private Callable<T> threaded;
	private Runnable postToEDT;
	
	/**
	 * Construct a PostAction with a threaded Callable (Thread with a return object),
	 * and a Runnable that will be called after the thread returns on the EDT
	 */
	public PostAction(Callable<T> threaded, Runnable postToEDT){
		this.threaded = threaded;
		this.postToEDT = postToEDT;
	}
	/**
	 * Invoke the PostAction
	 * @return the result of the callable, specified by type parameters
	 * Will return null if thread is interrupted
	 */
	public T start(){
		
		SwingWorker<T, Void> worker = new SwingWorker<T, Void>(){


			protected T doInBackground() throws Exception {
				
				T result = threaded.call();
				
				SwingUtilities.invokeAndWait(postToEDT);
				return result;
			}
			
		};
		worker.execute();
		try {
			return worker.get();
		} catch (InterruptedException e) {
			return null;
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
