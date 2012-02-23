package de.age.logtool;


public interface ApplicationWindow {

	void showWindow();
	
	void registerOperations(FileOperations fileOps);
	
	void registerSystemEvents(SystemEvents events);
	
}