/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.presentation.actions;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.opencommunity.bazaar.account.dto.Account;
import org.opencommunity.bazaar.catalog.business.IProductManager;
import org.opencommunity.bazaar.catalog.business.impl.ProductManagerDelegate;
import org.opencommunity.bazaar.catalog.dto.Product;
import org.opencommunity.bazaar.catalog.presentation.forms.EditProductForm;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

public final class EditProductAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionMessages errors = new ActionMessages();
		EditProductForm editProductForm = (EditProductForm) form;

        Account account = (Account) request.getSession(true).getAttribute("account"); 

		try {
			Product product = new Product();
			product.setNID(editProductForm.getNumId());
			product.setTxtName(editProductForm.getTxtName());
			product.setTxtShortDescription(editProductForm.getTxtShortDescription());
			product.setTxtDescription(editProductForm.getTxtDescription());
			product.setDblPrice(editProductForm.getDblPrice());
			product.setNumQuantity(editProductForm.getNumQuantity());
			product.setBActive(editProductForm.getTxtActive().equals("1"));
			product.setNAccountID(account.getNID());
			
			if (editProductForm.getTxtImgUrl().getFileSize() > 0) {
				BufferedImage image = ImageIO.read(editProductForm.getTxtImgUrl().getInputStream());
				editProductForm.getTxtImgUrl().getInputStream().close();
				product.setImgPhoto(image);
				product.setImgThumbnailPhoto(ImageProcessor.thumbnail(image));
			}
			
			ArrayList categoryIds = new ArrayList();
			
			StringTokenizer tokenizer = new StringTokenizer(editProductForm.getTxtAryCatIds(), ",");
			while (tokenizer.hasMoreTokens()) {
                categoryIds.add(new Long(Long.parseLong(tokenizer.nextToken())));
			}

			IProductManager services = new ProductManagerDelegate();
			services.update(categoryIds, product);
			
			return mapping.findForward(ForwardKeys.LIST_PRODUCTS_FWD);
		} catch (BazaarException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.EDIT_PRODUCT_FWD);
		} catch (RuntimeException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.EDIT_PRODUCT_FWD);
		}
	}
}
