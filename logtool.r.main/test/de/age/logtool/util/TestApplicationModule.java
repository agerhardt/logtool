package de.age.logtool.util;

import org.easymock.EasyMock;

import com.google.inject.AbstractModule;

import de.age.logtool.display.ApplicationWindow;
import de.age.logtool.parsing.LogParser;

public class TestApplicationModule extends AbstractModule {

	private final ApplicationWindow window;
	private final LogParser parser;
	
	public TestApplicationModule() {
		window = EasyMock.createNiceMock(ApplicationWindow.class);
		parser = EasyMock.createNiceMock(LogParser.class);
	}
	
	@Override
	protected void configure() {
		bind(ApplicationWindow.class).toInstance(window);
		bind(LogParser.class).toInstance(parser);
	}

	public ApplicationWindow getWindow() {
		return window;
	}
	
	public LogParser getParser() {
		return parser;
	}
	
	public void resetToNice() {
		EasyMock.resetToNice(window, parser);
	}

}
