package com.vision.universe.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomUtils {
	
	private static final SecureRandom SECURERANDOM = new SecureRandom();
	
	public static String randomBase64UrlSafe(String spi,int len) {
		byte[] random = new byte[len];
		SECURERANDOM.nextBytes(random);
		byte[] spiBytes = spi.getBytes();
		byte[] bytes = new byte[random.length+spiBytes.length];
		System.arraycopy(spiBytes, 0, bytes, 0, spiBytes.length);
		System.arraycopy(random, 0, bytes, spiBytes.length, random.length);
		return Base64.getUrlEncoder().encodeToString(bytes);
	}
	
	public static void main(String[]args) {
		String str = RandomUtils.randomBase64UrlSafe("img", 50);
		System.err.println(str);
	}
	
}
