package com.vision.universe.filters;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.vision.universe.exception.ImageException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.filters.ImageFilter;

public class SmartFilter implements ImageFilter {

	@Override
	public BufferedImage apply(BufferedImage g) {
		int w = g.getWidth();
		int h = g.getHeight();
		int minRow = Integer.MAX_VALUE;
		int maxRow = Integer.MIN_VALUE;
		int minCol = Integer.MAX_VALUE;
		int maxCol = Integer.MIN_VALUE;
		for (int y = 0; y < h; y+=1) {
			for (int x = 0; x < w; x+=1) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb != 255) {
					int minX = Math.max(0, x - 8);
					int maxX = Math.min(w, x + 8);
					int minY = Math.max(0, y - 8);
					int maxY = Math.min(h, y + 8);
					double sum = Math.PI * Math.pow(8, 2);
					boolean bit = true;
					out: for (int i = minX; i < maxX; i++) {
						for (int k = minY; k < maxY; k++) {
							if (Math.sqrt(Math.pow(y - k, 2) + Math.pow(x - i, 2)) < 8) {
								int r = g.getRGB(i, k) & 0xff;
								if(r!=255) {
									sum--;
								}
								if (sum / (Math.PI * Math.pow(8, 2)) < 0.96) {
									bit = false;
									break out;
								}
							}
						}
					}
					if (!bit) {
						minRow = Math.min(minRow, y-1);
						maxRow = Math.max(maxRow, y+1);
						minCol = Math.min(minCol, x-1);
						maxCol = Math.max(maxCol, x+1);
					}
				}
			}
		}
		minRow = Math.max(0, minRow);
		maxRow = Math.min(h, maxRow);
		minCol = Math.max(0, minCol);
		maxCol = Math.min(w, maxCol);
		
		if (maxRow - minRow < h || maxCol - minCol < w) {
			if (maxRow - minRow < 64 || maxCol - minCol < 64) {
				throw new ImageException("found unfeaturable image, it is too small.");
			}
			Builder<BufferedImage> b = Thumbnails.of(g).size(w, h);
			b.sourceRegion(minCol, minRow, maxCol - minCol, maxRow - minRow);
			try {
				return b.asBufferedImage();
			} catch (IOException e) {
				throw new ImageException(e);
			}
		}
		return g;
	}

}