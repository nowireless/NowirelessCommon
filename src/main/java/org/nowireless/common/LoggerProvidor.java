package org.nowireless.common;

import org.apache.logging.log4j.Logger;

/**
 * Allows a class to provide a {@link Logger} to its subclasses
 * @author nowireless
 *
 */
public interface LoggerProvidor {
	
	/**
	 * Gets the logger
	 * @return The logger
	 */
	public Logger log();
}
