package de.age.logtool.util;

import org.easymock.EasyMock;

import com.google.inject.AbstractModule;

import de.age.logtool.display.ApplicationWindow;
import de.age.logtool.parsing.LogParser;

public class TestApplicationModule extends AbstractModule {

	private final ApplicationWindow window;
	private final LogParser parser;
	
	public TestApplicationModule() {
		window = EasyMock.createMock(ApplicationWindow.class);
		parser = EasyMock.createMock(LogParser.class);
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

}
