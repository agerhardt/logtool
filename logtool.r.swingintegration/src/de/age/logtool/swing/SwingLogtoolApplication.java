package de.age.logtool.swing;

import de.age.logtool.FileOperations;
import de.age.logtool.LogtoolApplication;
import de.age.logtool.SystemEvents;

public class SwingLogtoolApplication implements LogtoolApplication {

	private final SwingMainWindow swingWindow = null;
	
	@Override
	public void showWindow() {
		swingWindow.showWindow();
	}

	@Override
	public void registerOperations(FileOperations fileOps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerSystemEvents(SystemEvents events) {
		// TODO Auto-generated method stub
		
	}

}
