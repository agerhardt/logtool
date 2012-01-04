package de.age.logtool.util;

import org.easymock.EasyMock;

import com.google.inject.AbstractModule;

import de.age.logtool.display.ApplicationWindow;

public class TestApplicationModule extends AbstractModule {

	private final ApplicationWindow window;
	
	public TestApplicationModule() {
		window = EasyMock.createMock(ApplicationWindow.class);
	}
	
	@Override
	protected void configure() {
		bind(ApplicationWindow.class).toInstance(window);
	}

	public ApplicationWindow getWindow() {
		return window;
	}

}
