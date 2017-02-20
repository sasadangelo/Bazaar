/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.presentation.actions;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.opencommunity.bazaar.catalog.business.IProductManager;
import org.opencommunity.bazaar.catalog.business.impl.ProductManagerDelegate;
import org.opencommunity.bazaar.catalog.dto.Product;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public final class ImageProductAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try {
			long nID = Long.parseLong(request.getParameter("nID"));
			boolean isThumbnail = Boolean.parseBoolean(request.getParameter("bThumbnail"));

			IProductManager services = new ProductManagerDelegate();
			Product product = services.getProduct(nID);
			
			BufferedImage image = null;
			
			if (product.getImgPhoto() != null) {
				if (isThumbnail) {
					image = product.getImgThumbnailPhoto();
				} else {
					image = product.getImgPhoto();
				}
			} else {
				image = ImageIO.read(new FileInputStream(request.getSession().getServletContext().getRealPath("images/no-photo.gif")));
			}

        	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);
    		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
    		encoder.setJPEGEncodeParam(param);
    		encoder.encode(image);
			
            response.setContentType("image/jpeg, image/gif");
			response.getOutputStream().write(outStream.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (BazaarException exception) {
			return null;
		} catch (RuntimeException exception) {
			return null;
		}
		return null;
	}
}
