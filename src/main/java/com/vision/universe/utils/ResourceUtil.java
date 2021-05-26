package com.vision.universe.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class ResourceUtil {
	private ResourceUtil() {}
	public static InputStream getStream(String path) {
		return ResourceUtil.class.getResourceAsStream(path);
	}
	
	public static File getFile(String path) {
		URL url = ResourceUtil.class.getResource(path);
		String file = url.getFile();
		return new File(file);
	}
}
