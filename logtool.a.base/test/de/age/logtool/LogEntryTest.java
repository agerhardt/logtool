package de.age.logtool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.age.logtool.exceptions.IllegalContentException;
import de.age.logtool.exceptions.IllegalTimestampException;

public class LogEntryTest {

	@Test(expected = IllegalTimestampException.class)
	public void constructorNullTimestampThrowsException() {
		new LogEntry(null, content("content"));
	}
	
	@Test
	public void constructorNullTimestampExceptionMessage() {
		try {
			new LogEntry(null, content("content"));
		} catch (IllegalTimestampException exc) {
			assertThat(exc.getMessage(), is(equalTo("Timestamp must not be <null>")));
		}
	}
	
	@Test(expected = IllegalContentException.class)
	public void constructorNullContentThrowsException() {
		new LogEntry(Timestamp.current(), null);
	}
	
	@Test
	public void constructorNullContentExceptionMessage() {
		try {
			new LogEntry(Timestamp.current(), null);
		} catch (IllegalContentException exc) {
			assertThat(exc.getMessage(), is(equalTo("Content must not be <null>")));
		}
	}
	
	@Test
	public void timestampGetsReturned() {
		Timestamp ts = Timestamp.current();
		LogContent content = content("content");
		LogEntry entry = new LogEntry(ts, content);
		assertThat(entry.getTimestamp(), is(sameInstance(ts)));
	}
	
	@Test
	public void contentGetsReturned() {
		Timestamp ts = Timestamp.current();
		LogContent content = content("content");
		LogEntry entry = new LogEntry(ts, content);
		assertThat(entry.getContent(), is(sameInstance(content)));
	}

	@Test
	public void entriesWithSameTimestampAndContentAreEqual() {
		Timestamp ts = Timestamp.current();
		LogContent content = content("content");
		LogEntry entry1 = new LogEntry(ts, content);
		LogEntry entry2 = new LogEntry(ts, content);
		assertThat(entry1, is(equalTo(entry2)));
	}
	
	@Test
	public void entriesWithEqualTimestampAndContentAreEqual() {
		Timestamp ts1 = new Timestamp(100);
		Timestamp ts2 = new Timestamp(100);
		LogContent content1 = content("content");
		LogContent content2 = content("content");
		LogEntry entry1 = new LogEntry(ts1, content1);
		LogEntry entry2 = new LogEntry(ts2, content2);
		assertThat(entry1, is(equalTo(entry2)));
	}
	
	@Test
	public void entriesWithDifferentTimestampsAreNotEqual() {
		Timestamp ts1 = new Timestamp(100);
		Timestamp ts2 = new Timestamp(200);
		LogContent content = content("content");
		LogEntry entry1 = new LogEntry(ts1, content);
		LogEntry entry2 = new LogEntry(ts2, content);
		assertThat(entry1, is(not(equalTo(entry2))));
	}
	
	@Test
	public void entriesWithDifferentContentAreNotEqual() {
		Timestamp ts = Timestamp.current();
		LogContent content1 = content("content1");
		LogContent content2 = content("content2");
		LogEntry entry1 = new LogEntry(ts, content1);
		LogEntry entry2 = new LogEntry(ts, content2);
		assertThat(entry1, is(not(equalTo(entry2))));
	}
	
	@Test
	public void equalEntriesHaveSameHashcode() {
		Timestamp ts1 = new Timestamp(100);
		Timestamp ts2 = new Timestamp(100);
		LogContent content1 = content("content");
		LogContent content2 = content("content");
		LogEntry entry1 = new LogEntry(ts1, content1);
		LogEntry entry2 = new LogEntry(ts2, content2);
		assertThat(entry1.hashCode(), is(equalTo(entry2.hashCode())));
	}
	
	private static LogContent content(String s) {
		return new StringLogContent(s);
	}

}
