package de.age.logtool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		timer.schedule(new FileSurveillanceTask(), 0, 200);
	}
	
	public void addLogfileListener(LogfileListener listener) {
		if (listener == null) {
			throw IllegalLogfileListenerException.nullListener();
		}
		listeners.add(listener);
	}
	
	private final class FileSurveillanceTask extends TimerTask {
		private final StringBuilder buffer = new StringBuilder();
		
		@Override
		public void run() {
			char[] characters = new char[1024];
			try {
				int read = reader.read(characters);
				if (read > 0) {
					buffer.append(characters, 0, read);
				}
				int endOfLine = buffer.indexOf("\n");
				if (endOfLine >= 0) {
					fireEvent(buffer.substring(0, endOfLine));
					buffer.delete(0, endOfLine + 1);
				}
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}

		private void fireEvent(String substring) {
			for (LogfileListener listener : listeners) {
				listener.lineRead(substring);
			}
		}
	}
		
}
