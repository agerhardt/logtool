package de.age.logtool;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.age.logtool.display.FileOperations;
import de.age.logtool.util.TestApplicationModule;

public class MainTest {

	private TestApplicationModule module;
	private Main mainclass;
	
	@Before
	public void setUp() {
		module = new TestApplicationModule();
		Injector injector = Guice.createInjector(module);
		mainclass = injector.getInstance(Main.class);
	}
	
	@Test
	public void startingWillShowWindow() {
		module.getWindow().showWindow();
		EasyMock.replay(module.getWindow());
		mainclass.start();
		EasyMock.verify(module.getWindow());
	}
	
	@Test
	public void startingWillRegisterFileOperations() {
		module.getWindow().registerOperations(EasyMock.notNull(FileOperations.class));
		EasyMock.replay(module.getWindow());
		mainclass.start();
		EasyMock.verify(module.getWindow());
	}

}
