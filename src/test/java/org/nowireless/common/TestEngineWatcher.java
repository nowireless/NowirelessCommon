package org.nowireless.common;

import org.nowireless.common.engine.Engine;
import org.nowireless.common.engine.EngineWatcherAbstract;

public class TestEngineWatcher extends EngineWatcherAbstract {

	public TestEngineWatcher(long timeout, long sleep, Engine engine) {
		super(timeout, sleep, engine);
	}

	@Override
	public void overDue() {
		log().warn("Engine is over due");
	}

	@Override
	public void init() {
		log().info("Initializing: {}", this.getEngineName());
		
	}

	@Override
	public void deinit() {
		log().info("Deinitializing: {}", this.getEngineName());
		
	}

}
