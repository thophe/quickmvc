package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Application;
import util.TimedAction;
import view.extension.StatusBar;
import view.extension.JSwapPane.JSwapPane;
import controller.AbstractController;
import controller.EventType;

/**
 * The Main View for our application
 * keeps track of a frame and a status bar, as well as a main swap pane
 * @author krm
 *
 */
public class MainView extends AbstractView {

	public MainView(AbstractController controller) {
		super(controller);
	}

	private static JFrame frame;
	private static StatusBar statusBar;
	private static JSwapPane swapPane;
	private static MainView mainView;
	private JPanel rootPane;
	
	
	/*TESTING, you may remove this*/
	private JLabel testlabel;
	
	@Override
	public void createGUI() {
		mainView = this;
		
		rootPane = new JPanel(new BorderLayout(0,0));
		
		setupFrame();
		
		statusBar = new StatusBar();
		swapPane = new JSwapPane(0);
		
		createTestView();
		
		rootPane.add(swapPane,BorderLayout.CENTER);
		rootPane.add(statusBar, BorderLayout.PAGE_END);
		
	}

	/**
	 * Sets up a frame with a title, a remembered window size,
	 * an icon, and an event that's fired on exit
	 */
	private void setupFrame() {
		frame = new JFrame(Application.TITLE);
		frame.setSize(Application.get().windowSize);
		frame.setMinimumSize(Application.DEFAULT_WINDOW_SIZE);
		
		if (Application.get().windowPosition == null)
			frame.setLocationRelativeTo(null);
		else
			frame.setLocation(Application.get().windowPosition);
		
		frame.setIconImage(Application.ICON);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				MainView.this.fireViewEvent(mainView, EventType.EXIT, null);
			}
			
		});
		frame.setContentPane(rootPane);
		setParentContainer(frame);
	}

	/**
	 * @return the application's main frame
	 */
	public static JFrame getFrame() {
		return frame;
	}

	/**
	 * Swap to a place in the swap pane
	 * @param index
	 */
	public void swapTo(int index) {
		swapPane.setViewed(index);
		
	}
	
	/**
	 * Retrieve the current index of the swap pane
	 * @return the swap pane's position
	 */
	public static int getMode() {
		return swapPane.getViewed();
	}

	/**
	 * Sets the title extension
	 * @param title - the title you wish to display
	 */
	public static void setFrameTitle(String title) {
		frame.setTitle(Application.TITLE + " - " + title);
		
	}


	@Override
	public void setStatusBarListeners() {
		//a place to set all of your status bar listeners
	}

	
	/*TESTING, you may remove this*/
	public void testDisplay(String msg) {
		final String prevText = testlabel.getText();
		testlabel.setText(msg);
		new TimedAction(1000, new Runnable() {
			
			@Override
			public void run() {
				testlabel.setText(prevText);
				
			}
		}).start();
			
		
	}
	
	/*TESTING, you may remove this*/
	private void createTestView() {
		JPanel test = new JPanel();
		JButton button = new JButton("click me!");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireStaticViewEvent(mainView, EventType.TEST_EVENT, "woah!");
			}
		});
		
		testlabel = new JLabel("click that button");
		
		StatusBar.addStatusListener(button, "you just hovered over a button");
		
		test.add(button);
		test.add(testlabel);
		
		swapPane.add(test);
	}
	
}
