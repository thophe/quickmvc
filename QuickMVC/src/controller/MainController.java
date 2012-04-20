 /* Copyright 2012 Kristofer Mitchell

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/package controller;
import model.Application;
import view.MainView;
import view.AbstractView;
/**
 * The main Controller for our application
 * @author krm
 *
 */
public class MainController extends AbstractController {

	@Override
	public boolean handleEvent(AbstractView source, EventType type, Object message) {
		
		MainView view = (MainView) this.view;
		
		switch (type){
		case TEST_EVENT:
			view.testDisplay(message.toString());
			break;
		case EXIT:
			Application.saveSettings();
			System.exit(0);
		}
		
		return false;
	}

	@Override
	public void updated(EventType type, Object arg) {
		System.out.println("ModelUpdated with " + type + " and " + arg);
		switch (type){
		
		}
		
	}


}
