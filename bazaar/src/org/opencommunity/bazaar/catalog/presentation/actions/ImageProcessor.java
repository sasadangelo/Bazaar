package org.opencommunity.bazaar.catalog.presentation.actions;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageProcessor {
	private static int THUMBNAIL_HEIGHT = 100;

	static BufferedImage thumbnail(Image image) {
		try {
			MediaTracker mediaTracker = new MediaTracker(new Container());
			mediaTracker.addImage(image, 0);
			mediaTracker.waitForID(0);
	
			// determine the image ration
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			double imageRatio = (double) imageWidth / (double) imageHeight;
	
			// calculate size for thumbnail
			int thumbHeight = THUMBNAIL_HEIGHT;
			int thumbWidth = (int) (thumbHeight * imageRatio);
	
			// create the thumbnail image
			BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = thumbImage.createGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
			
			return thumbImage;
		} catch (InterruptedException exception) {
			return null;
		}
		
/*		// put the image in the output stream
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
		//int quality = Integer.parseInt(args[4]);
		//quality = Math.max(0, Math.min(quality, 100));
		//param.setQuality((float) quality / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(thumbImage);
		return out;*/
	}
		/*	
	static ByteArrayOutputStream thumbnail(InputStream inStream) throws IOException, InterruptedException  {
		// load image from INFILE
		Image image = ImageIO.read(inStream);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		mediaTracker.waitForID(0);

		// determine the image ration
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;

		// calculate size for thumbnail
		int thumbHeight = THUMBNAIL_HEIGHT;
		int thumbWidth = (int) (thumbHeight * imageRatio);

		// create the thumbnail image
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		
		// put the image in the output stream
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
		//int quality = Integer.parseInt(args[4]);
		//quality = Math.max(0, Math.min(quality, 100));
		//param.setQuality((float) quality / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(thumbImage);
		return out;
	}*/
}