package de.age.logtool;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.age.logtool.display.ApplicationWindow;
import de.age.logtool.operations.DefaultFileOperations;
import de.age.logtool.operations.DefaultSystemEvents;
import de.age.logtool.parsing.LogParser;
import de.age.logtool.parsing.websphere.WebsphereLogModule;
import de.age.logtool.swing.SwingApplicationModule;

public class Main {

	private final ApplicationWindow window;
	private final LogParser logParser;
	
	@Inject
	public Main(ApplicationWindow window, LogParser logParser) {
		this.window = window;
		this.logParser = logParser;
	}
	
	public void start() {
		window.registerOperations(new DefaultFileOperations());
		window.registerSystemEvents(new DefaultSystemEvents());
		window.showWindow();
	}
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new SwingApplicationModule(), new WebsphereLogModule());
		Main mainClass = injector.getInstance(Main.class);
		mainClass.start();
	}
}
