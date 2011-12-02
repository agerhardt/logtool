package de.age.logtool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.age.logtool.exceptions.IllegalContentException;
import de.age.logtool.exceptions.IllegalTimestampException;

public class LogEntryTest {

	@Test(expected = IllegalTimestampException.class)
	public void constructorNullTimestampThrowsException() {
		new LogEntry(null, new LogContent());
	}
	
	@Test
	public void constructorNullTimestampExceptionMessage() {
		try {
			new LogEntry(null, new LogContent());
		} catch (IllegalTimestampException exc) {
			assertThat(exc.getMessage(), is(equalTo("Timestamp must not be <null>")));
		}
	}
	
	@Test(expected = IllegalContentException.class)
	public void constructorNullContentThrowsException() {
		new LogEntry(new Timestamp(), null);
	}
	
	@Test
	public void constructorNullContentExceptionMessage() {
		try {
			new LogEntry(new Timestamp(), null);
		} catch (IllegalContentException exc) {
			assertThat(exc.getMessage(), is(equalTo("Content must not be <null>")));
		}
	}
	
	@Test
	public void timestampGetsReturned() {
		Timestamp ts = new Timestamp();
		LogContent content = new LogContent();
		LogEntry entry = new LogEntry(ts, content);
		assertThat(entry.getTimestamp(), is(sameInstance(ts)));
	}
	
	@Test
	public void contentGetsReturned() {
		Timestamp ts = new Timestamp();
		LogContent content = new LogContent();
		LogEntry entry = new LogEntry(ts, content);
		assertThat(entry.getContent(), is(sameInstance(content)));
	}

}
