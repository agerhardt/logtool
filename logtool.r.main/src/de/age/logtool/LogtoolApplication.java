package de.age.logtool;

import de.age.logtool.display.ApplicationWindow;
import de.age.logtool.display.FileOperations;
import de.age.logtool.display.SystemEvents;

public interface LogtoolApplication {
	
	ApplicationWindow getWindow();
	
	void registerOperations(FileOperations fileOps);
	
	void registerSystemEvents(SystemEvents events);
	

}
