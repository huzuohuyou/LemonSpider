package com.lemon.commons.file;

public class MainPasswordComponents {

	private byte part0;
	private byte part1;
	private byte part2;
	private byte part3;
	private byte part4;
	private byte part5;
	private byte part6;
	private byte part7;
	private byte[] parts;

	public MainPasswordComponents(int high, int low) {
		this.part0 = (byte) (((high & 0xFF000000) >> 24) & 0x000000FF);
		this.part1 = (byte) (((high & 0x00FF0000) >> 16) & 0x000000FF);
		this.part2 = (byte) (((high & 0x0000FF00) >> 8) & 0x000000FF);
		this.part3 = (byte) (high & 0x000000FF);
		this.part4 = (byte) (((low & 0xFF000000) >> 24) & 0x000000FF);
		this.part5 = (byte) (((low & 0x00FF0000) >> 16) & 0x000000FF);
		this.part6 = (byte) (((low & 0x0000FF00) >> 8) & 0x000000FF);
		this.part7 = (byte) (low & 0x000000FF);
		parts = new byte[]{part0, part1, part2, part3, part4, part5, part6, part7};
	}

	public byte[] getByteArrays() {
		return parts;
	}

	public String toString() {
		String msg = this.part0 + "-" + this.part1 + "-" + this.part2 + "-" + this.part3 + "-" + this.part4 + "-" + this.part5 + "-" + this.part6 + "-" + this.part7;
		return msg;
	}
}
