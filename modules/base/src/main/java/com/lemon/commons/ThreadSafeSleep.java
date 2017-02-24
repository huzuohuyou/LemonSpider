package com.lemon.commons;

public class ThreadSafeSleep {

	public static boolean sleep(long timeInterval) {
		try {
			Thread.sleep(timeInterval);
			return true;
		} catch (InterruptedException e) {
			return false;
		}
	}
}
