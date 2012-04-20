package controller;
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
