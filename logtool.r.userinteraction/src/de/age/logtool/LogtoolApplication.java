package de.age.logtool;


public interface LogtoolApplication {
	
	void showWindow();
	
	void registerOperations(FileOperations fileOps);
	
	void registerSystemEvents(SystemEvents events);

}
