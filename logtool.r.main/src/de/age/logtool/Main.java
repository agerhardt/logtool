package de.age.logtool;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.age.logtool.display.ApplicationWindow;
import de.age.logtool.display.swing.SwingApplicationModule;

public class Main {

	private ApplicationWindow window;
	
	@Inject
	public Main(ApplicationWindow window) {
		this.window = window;
	}
	
	public void start() {
		window.showWindow();
	}
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new SwingApplicationModule());
		Main mainClass = injector.getInstance(Main.class);
		mainClass.start();
	}
}
