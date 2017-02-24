package com.lemon.commons.math;

import java.util.Random;

public class MyRandom {
	private static Random rand = new Random(System.currentTimeMillis());

	/**
	 * 前闭后开区间 [start, end)
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int randomInt(int start, int end) {
		if (end <= start) {
			return start;
		}

		return (int) (rand.nextDouble() * (end - start) + start);
	}

	/**
	 * 前闭后开区间 [start, end)
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static long randomLong(long start, long end) {
		if (end <= start) {
			return start;
		}

		return (long) (rand.nextDouble() * (end - start) + start);
	}
}
