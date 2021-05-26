package com.vision.universe;


public class RgbCounter implements Comparable<RgbCounter> {
	int key = 0;
	int count = 0;

	public RgbCounter(int key) {
		this.key = key;
	}

	public void incr() {
		count++;
	}

	public void decr() {
		count--;
	}

	public int get() {
		return count;
	}
	
	public int key() {
		return key;
	}

	@Override
	public int compareTo(RgbCounter o) {
		return o.count - this.count;
	}

	@Override
	public String toString() {
		return String.format("key:%d count:%d", key,count);
	}
	
	
}
