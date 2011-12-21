package de.age.logtool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringLogContentTest {
	
	@Test(expected = NullPointerException.class)
	public void constructorNullContentThrowsException() {
		new StringLogContent(null);
	}
	
	@Test
	public void constructorAcceptsEmptyContent() {
		new StringLogContent("");
	}
	
	@Test
	public void objectsWithIdenticalContentAreEqual() {
		String stringContent = "content";
		StringLogContent content1 = new StringLogContent(stringContent);
		StringLogContent content2 = new StringLogContent(stringContent);
		assertThat(content1, is(equalTo(content2)));
	}
	
	@Test
	public void objectsWithDifferentContentAreNotEqual() {
		StringLogContent content1 = new StringLogContent("content1");
		StringLogContent content2 = new StringLogContent("content2");
		assertThat(content1, is(not(equalTo(content2))));
	}
	
	@Test
	public void equalObjectsHaveSameHashcode() {
		String stringContent = "content";
		StringLogContent content1 = new StringLogContent(stringContent);
		StringLogContent content2 = new StringLogContent(stringContent);
		assertThat(content1.hashCode(), is(equalTo(content2.hashCode())));
	}
	
	@Test
	public void constructorArgumentIsReturned() {
		String stringContent = "content";
		StringLogContent content = new StringLogContent(stringContent);
		assertThat(content.getString(), is(equalTo(stringContent)));
	}
	
}
