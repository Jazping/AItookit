package com.vision.universe.filters;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;

import com.vision.universe.utils.RgbUtil;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.filters.ImageFilter;

public class TryCutFilter implements ImageFilter {
	private BufferedImage forcut;
	private int width = 0;
	private int height = 0;
	private double hrate;
	private double wrate;
	
	public TryCutFilter(BufferedImage forcut,int wrate,int hrate){
		this.forcut = forcut;
		if(forcut!=null) {
			this.width = forcut.getWidth();
			this.height = forcut.getHeight();
		}
		this.hrate = hrate;
		this.wrate = wrate;
	}
	@Override
	public BufferedImage apply(BufferedImage source) {
		try {
			BufferedImage img = Thumbnails.of(source).size(source.getWidth(), source.getHeight()).
					imageType(BufferedImage.TYPE_BYTE_GRAY).asBufferedImage();
			int w = img.getWidth();
			int h = img.getHeight();
			
			RgbUtil.clean(img,10);
			
			int minRow = Integer.MAX_VALUE;
			int maxRow = Integer.MIN_VALUE;
			int minCol = Integer.MAX_VALUE;
			int maxCol = Integer.MIN_VALUE;
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					int rgb = img.getRGB(x, y) & 0xff;
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
									if(RgbUtil.isWhite(img.getRGB(i, k))) {
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
			int h1 = h;
			int w1 = w;
			
			if(forcut!=null&&hrate>0&&wrate>0) {
				minRow = new BigDecimal(minRow*(height/hrate)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				maxRow = new BigDecimal(maxRow*(height/hrate)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				minCol = new BigDecimal(minCol*(width/wrate)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				maxCol = new BigDecimal(maxCol*(width/wrate)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				
				minRow = Math.max(0, minRow);
				maxRow = Math.min(height, maxRow);
				minCol = Math.max(0, minCol);
				maxCol = Math.min(width, maxCol);
				
				h1 = height;
				w1 = width;
			}
			
			if (maxRow - minRow < h1 || maxCol - minCol < w1) {
				if (maxRow - minRow < 64 && maxCol - minCol < 64) {
					throw new IllegalArgumentException("found unfeaturable image, it is too small.");
				}
			}
			BufferedImage target = source;
			if(forcut!=null&&hrate>0&&wrate>0) {
				target = forcut;
			}
			Builder<BufferedImage> b = Thumbnails.of(target).size(w1, h1);
			b.sourceRegion(minCol, minRow, maxCol - minCol, maxRow - minRow);
			return b.asBufferedImage();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}