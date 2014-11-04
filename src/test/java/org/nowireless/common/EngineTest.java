package org.nowireless.common;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.nowireless.common.engine.Engine;

public class EngineTest {

	private final Logger log = LogManager.getLogger(this.getClass().getSimpleName());
	
	@Test
	public void test() {
		Engine engine = new TestEngine();
		engine.startEngine();
		
		long start = System.currentTimeMillis();
		
		boolean stopped = false;
		
		while(engine.isRunning()) {
			long delta = System.currentTimeMillis() - start;
			if(delta > 1000 && !stopped) {
				log.info("Stopping engine");
				engine.stopEngine();
				stopped = true;
			} else if(delta > 2000) {
				log.fatal("Could not stop engine in time");;
				fail("Engine did not stop in time");
			}
			this.sleep();
			log.trace("Delta {}", delta);
			
		}
		
		
	}
	
	private void sleep() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
