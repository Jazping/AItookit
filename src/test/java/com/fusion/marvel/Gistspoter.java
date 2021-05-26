package com.fusion.marvel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.vision.universe.filters.BackGroudFilter;
import com.vision.universe.filters.CleanupFilter;
import com.vision.universe.filters.ReduceFilter;
import com.vision.universe.filters.SampleBgFilter;
import com.vision.universe.filters.SmartFilter;
import com.vision.universe.filters.TryCutFilter;
import com.vision.universe.utils.RandomUtils;
import com.vision.universe.utils.ResourceUtil;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

/**
 * showing image background reducing affect 
 * @author Jazping
 *
 */
public class Gistspoter {
	
	public static void main(String[]args) throws IOException {
		//set the local directory to save filtered images
		String saveDir = "D:\\temp";
		//deal with images with name from 01 to 09
		save(saveDir,filter(readSrcImage("/images/01.jpg"),256));
		save(saveDir,filter(readSrcImage("/images/02.jpg"),256));
		save(saveDir,filter(readSrcImage("/images/03.jpg"),256));
		save(saveDir,filter(readSrcImage("/images/04.jpg"),256));
		save(saveDir,filter(readSrcImage("/images/05.jpg"),256));
		save(saveDir,filter(readSrcImage("/images/06.jpg"),256));
		save(saveDir,filter(readSrcImage("/images/07.jpg"),256));
		save(saveDir,filter(readSrcImage("/images/08.jpg"),256));
		save(saveDir,filter(readSrcImage("/images/09.jpg"),256));
	}
	
	/**
	 * read image from class path
	 * @param name
	 * @return
	 * @throws IOException throw if any io exception occur
	 */
	private static BufferedImage readSrcImage(String name) throws IOException {
		InputStream stream = ResourceUtil.getStream(name);
		if(stream==null) {
			throw new IOException("Resource Not Found");
		}
		return ImageIO.read(stream);
	}
	
	/**
	 * filter image background with indicating size which proper for AI training
	 * @param image the buffer image
	 * @param size the fix size for output
	 * @return filtered image
	 * @throws IOException throw if any io exception occur
	 */
	private static BufferedImage filter(BufferedImage image,int size) throws IOException {
		if(image.getWidth()<256&&image.getHeight()<256) {
			throw new IOException("Image too small to deal with");
		}
		if(image.getWidth()<128||image.getHeight()<128) {
			throw new IOException("Image too small to deal with");
		}
		if(size<128||size>1024) {
			throw new IOException("Output image size from 128 to 1024");
		}
		Builder<BufferedImage> b = Thumbnails.of(image).size(size, size);
		b.addFilter(new SampleBgFilter()).addFilter(new BackGroudFilter());
		b.addFilter(new CleanupFilter(0.98,false));
		b.addFilter(new TryCutFilter(null,size,size));
		b.addFilter(new ReduceFilter(-1));
		b.addFilter(new SmartFilter());
		b.imageType(BufferedImage.TYPE_BYTE_GRAY);
		return b.asBufferedImage();
	}
	
	/**
	 * save reducing image to the target directory
	 * @param dir the directory
	 * @param img the image to save
	 * @throws IOException throw if any io exception occur
	 */
	private static void save(String dir, BufferedImage img) throws IOException {
		File directory = new File(dir);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		ImageIO.write(img, "jpg", new File(directory,RandomUtils.randomBase64UrlSafe("img", 8)+".jpg"));
	}
	
}
