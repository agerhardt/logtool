package de.age.logtool;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.age.logtool.operations.DefaultFileOperations;
import de.age.logtool.operations.DefaultSystemEvents;
import de.age.logtool.parsing.LogParser;
import de.age.logtool.parsing.websphere.WebsphereLogModule;
import de.age.logtool.swing.SwingApplicationModule;

public class Main {

	private final LogtoolApplication application;
	private final LogParser logParser;
	
	@Inject
	public Main(LogtoolApplication application, LogParser logParser) {
		this.application = application;
		this.logParser = logParser;
	}
	
	public void start() {
		application.registerOperations(new DefaultFileOperations());
		application.registerSystemEvents(new DefaultSystemEvents());
		application.showWindow();
	}
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new SwingApplicationModule(), new WebsphereLogModule());
		Main mainClass = injector.getInstance(Main.class);
		mainClass.start();
	}
}
