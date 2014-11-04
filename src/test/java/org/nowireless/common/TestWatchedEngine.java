package org.nowireless.common;

import org.nowireless.common.engine.WatchedEngineAbstact;

public class TestWatchedEngine extends WatchedEngineAbstact {

	public TestWatchedEngine(long timeout) {
		super(timeout);
	}

	@Override
	public void init() {
		log().info("Init");
		
	}

	@Override
	public void deinit() {
		log().info("DeInit");
	}

	@Override
	protected long getSleepTime() {
		return 10;
	}

	@Override
	public void runTask() {
		log().info("Runnning Task!");
	}

	@Override
	protected void overdue() {
		// TODO Auto-generated method stub
		
	}

}
