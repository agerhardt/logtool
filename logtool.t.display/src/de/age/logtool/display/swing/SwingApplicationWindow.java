package de.age.logtool.display.swing;

import javax.swing.JFrame;

import de.age.logtool.display.ApplicationWindow;

/**
 * Controller for the main application window
 */
class SwingApplicationWindow implements ApplicationWindow {
	
	private JFrame window;
	
	SwingApplicationWindow() {
	}
	
	protected JFrame getWindow() {
		if (window == null) {
			window = new JFrame();
		}
		return window;
	}
	
	@Override
	public void showWindow() {
		getWindow().setVisible(true);
	}

}
