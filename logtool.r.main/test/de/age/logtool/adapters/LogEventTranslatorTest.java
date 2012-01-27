package de.age.logtool.adapters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.LogEntry;
import de.age.logtool.StringLogContent;
import de.age.logtool.Timestamp;
import de.age.logtool.display.DisplayLogEvent;

public class LogEventTranslatorTest {

	private LogEventTranslator translator;
	
	@Before
	public void setUp() {
		translator = new LogEventTranslator();
	}
	
	@After
	public void tearDown() {
		translator = null;
	}
	
	@Test
	public void timestampGetsTranslatedCorrectly() {
		LogEntry parserEvent = new LogEntry(Timestamp.current(), new StringLogContent("content"));
		DisplayLogEvent displayEvent = translator.translate(parserEvent);
		
	}

}
