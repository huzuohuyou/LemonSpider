package com.lemon.commons.file;

import java.util.ArrayList;
import java.util.List;

public class NSData {
	private int capicatiy;
	private List<Byte> list = null;

	public NSData(int initCapacity) {
		this.capicatiy = initCapacity;
		list = new ArrayList<Byte>(this.capicatiy);
	}

	public NSData(String rawdataFilePath) {
		byte[] bs = FileUtil.readRawData(rawdataFilePath);
		this.capicatiy = bs.length;
		list = new ArrayList<Byte>(this.capicatiy);
		appendBytes(bs);
	}

	public int appendBytes(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			list.add(bytes[i]);
		}
		return list.size();
	}

	public void appendByte(byte b) {
		list.add(b);
	}

	public int length() {
		return list.size();
	}

	public Byte[] getBytes() {
		Byte[] arr = (Byte[]) list.toArray();
		return arr;
	}

	public byte[] tobytearray() {
		byte[] bs = new byte[list.size()];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = list.get(i);
		}
		return bs;
	}

	public NSData encryptData(MainPasswordComponents mainPassword) {
		return NSData.encryptCompressData(this, mainPassword);
	}

	public static NSData encryptCompressData(NSData compressData, MainPasswordComponents mainPassword) {
		int totallen = compressData.length();
		byte[] byteArray = compressData.tobytearray();
		NSData encryptData = new NSData(totallen);
		byte[] mask = mainPassword.getByteArrays();

		for (int i = 0; i < totallen; i++) {
			byte b = byteArray[i];
			byte m = mask[i % 8];
			byte b2;
			b2 = (byte) (b ^ m & 0xFF);
			encryptData.appendByte((byte) b2);
		}
		return encryptData;
	}
}
