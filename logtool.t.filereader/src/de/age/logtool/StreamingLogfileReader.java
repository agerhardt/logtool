package de.age.logtool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.age.logtool.exceptions.IllegalLogfileListenerException;

/**
 * Watches a logfile and sends out events with new content when it becomes available.
 */
public class StreamingLogfileReader {

	private final Reader reader;
	private final List<LogfileListener> listeners;
	
	public StreamingLogfileReader(File file) {
		listeners = new ArrayList<LogfileListener>();
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException();
		}
		Timer timer = new Timer(true);
		TimerTask updateLogTask = new TimerTask() {
			
			@Override
			public void run() {
			}
		};
		timer.scheduleAtFixedRate(updateLogTask, 500, 200);
	}
	
	public void addLogfileListener(LogfileListener listener) {
		if (listener == null) {
			throw IllegalLogfileListenerException.nullListener();
		}
		listeners.add(listener);
	}
	
}
