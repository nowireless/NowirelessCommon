package org.nowireless.common.util;

import org.apache.logging.log4j.LogManager;

public class ShutDownHookTest {
	
	public static class TestHook extends ShutDownHookAbstract {
		
		@Override
		public void run() {
			LogManager.getLogger().info("The Hook!!!");
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		new TestHook().register();
		
		Thread.sleep(200);
		
	}
}
