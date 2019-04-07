package uk.co.ramyun.cube_timer.util;

public class Time implements Comparable<Time> {
	/**
	 * @author © Michael
	 * @since 8 Mar 2017
	 * @file Time.java
	 */

	private long millis = 0L;

	public Time(long millis) {
		this.millis = millis;
	}

	public long getMillis() {
		return millis;
	}

	public String format() {
		final int sec = (int) (millis / 1000), h = sec / 3600 % 24, m = sec / 60 % 60, s = sec % 60;
		return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s) + ":"
				+ millis % 1000;
	}

	@Override
	public String toString() {
		return format();
	}

	@Override
	public int compareTo(Time o) {
		return Long.compare(millis, o.getMillis());
	}

}
