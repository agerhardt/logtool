package de.age.logtool.util;

import org.easymock.EasyMock;

import com.google.inject.AbstractModule;

import de.age.logtool.LogtoolApplication;
import de.age.logtool.parsing.LogParser;

public class TestApplicationModule extends AbstractModule {

	private final LogtoolApplication application;
	private final LogParser parser;
	
	public TestApplicationModule() {
		application = EasyMock.createNiceMock(LogtoolApplication.class);
		parser = EasyMock.createNiceMock(LogParser.class);
	}
	
	@Override
	protected void configure() {
		bind(LogtoolApplication.class).toInstance(application);
		bind(LogParser.class).toInstance(parser);
	}

	public LogtoolApplication getWindow() {
		return application;
	}
	
	public LogParser getParser() {
		return parser;
	}
	
	public void resetToNice() {
		EasyMock.resetToNice(application, parser);
	}

}
