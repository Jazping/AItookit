package com.vision.universe.filters;

import java.awt.image.BufferedImage;

import com.vision.universe.utils.RgbUtil;

import net.coobird.thumbnailator.filters.ImageFilter;

public class ReduceFilter implements ImageFilter {
	private int toRgb;
	public ReduceFilter(int toRgb) {
		this.toRgb = toRgb;
	}
	@Override
	public BufferedImage apply(BufferedImage g) {
		if (g.getType() != BufferedImage.TYPE_BYTE_GRAY) {
			throw new IllegalArgumentException("only gray to be perspective");
		}
		int prt = RgbUtil.caculatePrt(g);
		int w = g.getWidth();
		int h = g.getHeight();
		int c1 = 0;
		int c2 = 0;
		double total = 0;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb == 255) {
					continue;
				}
				total++;
				if (prt > rgb) {
					c1++;
				} else {
					c2++;
				}
			}
		}
		if(total<h*w*0.3) {
			return g;
		}
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb == 255) {
					continue;
				}
				boolean condition = (c1>c2 && rgb<=prt) || (c1<c2 && rgb>=prt);
				if (condition) {
					g.setRGB(x, y, 0xffffff);
				} else if (toRgb!=-1) {
					g.setRGB(x, y, toRgb);
				}
			}
		}
		return g;
	}

}
