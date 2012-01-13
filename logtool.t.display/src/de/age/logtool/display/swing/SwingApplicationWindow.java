package de.age.logtool.display.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import de.age.logtool.display.ApplicationWindow;
import de.age.logtool.display.FileOperations;
import de.age.logtool.display.SystemEvents;

/**
 * Controller for the main application window
 */
class SwingApplicationWindow implements ApplicationWindow {
	
	private JFrame window;
	private FileOperations fileOps;
	private SystemEvents systemEvents;
	
	static {
		setSystemLookAndFeel();
	}
	
	private static void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
	}
	
	SwingApplicationWindow() {
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
					fileOps.open(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		return toolbar;
	}

	@Override
	public void showWindow() {
		getWindow().setVisible(true);
	}

	@Override
	public void registerOperations(FileOperations fileOps) {
		this.fileOps = fileOps;
	}

	@Override
	public void registerSystemEvents(SystemEvents events) {
		this.systemEvents = events;
	}

}
