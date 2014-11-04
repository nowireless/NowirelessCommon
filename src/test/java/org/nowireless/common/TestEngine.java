package org.nowireless.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nowireless.common.engine.EngineAbstract;

public class TestEngine extends EngineAbstract {

	private final Logger log = LogManager.getLogger(this.getClass().getSimpleName());
	
	@Override
	public void init() {
		log.info("Initializing");;
	}

	@Override
	public void deinit() {
		log.info("Deinitalizing");
	}

	@Override
	public void runTask() {
		log.info("Running Task");
	}

	@Override
	public String getEngineName() {
		return "TestEngine";
	}

	@Override
	protected long getSleepTime() {
		return 10;
	}
	
}