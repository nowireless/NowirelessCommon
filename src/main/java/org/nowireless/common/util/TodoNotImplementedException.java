package org.nowireless.common.util;

import org.apache.commons.lang3.NotImplementedException;

/**
 * A convince Exception for marking TODO of unimplemented logic.
 * @author nowireless
 *
 */
public class TodoNotImplementedException extends NotImplementedException {


	private static final long serialVersionUID = 9094618762057161082L;

	public TodoNotImplementedException() {
		super("TODO");
	}

}
