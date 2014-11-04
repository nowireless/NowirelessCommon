package org.nowireless.common;

import static org.junit.Assert.*;

import org.junit.Test;
import org.nowireless.common.engine.WatchedEngine;
import org.nowireless.common.engine.WatchedEngineAbstact;

public class WatchedEngineTest {

	private class LockedWatchedThread extends WatchedEngineAbstact {

		public LockedWatchedThread(long timeout) {
			super(timeout, "LockedThread");
		}

		@Override
		public void init() {
			log().info("Init");
		}

		@Override
		public void deinit() {
			log().info("Deinit");
			
		}

		@Override
		protected long getSleepTime() {
			return 10;
		}

		@Override
		protected void preRunTask() {
		}
		
		@Override
		public void runTask() {
		}

		@Override
		protected void overdue() {
		}
		
	}
	
	@Test
	public void normal() throws InterruptedException {
		WatchedEngine engine = new TestWatchedEngine(200);
		
		engine.startEngine();
		
		long start = System.currentTimeMillis();
		while (engine.isRunning()) {
			long delta = System.currentTimeMillis() - start;
			if(delta > 1000) {
				break;
			}
			this.sleep();
		}
		
		engine.stopEngine();
		
		engine.getWatcher().getEngineThread().join();
		engine.getEngineThread().join();
		
		assertFalse(engine.isRunning());
		assertFalse(engine.getWatcher().isRunning());
		assertFalse(engine.getWatcher().isLocked());
	}
	
	@Test
	public void lockedUp() throws InterruptedException {
		WatchedEngine engine = new LockedWatchedThread(200);
		
		engine.startEngine();
		
		long start = System.currentTimeMillis();
		while (engine.isRunning()) {
			long delta = System.currentTimeMillis() - start;
			if(delta > 1000) {
				assertTrue(engine.getWatcher().isLocked());
				break;
			}
			this.sleep();
		}
		
		engine.stopEngine();
		
		engine.getWatcher().getEngineThread().join();
		engine.getEngineThread().join();
		
		assertFalse(engine.isRunning());
		assertFalse(engine.getWatcher().isRunning());
	
	}
	
	private void sleep() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
