package com.vision.universe.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.vision.universe.RgbCounter;

import net.coobird.thumbnailator.Thumbnails;

public class RgbUtil {
	
	public static BufferedImage brightness(BufferedImage imgsrc, int brightness,int type) {
        try {
            BufferedImage back = new BufferedImage(imgsrc.getWidth(), imgsrc.getHeight(),type);
            int width = imgsrc.getWidth();  
            int height = imgsrc.getHeight();  
            for (int i = 0; i < height; i++) { 
                for (int j = 0; j < width; j++) {  
                    int pixel = imgsrc.getRGB(j, i);
                    if(!isWhite(pixel)) {
                    	Color color = new Color(pixel);
                    	int red= format(color.getRed()+brightness);
                    	int green= format(color.getGreen()+brightness);
                    	int blue= format(color.getBlue()+brightness);
                    	color = new Color(red,green,blue);
                    	int x=color.getRGB();
                    	back.setRGB(j,i,x);
                    }else {
                    	back.setRGB(j,i,0xffffff);
                    }
                }
            }
            return back;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	private static int format(int red) {
		if(red>255) {
			red=255; 
		}else if(red<0) {
			red=0;
		}
		return red;
	}
	
	public static Map<Integer, RgbCounter> newVector() {
		Map<Integer, RgbCounter> vector = new HashMap<>(255);
		for (int i = 0; i < 256; i++) {
			vector.put(i, new RgbCounter(i));
		}
		return vector;
	}
	
	public static BufferedImage reCodeImg(BufferedImage img) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, "jpg", os);
			return ImageIO.read(new ByteArrayInputStream(os.toByteArray()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean clean(BufferedImage g) {
		if(clean(g,10)) {
			return true;
		}
		double sum = 0;
		double x0 = g.getWidth()/2;
		double y0 = g.getHeight()/2;
		double min = y0;
		int counter = 0;
		for(int y=0;y<g.getHeight();y++) {
			for(int x=0;x<g.getWidth();x++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if(rgb!=255) {
					sum+=rgb;
					counter++;
				}
			}
		}
		double avg = sum/counter;
		double avgCounter = 0;
		for(int y=0;y<g.getHeight();y++) {
			for(int x=0;x<g.getWidth();x++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if(rgb>avg) {
					avgCounter++;
				}
			}
		}
//		double radio = avgCounter/(g.getWidth()*g.getHeight());
		double radio = avgCounter/counter;
		if(radio>0.8) {
			return false;
		}
		boolean cond = radio>0.5&&radio<0.8;
		double c = 0;
		for(int y=0;y<g.getHeight();y++) {
			for(int x=0;x<g.getWidth();x++) {
				int rgb = g.getRGB(x, y) & 0xff;
				double d = Math.sqrt(Math.pow(x0-x, 2)+Math.pow(y0-y, 2));
				if(cond) {
					if(rgb>avg||rgb+d>avg+min) {
						g.setRGB(x, y, 0xffffff);
						c++;
					}
				}else {
					if(rgb<avg||rgb-d<avg-min) {
						g.setRGB(x, y, 0xffffff);
						c++;
					}
				}
			}
		}
		double r = c/counter;
		return r>0.2;
	}
	
	public static boolean clean(BufferedImage g,int point) {
		Map<Integer, Boolean> bitSet = new HashMap<>();
		int total = 0;
		for(int y=0;y<g.getHeight();y++) {
			Map<Integer, Boolean> temp = new HashMap<>();
			int rowCount = 0;
			for(int x=0;x<g.getWidth();x++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if (rgb != 255) {
					rowCount++;
					temp.put(rgb, true);
				}
			}
			total+=rowCount;
			if(rowCount>g.getWidth()*0.5&&temp.size()<point) {
				bitSet.putAll(temp);
			}
		}
		double counter = 0;
		if(!bitSet.isEmpty()) {
			for (int i = 0; i < g.getHeight(); i++) {
				for (int j = 0; j < g.getWidth(); j++) {
					int rgb = g.getRGB(j, i) & 0xff;
					if(bitSet.containsKey(rgb)) {
						counter++;
						g.setRGB(j, i, 0xffffff);
					}
				}
			}
		}
		return counter/total>0.5;
	}
	
	public static int caculatePrt(BufferedImage g) {
		int w = g.getWidth();
		int h = g.getHeight();
		long sum = 0;
		int counter = 0;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int rgb = g.getRGB(x, y) & 0xff;
				if(rgb!=255) {
					sum += rgb;
					counter++;
				}
			}
		}
		return Long.valueOf(sum / counter).intValue();
	}
	
	/**
	 * force resize this image by Thumbnails algorithm
	 * @param image the image
	 * @param width to width
	 * @param height to height
	 * @return new BufferedImage
	 */
	public static BufferedImage forceResize(BufferedImage image, int width, int height) {
		return forceResize(image, width, height, BufferedImage.TYPE_BYTE_GRAY);
	}
	
	/**
	 * force resize this image by Thumbnails algorithm
	 * @param image the image
	 * @param width to width
	 * @param height to height
	 * @param type convert type
	 * @return
	 */
	public static BufferedImage forceResize(BufferedImage image, int width, int height, int type) {
		try {
			return Thumbnails.of(image).forceSize(width, height).imageType(type).asBufferedImage();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static BufferedImage resize(BufferedImage image, int width, int height) {
		return resize(image, width, height, BufferedImage.TYPE_BYTE_GRAY);
	}
	
	public static BufferedImage resize(BufferedImage image, int width, int height, int type) {
		try {
			return Thumbnails.of(image).size(width, height).imageType(type).asBufferedImage();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isWhite(int rgb) {
		int r = (rgb & 0xff0000) >> 16;
		int g = (rgb & 0xff00) >> 8;
		int b = (rgb & 0xff); 
		return 255==r&&255==g&&255==b;
	}
	
//	private static boolean isMatch(int rgb, int substract) {
//		int r = (rgb & 0xff0000) >> 16;
//		int g = (rgb & 0xff00) >> 8;
//		int b = (rgb & 0xff);
//		return (r-substract> g && r-substract > b && Math.abs(g-b)<15)
//				|| (g-substract> r && g-substract > b && Math.abs(r-b)<15)
//				|| (b-substract> r && b-substract > g && Math.abs(r-g)<15);
//	}
}
