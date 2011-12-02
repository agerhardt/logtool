package de.age.logtool.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Helper class that will append to a file. The file is kept open until closed.
 */
public class FileCreator {

	private final ContentCreator contentCreator;
	private final Writer writer;
	private boolean writerOpen;
	
	public FileCreator(String filename, ContentCreator contentCreator) {
		this.contentCreator = contentCreator;
		try {
			writer = new FileWriter(filename);
			writerOpen = true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void handleException(IOException exc) {
		writerOpen = false;
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
			}
		}
		exc.printStackTrace();
	}
	
	public void append() {
		if (!writerOpen) {
			throw new IllegalStateException("Writer is already closed");
		}
		try {
			writer.append(contentCreator.createContent());
		} catch (IOException e) {
			handleException(e);
		}
	}
	
	public void closeWriter() {
		try {
			writer.close();
		} catch (IOException e) {
			handleException(e);
		}
	}
	
}
