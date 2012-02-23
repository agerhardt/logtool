package de.age.logtool.parsing.websphere;

import com.google.inject.AbstractModule;

import de.age.logtool.parsing.LogParser;

public class WebsphereLogModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LogParser.class).to(WebsphereLogParser.class);
	}

}
