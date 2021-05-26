package com.vision.universe.filters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vision.universe.RgbCounter;

import net.coobird.thumbnailator.filters.ImageFilter;

public class CleanupFilter2 implements ImageFilter {
	private double percent = 0.99;
	private boolean checking = true;
	private boolean arcOnly = false;
	private double keeping = 0.30;
	private int rad = 8;
	
	public CleanupFilter2() {
		
	}
	
	public CleanupFilter2(double percent) {
		this.percent = percent;
	}
	
	public CleanupFilter2(double percent,boolean checking) {
		this.percent = percent;
		this.checking = checking;
	}
	
	public CleanupFilter2(double percent,boolean checking,int rad) {
		this.percent = percent;
		this.checking = checking;
		this.rad = rad;
	}
	
	public CleanupFilter2(double percent,boolean checking,boolean arcOnly) {
		this.percent = percent;
		this.checking = checking;
		this.arcOnly = arcOnly;
		this.keeping = 0.15;
	}
	
	@Override
	public BufferedImage apply(BufferedImage g) {
		int w = g.getWidth();
		int h = g.getHeight();
		double counter = 0;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb == 255) {
					continue;
				}
				counter++;
			}
		}
		if(checking&&counter<h*w*keeping) {
			return g;
		}
		Map<Integer,RgbCounter> bitSet = new LinkedHashMap<>();
		Map<Integer,Boolean> cleanSet = new LinkedHashMap<>();
		for (int y = 0; y < h; y += 1) {
			for (int x = 0; x < w; x += 1) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb != 255) {
					int minX = Math.max(0, x - rad);
					int maxX = Math.min(w, x + rad);
					int minY = Math.max(0, y - rad);
					int maxY = Math.min(h, y + rad);
					double sum = 0;
					int count = 0;
					for (int i = minX; i < maxX; i++) {
						for (int k = minY; k < maxY; k++) {
							if (Math.sqrt(Math.pow(y - k, 2) + Math.pow(x - i, 2)) < rad) {
								int r = g.getRGB(i, k) & 0xff;
								sum+=r;
								if(r!=255) {
									count++;
								}
							}
						}
					}
					boolean bit = sum / (255*(Math.PI * Math.pow(rad, 2))) > percent;
					if(arcOnly&&count>Math.PI * Math.pow(rad, 2) * 0.95) {
						if(!bitSet.containsKey(rgb)) {
							bitSet.put(rgb, new RgbCounter(rgb));
						}
						bitSet.get(rgb).incr();
					}
					if(bit) {
						cleanSet.put(rgb, true);
					}
				}
			}
		}
		if(!bitSet.isEmpty()) {
			List<RgbCounter> list = new ArrayList<>(bitSet.values());
			Collections.sort(list);
			int sum = 0;
			for(RgbCounter r : list) {
				sum+=r.get();
			}
			Iterator<RgbCounter> it = bitSet.values().iterator();
			int sub = 0;
			while(it.hasNext()) {
				RgbCounter r = it.next();
				if(sub>=sum*0.3) {
					it.remove();
				}else {
					sub+=r.get();
				}
			}
		}
		for (int y = 0; y < h; y += 1) {
			for (int x = 0; x < w; x += 1) {
				int rgb = g.getRGB(x, y) & 0xff;
				if(bitSet.containsKey(rgb)||cleanSet.containsKey(rgb)) {
					g.setRGB(x, y, 0xffffff);
				}
			}
		}
		return g;
	}

}

