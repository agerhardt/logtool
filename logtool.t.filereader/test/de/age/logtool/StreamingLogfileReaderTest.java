package de.age.logtool;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.age.logtool.exceptions.IllegalLogfileListenerException;
import de.age.logtool.util.ContentCreator;
import de.age.logtool.util.FileCreator;
import de.age.logtool.util.TrackingContentCreator;
import de.age.logtool.util.TrackingLogfileListener;

public class StreamingLogfileReaderTest {

	private ContentCreator content;
	private FileCreator source;
	private File tempFile;
	private TrackingLogfileListener listener;
	
	@Before
	public void setUp() throws Throwable {
		content = new TrackingContentCreator();
		tempFile = File.createTempFile("temptest", "log");
		source = new FileCreator(tempFile.getAbsolutePath(), content);
		listener = new TrackingLogfileListener();
	}
	
	@After
	public void tearDown() {
		source.closeWriter();
		content = null;
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
		Thread.sleep(500);
		assertThat(listener.getNumberOFLines(), is(1));
	}

}
