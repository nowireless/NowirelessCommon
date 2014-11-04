package org.nowireless.common.engine;

/**
 * The WatchingEgineAbstarct extends the {@link EngineAbstract} while implementing the {@link WatchedEngine}.
 * @author nowireless
 *
 */
public abstract class WatchedEngineAbstact extends EngineAbstract implements WatchedEngine{
	
	private class InternalThreadWatcher extends EngineWatcherAbstract {

		public InternalThreadWatcher(long timeout) {
			super(timeout, 20, WatchedEngineAbstact.this);
		}

		@Override
		public void overDue() {
			log().info("OverDue!");
			WatchedEngineAbstact.this.overdue();
		}

		@Override
		public void init() {
			log().info("Init");
			
		}

		@Override
		public void deinit() {
			log().info("DeInit");
		}
		
	}
	
	private final EngineWatcher watcher;
	
	public WatchedEngineAbstact(long timeout) {
		watcher = new InternalThreadWatcher(timeout);
	}
	
	public WatchedEngineAbstact(long timeout, String threadName) {
		super(threadName);
		watcher = new InternalThreadWatcher(timeout);
	}
	
	@Override public abstract void init();
	@Override public abstract void deinit();
	@Override protected abstract long getSleepTime();
	
	@Override
	public void startEngine() {
		watcher.startEngine();
		super.startEngine();
	}
	
	@Override
	public void stopEngine() {
		watcher.stopEngine();
		super.stopEngine();
	}
	
	@Override
	public void stopEngineAndJoin() throws InterruptedException {
		try {
			watcher.stopEngineAndJoin();
		} catch (InterruptedException e) {
			log().warn("Interrupted, could not join with watcher Engine");
			throw new InterruptedException(e.toString());
		}
		super.stopEngineAndJoin();
	}
	
	@Override
	public EngineWatcher getWatcher() {
		return watcher;
	}
	
	@Override
	protected void preRunTask() {
		watcher.checkIn();
	}


	/**
	 * Called when the engine is timed out.
	 * Not Thread safe.
	 */
	protected abstract void overdue();

}
