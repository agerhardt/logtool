package de.age.logtool.swing;

import com.google.inject.AbstractModule;

import de.age.logtool.LogtoolApplication;

public class SwingApplicationModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LogtoolApplication.class).to(SwingLogtoolApplication.class);
	}

}
