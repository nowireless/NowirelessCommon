package org.nowireless.common.util;

public interface ShutDownHook extends Runnable {
	
	/**
	 * Registers the shutdown hook
	 */
	public void register();
	
	/**
	 * Unregersters teh shutdown hook
	 */
	public void unRegister();
	
}
