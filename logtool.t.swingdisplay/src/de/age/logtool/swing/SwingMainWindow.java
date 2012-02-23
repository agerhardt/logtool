package de.age.logtool.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.UIManager;

/**
 * Controller for the main application window
 */
public class SwingMainWindow {
	
	private JFrame window;
	
	static {
		setSystemLookAndFeel();
	}
	
	private static void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
	}
	
	SwingMainWindow() {
	}
	protected JFrame getWindow() {
		if (window == null) {
			window = new JFrame();
			window.getContentPane().setLayout(new BorderLayout());
			window.getContentPane().add(createToolbar(), BorderLayout.NORTH);
		}
		return window;
	}
	
	protected JToolBar createToolbar() {
		JToolBar toolbar = new JToolBar();
		// TODO fix this junk, we dont want code like that
		toolbar.add(new AbstractAction("Open") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION) {
//					fileOps.open(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		return toolbar;
	}

	public void showWindow() {
		getWindow().setVisible(true);
	}

}
