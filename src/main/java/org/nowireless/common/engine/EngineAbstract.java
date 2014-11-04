package org.nowireless.common.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The EngineAbstract is a base class for implementing a {@link Engine}.
 * @author nowireless
 *
 */
public abstract class EngineAbstract extends Thread implements Engine {
	
	private transient volatile boolean isRunning = false;
	
	private transient final Logger log = LogManager.getLogger(this.getClass().getSimpleName());
	
	/**
	 * Creates a EngineAbstract with the a named Engine thread.
	 * @param threadName
	 */
	public EngineAbstract(String threadName) {
		super(threadName);
	}
	
	/**
	 * Creates a EngineAbstract
	 */
	public EngineAbstract() {
		super();
	}
	
	@Override public abstract void init();
	@Override public abstract void deinit();
	@Override public abstract void runTask();
	
	@Override 
	public String getEngineName() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public Logger log() {
		return log;
	}
	
	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public void startEngine() {
		isRunning = true;
		this.start();
	}

	@Override
	public void stopEngine() {
		this.interrupt();
	}
	
	@Override
	public void stopEngineAndJoin() throws InterruptedException {
		this.stopEngine();
		this.join();
	}

	@Override
	public final void run() {
		if(this.getSleepTime() <= 0) {
			log.fatal("Sleep time is lower than 0: {}", this.getSleepTime());
			isRunning = false;
			return;
		}
		
		this.init();
		
		while(true) {
			this.preRunTask();
			this.runTask();
			try {
				Thread.sleep(this.getSleepTime());
			} catch (InterruptedException e) {
				log.warn("{} has been interuped stoping...", this.getClass().getSimpleName());
				break;
			}
		}
		
		this.deinit();
		isRunning = false;
	}
	
	@Override
	public Thread getEngineThread() {
		return this;
	}
	
	/**
	 * Gets called right before runTask().
	 */
	protected void preRunTask() {
		
	}
	
	/**
	 * Gets the time for how long the engine should sleep.
	 * @return time in ms
	 */
	protected abstract long getSleepTime();

}
