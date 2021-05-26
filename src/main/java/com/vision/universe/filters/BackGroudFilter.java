package com.vision.universe.filters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vision.universe.RgbCounter;

import net.coobird.thumbnailator.filters.ImageFilter;

public class BackGroudFilter implements ImageFilter {

	@Override
	public BufferedImage apply(BufferedImage g) {
		Map<Integer,Boolean> line = new LinkedHashMap<Integer, Boolean>();
		for (int j = 0; j < g.getWidth(); j++) {
			int rgb = g.getRGB(j, 0) & 0xff;
			line.put(rgb, true);
		}
		if(line.size()>8) {
			List<Integer> l = new ArrayList<Integer>(line.keySet());
			int rgb0 = l.get(0);
			for(int k=1;k<8;k++) {
				int rgb = l.get(k);
				if(rgb0+k!=rgb&&rgb0-k!=rgb) {
					return g;
				}
			}
		}
		line.clear();
		for (int j = 0; j < g.getHeight(); j++) {
			int rgb = g.getRGB(0, j) & 0xff;
			line.put(rgb, true);
		}
		if(line.size()>8) {
			List<Integer> l = new ArrayList<Integer>(line.keySet());
			int rgb0 = l.get(0);
			for(int k=1;k<8;k++) {
				int rgb = l.get(k);
				if(rgb0+k!=rgb&&rgb0-k!=rgb) {
					return g;
				}
			}
		}
		
		int total = 0;
		for (int i = 0; i < g.getHeight(); i++) {
			for (int j = 0; j < g.getWidth(); j++) {
				int rgb = g.getRGB(j, i) & 0xff;
				if(rgb==255) {
					total++;
				}
			}
		}
		if(total>g.getWidth()*g.getHeight()*0.3) {
			return g;
		}
		Map<Integer, Boolean> bitSet = new HashMap<>();
		int centerW = g.getWidth()/2;
		bitSet.putAll(clearHalf(g,0,0,centerW,g.getHeight()));
		bitSet.putAll(clearHalf(g,centerW,0,g.getWidth(),g.getHeight()));
		if(bitSet.size()>110) {
			return g;
		}
		if(!bitSet.isEmpty()) {
			for (int i = 0; i < g.getHeight(); i++) {
				for (int j = 0; j < g.getWidth(); j++) {
					int rgb = g.getRGB(j, i) & 0xff;
					if(bitSet.containsKey(rgb)) {
						g.setRGB(j, i, 0xffffff);
					}
				}
			}
		}
		for (int x = 0; x < g.getWidth(); x++) {
			int colCounter = 0;
			for (int y = 0; y < g.getHeight(); y++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb == 255) {
					continue;
				}
				colCounter++;
			}
			if(colCounter>g.getHeight()*0.9) {
				for (int y = 0; y < g.getHeight(); y++) {
					g.setRGB(x, y, 0xffffff);
				}
			}
		}
		for (int y = 0; y < g.getHeight(); y++) {
			int rowCounter = 0;
			for (int x = 0; x < g.getWidth(); x++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb == 255) {
					continue;
				}
				rowCounter++;
			}
			if(rowCounter>g.getWidth()*0.9) {
				for (int x = 0; x < g.getWidth(); x++) {
					g.setRGB(x, y, 0xffffff);
				}
			}
		}
		return g;
	}
	
	private Map<Integer, Boolean> clearHalf(BufferedImage g,int startX,int startY,int endX,int endY) {
		Map<Integer, Boolean> bitSet = new HashMap<>();
		for(int y=startY;y<endY;y++) {
			Map<Integer, RgbCounter> temp = new LinkedHashMap<>();
			for(int x=startX;x<endX;x++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb != 255) {
					if(!temp.containsKey(rgb)) {
						temp.put(rgb, new RgbCounter(rgb));
					}
					temp.get(rgb).incr();
				}
			}
			if(temp.isEmpty()) {
				continue;
			}
			List<Integer> l = new ArrayList<Integer>(temp.keySet());
			int start = 0;
			int c = 0;
			int rgb0 = l.get(0);
			for(int i=1;i<l.size();i++) {
				if(rgb0+(i-start)==l.get(i)||rgb0-(i-start)==l.get(i)) {
					c++;
				}else {
					if(c>3) {
						for(Integer r :l.subList(start, start+c+1)) {
							bitSet.put(r,true);
						}
					}
					start = i;
					rgb0 = l.get(i);
					c=0;
				}
			}
			if(c>0) {
				for(Integer r :l.subList(start, start+c+1)) {
					bitSet.put(r,true);
				}
			}
		}
		return bitSet;
	}

}
