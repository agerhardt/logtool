package de.age.logtool.experiments;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReaderExperiments {

	private File tempFile;
	private Reader reader;
	private Writer writer;

	@Before
	public void setUp() throws Throwable {
		tempFile = File.createTempFile("temptest", "log");
		writer = new FileWriter(tempFile);
		reader = new FileReader(tempFile);
	}

	@After
	public void tearDown() throws IOException {
		writer.close();
		reader.close();
	}

	@Test
	public void returnedIntCanBeCastToChar() throws IOException {
		char expectedChar = 'x';
		writer.write("" + expectedChar);
		writer.flush();
		char readChar = (char) reader.read();
		assertThat(readChar, is(equalTo(expectedChar)));
	}
	
	@Test
	public void readDoesNotBlockAtEndOfFile() throws IOException, InterruptedException {
		final AtomicBoolean readFlag = new AtomicBoolean(false);
		TimerTask readInBackground = new TimerTask() {
			
			@Override
			public void run() {
				try {
					reader.read();
					readFlag.set(true);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
		Timer timer = new Timer(true);
		timer.schedule(readInBackground, 0);
		Thread.sleep(100);
		assertThat("Read blocked instead of returning -1.", readFlag.get(), is(true));
	}
	
}
