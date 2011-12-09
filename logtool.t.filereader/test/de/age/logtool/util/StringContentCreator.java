package de.age.logtool.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.age.testtools.TrackingContentCreator;

public class StringContentCreator extends TrackingContentCreator<String> {
	
	private static final SimpleDateFormat dateformat = new SimpleDateFormat("yyMMdd,HH:mm:ss.SSS");
	private int numEntries = 0;
	
	@Override
	public String createContent() {
		StringBuffer newContent = new StringBuffer();
		newContent.append(dateformat.format(new Date()));
		newContent.append(" :: Contententry [");
		newContent.append(numEntries++);
		newContent.append("]");
		return newContent.toString();
	}
	
}
