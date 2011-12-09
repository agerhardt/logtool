package de.age.logtool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.exceptions.IllegalLogfileListenerException;
import de.age.logtool.util.FileCreator;
import de.age.logtool.util.StringContentCreator;
import de.age.logtool.util.TrackingLogfileListener;
import de.age.testtools.TrackingContentCreator;

public class StreamingLogfileReaderTest {

	private TrackingContentCreator<String> content;
	private FileCreator source;
	private File tempFile;
	private TrackingLogfileListener listener;
	
	@Before
	public void setUp() throws Throwable {
		content = new StringContentCreator();
		tempFile = File.createTempFile("temptest", "log");
		source = new FileCreator(tempFile.getAbsolutePath(), content);
		listener = new TrackingLogfileListener();
	}
	
	@After
	public void tearDown() {
		source.closeWriter();
		content = null;
	}

	@Test(expected = NullPointerException.class)
	public void constructorNullFileThrowsException() {
		new StreamingLogfileReader(null);
	}
	
	@Test
	public void constructorNullFileExceptionMessage() {
		try {
			new StreamingLogfileReader(null);
		} catch (NullPointerException exc) {
			assertThat(exc.getMessage(), is(equalTo("File must not be <null>")));
		}
	}
	
	@Test
	public void fileCanGetOpened() {
		new StreamingLogfileReader(tempFile);
	}
	
	@Test(expected = IllegalLogfileListenerException.class)
	public void addingNullListenerThrowsException() {
		StreamingLogfileReader reader = new StreamingLogfileReader(tempFile);
		reader.addLogfileListener(null);
	}
	
	@Test
	public void addingNullListenerExceptionMessage() {
		StreamingLogfileReader reader = new StreamingLogfileReader(tempFile);
		try  {
		reader.addLogfileListener(null);
		} catch (IllegalLogfileListenerException exc) {
			assertThat(exc.getMessage(), is(equalTo("Listener must not be <null>")));
		}
	}
	
	@Test
	public void changesToFileFireEvent() throws InterruptedException {
		StreamingLogfileReader reader = new StreamingLogfileReader(tempFile);
		reader.addLogfileListener(listener);
		source.append();
		reader.checkFileForUpdates();
		assertThat(listener.getLines(), is(matchingContent()));
	}
	
	@Test
	public void onlyNewChangesToFileFireEvent() throws InterruptedException {
		source.append();
		StreamingLogfileReader reader = new StreamingLogfileReader(tempFile);
		reader.checkFileForUpdates();
		reader.addLogfileListener(listener);
		source.append();
		reader.checkFileForUpdates();
		assertThat(listener.getLines(), is(matchingContent(1)));
	}
	
	@Test
	public void readerDoesNotPreventFileFromBeingDeleted() {
		new StreamingLogfileReader(tempFile);
		source.closeWriter();
		assertThat(tempFile.delete(), is(true));
	}
	
	@Test
	public void correctDataGetsRead() throws InterruptedException {
		StreamingLogfileReader reader = new StreamingLogfileReader(tempFile);
		reader.addLogfileListener(listener);
		source.append();
		source.append();
		source.append();
		reader.checkFileForUpdates();
		assertThat(listener.getLines(), is(matchingContent()));
	}
	
	private Matcher<List<String>> matchingContent() {
		return content.matcher();
	}
	
	
	private Matcher<List<String>> matchingContent(int startingOffset) {
		return content.matcher(startingOffset);
	}
	
}
