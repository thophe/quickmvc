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

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel;

import view.MainView;
import view.extension.JSpacer;
import controller.MainController;
/**
 * Singleton class for application initialization and settings storage.
 * Supports self-serialization to store settings in a file on the filesystem
 * @author krm
 *
 */
public class Application implements Serializable {
	private static final long serialVersionUID = -7955277313153215335L;

	private static Application settings;

	
	
	public static final String FILE_PATH = System.getProperty("user.dir")
			+ File.separator + "files" + File.separator;
	public static final File SETTINGS_FILE = new File(FILE_PATH
			+ "../settings.tmp");
	
	public static Image ICON;
	
	public static final String TITLE = "APPLICATION_TITLE";
	public static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(647, 412);//Golden Ratio

	public static final int SWAP_TIME = 800;//Controls the speed of JSwapPanes

	
	
	/*Non-static (saved) settings*/
	public Dimension windowSize;
	public Point windowPosition;
	
	public Application() {
		windowSize = DEFAULT_WINDOW_SIZE;
		windowPosition = null; // for center; handled in MainView
	}
	
	/**
	 * Singleton access to the Application
	 * @return the Application
	 */
	public static Application get() {
		if (settings == null)
			settings = loadSettings();
		
		return settings;
	}

	/**
	 * Saves any necessary settings as a serialized object
	 * Call before exiting!
	 */
	public static void saveSettings() {
		Application s = get();
		//Save the window size and position
		s.windowSize = MainView.getFrame().getSize();
		s.windowPosition = MainView.getFrame().getLocation();
		
		Model.getModel();
		
		writeObject();
	}

	/**
	 * Loads settings, returns the newly loaded Application
	 * @return the loaded Application
	 */
	public static Application loadSettings() {

		try {
			ObjectInputStream objInStream = new ObjectInputStream(
					new FileInputStream(SETTINGS_FILE));
			Object newSettings = objInStream.readObject();

			if (newSettings != null && newSettings instanceof Application) {
				objInStream.close();
				return (Application) newSettings;
			}

			objInStream.close();

		} catch (FileNotFoundException e) {
			return new Application();

		} catch (Exception e) {
			e.printStackTrace();
			return new Application();
			
		}
		
		return null;
	}

	/**
	 * Writes out a settings file
	 */
	private static void writeObject() {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objOutStream = null;
		try {
			fileOutputStream = new FileOutputStream(SETTINGS_FILE);
			objOutStream = new ObjectOutputStream(fileOutputStream);
			objOutStream.writeObject(settings);
			objOutStream.close();
		} catch (Exception e) {
			// FAILED TO SAVE
			System.err.println("Serialization failed");

		}
	}
	
	/**
	 * Build a local resource URL, needed for deployment in a .jar
	 * @param name - the file name, in the directory /files
	 * @return a URL referring to the locally named resource (in /files)
	 */
	public static URL buildResource(String name){
		JSpacer j = new JSpacer(1,1);//need nonstatic reference class
		return j.getClass().getResource("/files/"+name);
	}

	public static void main(String args[]) {
		//Build image resources
		ICON = new ImageIcon(buildResource("icon.png")).getImage();
		
		//pre-construct an Application
		Application.get();
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					//initialize Substance
					JFrame.setDefaultLookAndFeelDecorated(true);
					UIManager.setLookAndFeel(new SubstanceGeminiLookAndFeel());
				} catch (Exception e) {
					System.out.println("Substance failed to initialize");
				}
				//start the application; build the model first, 
				//then build MainController, then build MainView
				//then do a notify to initialize controllers
				//then write out any settings
				Model m = Model.getModel();
				new MainView(new MainController());
				MainView.getFrame().setVisible(true);
				m.notifyControllers();
				
				Application.saveSettings();
			}
		};
		//run on EDT
		SwingUtilities.invokeLater(r);
		
	}
}
