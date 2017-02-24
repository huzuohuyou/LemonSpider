package com.lemon.commons.math;

public class MathPlus {

	public static int min(int a, int b, int c) {
		int m = a<b?a:b;
		m = m<c?m:c;
		return m;
	}
}
