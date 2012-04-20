package view.extension;

import javax.swing.JOptionPane;

import view.MainView;


public class DialogFactory {

	public static void errorDialog(String msg) {
		JOptionPane.showMessageDialog(MainView.getFrame(), msg, "Error",
				JOptionPane.ERROR_MESSAGE);
		
	}

}
