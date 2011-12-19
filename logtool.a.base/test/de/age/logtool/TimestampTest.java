package de.age.logtool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimestampTest {
	
	@Test
	public void currentTimestampReturnsATimestamp() {
		Timestamp ts = Timestamp.current();
		assertThat(ts, is(notNullValue()));
	}
	
	@Test
	public void timestampsWithSameTimeAreEqual() {
		long time = 5000000;
		Timestamp ts1 = new Timestamp(time);
		Timestamp ts2 = new Timestamp(time);
		assertThat(ts1, is(equalTo(ts2)));
	}
	
	@Test
	public void equalTimestampsHaveSameHashcode() {
		long time = 5000000;
		Timestamp ts1 = new Timestamp(time);
		Timestamp ts2 = new Timestamp(time);
		assertThat(ts1.hashCode(), is(equalTo(ts2.hashCode())));
	}
	
	@Test
	public void timestampsAreOrdered() {
		Timestamp ts1 = new Timestamp(100);
		Timestamp ts2 = new Timestamp(200);
		assertThat(ts1.compareTo(ts2), is(-1));
		assertThat(ts2.compareTo(ts1), is(1));
		assertThat(ts1.compareTo(ts1), is(0));
	}
	
	@Test
	public void currentTimestampsAreOrdered() {
		Timestamp ts1 = Timestamp.current();
		Timestamp ts2 = Timestamp.current();
		assertThat(ts2.compareTo(ts1), is(not(-1)));
	}
	
}
