package org.nowireless.common.engine;

/**
 * The Watched Engine is a {@link Engine} that has a integrated {@link EngineWatcher} within it.
 * @author nowireless
 *
 */
public interface WatchedEngine extends Engine {
	
	/**
	 * Gets the Engine watcher watching this thread.
	 * @return The Engine Watcher
	 */
	public EngineWatcher getWatcher();
}
