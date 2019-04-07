package uk.co.ramyun.cube_timer.util;

import java.util.concurrent.ThreadLocalRandom;

public class Random {
	/**
	 * @author © Michael
	 * @since 8 Mar 2017
	 * @file Random.java
	 */

	int last = 0;

	public Random() {

	}

	public int getLastGeneration() {
		return last;
	}

	public int generate(int min, int max) {
		last = ThreadLocalRandom.current().nextInt(min, max + 1);
		return last;
	}
}
