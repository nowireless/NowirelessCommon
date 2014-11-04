package org.nowireless.common;

import static org.junit.Assert.*;

import org.junit.Test;
import org.nowireless.common.engine.Engine;
import org.nowireless.common.engine.EngineWatcher;

public class EngineWatcherTest {

	public class DumbWatcher extends TestEngineWatcher {

		public DumbWatcher(long timeout, long sleep, Engine engine) {
			super(timeout, sleep, engine);
		}
		
	}
	
	public class TestEngine2 extends TestEngine {
		
		private EngineWatcher watcher;
		
		public void setWatcher(EngineWatcher watcher) {
			this.watcher = watcher;
		}
		
		@Override
		public void runTask() {
			super.runTask();
			watcher.checkIn();
		}
	}
	
	@Test
	public void lockedUp() {
		EngineWatcher watcher = new DumbWatcher(200, 10, new TestEngine());
		watcher.startEngine();
		
		boolean stopped = false;
		long start = System.currentTimeMillis();
		
		while(watcher.isRunning() && !stopped) {
			long delta = System.currentTimeMillis() - start;
			if(delta > 1000) {
				break;
			}
			this.sleep();
		}
		
		assertTrue(watcher.isLocked());
		
		watcher.stopEngine();
		try {
			watcher.getEngineThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void normal() {
		TestEngine2 engine = new TestEngine2();
		EngineWatcher watcher = new DumbWatcher(200, 10, engine);
		engine.setWatcher(watcher);
		
		watcher.startEngine();
		engine.startEngine();
	
		long start = System.currentTimeMillis();
		
		while(watcher.isRunning()) {
			long delta = System.currentTimeMillis() - start;
			assertFalse(watcher.isLocked());
			if(delta > 1000) {
				break;
			}
		}
		
		assertFalse(watcher.isLocked());
		
		watcher.stopEngine();
		engine.stopEngine();
		
		try {
			watcher.getEngineThread().join();
			engine.getEngineThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void sleep() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
