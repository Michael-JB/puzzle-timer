package uk.co.ramyun.cube_timer.util;

public class Timer {
	/**
	 * @author © Michael
	 * @since 8 Mar 2017
	 * @file Timer.java
	 */

	private long period;
	protected long start;

	public Timer(long period) {
		this.period = period;
		start = System.currentTimeMillis();
	}

	public long getElapsed() {
		return System.currentTimeMillis() - start;
	}

	public long getRemaining() {
		return period - getElapsed();
	}

	public boolean isRunning() {
		return getElapsed() <= period;
	}

	public void reset() {
		start = System.currentTimeMillis();
	}

	public void stop() {
		period = 0;
	}
}