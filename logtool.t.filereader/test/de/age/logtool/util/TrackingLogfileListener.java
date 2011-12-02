package de.age.logtool.util;

import java.util.ArrayList;
import java.util.List;

import de.age.logtool.LogfileListener;

public class TrackingLogfileListener implements LogfileListener {

	private final List<String> lines = new ArrayList<String>();
	
	@Override
	public void lineRead(String line) {
		lines.add(line);
	}

	public int getNumberOFLines() {
		return lines.size();
	}

}
