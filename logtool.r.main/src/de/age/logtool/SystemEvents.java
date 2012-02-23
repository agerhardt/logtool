package de.age.logtool;

import de.age.logtool.display.LogEventListener;

/**
 * Interface for registering listeners to system-initiated events.
 */
public interface SystemEvents {
	
	void addLogEventListener(LogEventListener listener);

}
