package com.lemon.commons.encoding;

public class Base64 {
	protected static char getChar(int sixbit) {
		if (sixbit >= 0 && sixbit <= 25) {
			return (char) (65 + sixbit);
		}

		if (sixbit >= 26 && sixbit <= 51) {
			return (char) (97 + (sixbit - 26));
		}

		if (sixbit >= 52 && sixbit <= 61) {
			return (char) (48 + (sixbit - 52));
		}

		if (sixbit == 62) {
			return CharPool.PLUS;
		}

		return sixbit != 63 ? CharPool.QUESTION : CharPool.SLASH;
	}

	protected static int getValue(char c) {
		if ((c >= CharPool.UPPER_CASE_A) && (c <= CharPool.UPPER_CASE_Z)) {
			return c - 65;
		}

		if ((c >= CharPool.LOWER_CASE_A) && (c <= CharPool.LOWER_CASE_Z)) {
			return (c - 97) + 26;
		}

		if (c >= CharPool.NUMBER_0 && c <= CharPool.NUMBER_9) {
			return (c - 48) + 52;
		}

		if (c == CharPool.PLUS) {
			return 62;
		}

		if (c == CharPool.SLASH) {
			return 63;
		}

		return c != CharPool.EQUAL ? -1 : 0;
	}

	public static String encode(byte raw[]) {
		return encode(raw, 0, raw.length);
	}

	public static String encode(byte raw[], int offset, int length) {
		int lastIndex = Math.min(raw.length, offset + length);

		StringBuilder sb = new StringBuilder(((lastIndex - offset) / 3 + 1) * 4);

		for (int i = offset; i < lastIndex; i += 3) {
			sb.append(encodeBlock(raw, i, lastIndex));
		}

		return sb.toString();
	}
	protected static char[] encodeBlock(byte raw[], int offset, int lastIndex) {
		int block = 0;
		int slack = lastIndex - offset - 1;
		int end = slack < 2 ? slack : 2;

		for (int i = 0; i <= end; i++) {
			byte b = raw[offset + i];

			int neuter = b >= 0 ? ((int) (b)) : b + 256;
			block += neuter << 8 * (2 - i);
		}

		char base64[] = new char[4];

		for (int i = 0; i < 4; i++) {
			int sixbit = block >>> 6 * (3 - i) & 0x3f;
			base64[i] = getChar(sixbit);
		}

		if (slack < 1) {
			base64[2] = CharPool.EQUAL;
		}

		if (slack < 2) {
			base64[3] = CharPool.EQUAL;
		}

		return base64;
	}
}
