package de.age.logtool.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;

import de.age.logtool.LogfileListener;

public class TrackingLogfileListener implements LogfileListener, SelfDescribing {

	private final List<String> lines = new ArrayList<String>();
	
	@Override
	public void lineRead(String line) {
		lines.add(line);
	}

	public int getNumberOFLines() {
		return lines.size();
	}
	
	public List<String> getLines() {
		return Collections.unmodifiableList(lines);
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendValueList("[", ",", "]", lines);
	}

}
