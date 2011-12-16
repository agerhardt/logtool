package de.age.logtool.display;

import javax.swing.JFrame;

/**
 * Controller for the main application window
 */
public class ApplicationWindow {
	
	private JFrame window;
	
	protected JFrame getWindow() {
		if (window == null) {
			window = new JFrame();
		}
		return window;
	}

}
