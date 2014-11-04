package org.nowireless.common.util;

public abstract class ShutDownHookAbstract extends Thread implements ShutDownHook {

	@Override
	public void register() {
		Runtime.getRuntime().addShutdownHook(this);
	}

	@Override
	public void unRegister() {
		Runtime.getRuntime().removeShutdownHook(this);
	}

	@Override
	public abstract void run();
}
