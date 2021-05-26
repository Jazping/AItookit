package com.vision.universe.filters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vision.universe.RgbCounter;

import net.coobird.thumbnailator.filters.ImageFilter;

public class SampleBgFilter implements ImageFilter {

	@Override
	public BufferedImage apply(BufferedImage g) {
		Map<Integer, Boolean> bitSet = new HashMap<>();
		int total = 0;
		for(int y=0;y<g.getHeight();y++) {
			Map<Integer, RgbCounter> temp = new HashMap<>();
			for(int x=0;x<g.getWidth();x++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb != 255) {
					if(!temp.containsKey(rgb)) {
						temp.put(rgb, new RgbCounter(rgb));
					}
					temp.get(rgb).incr();
				}else {
					total++;
				}
			}
			if(temp.size()<6) {
				List<RgbCounter> list = new ArrayList<>(temp.values());
				for(RgbCounter r : list) {
					bitSet.put(r.key(),true);
				}
			}
		}
		if(total>g.getWidth()*g.getHeight()*0.5) {
			return g;
		}
		for(int x=0;x<g.getWidth();x++) {
			Map<Integer, RgbCounter> temp = new HashMap<>();
			for(int y=0;y<g.getHeight();y++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb != 255) {
					if(!temp.containsKey(rgb)) {
						temp.put(rgb, new RgbCounter(rgb));
					}
					temp.get(rgb).incr();
				}
			}
			if(temp.size()<6) {
				List<RgbCounter> list = new ArrayList<>(temp.values());
				for(RgbCounter r : list) {
					bitSet.put(r.key(),true);
				}
			}
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
		return g;
	}

}