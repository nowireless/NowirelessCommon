package org.nowireless.common.engine;

import org.nowireless.common.Initializable;
import org.nowireless.common.LoggerProvidor;
import org.nowireless.common.Task;

/**
 * A Engine is a repeating task that lives in its own thread.
 * @author nowireless
 *
 */
public interface Engine extends Runnable, Task, Initializable, LoggerProvidor {
	
	/**
	 * Starts the engine.
	 */
	public void startEngine();
	
	/**
	 * Stops the engine
	 */
	public void stopEngine();
	
	/**
	 * Stops the engine and waits for its {@link Thread} to join.
	 */
	public void stopEngineAndJoin() throws InterruptedException;
	
	/**
	 * Gets if the engine is still running
	 * @return
	 */
	public boolean isRunning();
	
	/**
	 * Gets the thread that the engine is running in.
	 * @return
	 */
	public Thread getEngineThread();
	
	/**
	 * Gets the name of the engine.
	 * @return
	 */
	public String getEngineName();
}
