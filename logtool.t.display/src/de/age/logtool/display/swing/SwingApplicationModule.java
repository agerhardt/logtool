package de.age.logtool.display.swing;

import com.google.inject.AbstractModule;

import de.age.logtool.display.ApplicationWindow;

public class SwingApplicationModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ApplicationWindow.class).to(SwingApplicationWindow.class);
	}

}
