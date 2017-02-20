/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.presentation.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.opencommunity.bazaar.catalog.business.ICategoryManager;
import org.opencommunity.bazaar.catalog.business.impl.CategoryManagerDelegate;
import org.opencommunity.bazaar.catalog.dto.Category;
import org.opencommunity.bazaar.catalog.presentation.forms.EditCategoryForm;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

public final class EditCategoryAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionMessages errors = new ActionMessages();
		EditCategoryForm editCategoryForm = (EditCategoryForm) form;

		try {
			Category category = new Category(editCategoryForm.getNumId(), 
					editCategoryForm.getNumParent(),
					editCategoryForm.getTxtName(),
					editCategoryForm.getTxtName(),
					editCategoryForm.getTxtDescription());

			ICategoryManager services = new CategoryManagerDelegate();
			services.update(category);
		} catch (BazaarException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.EDIT_CATEGORY_FWD);
		} catch (RuntimeException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.EDIT_CATEGORY_FWD);
		}

		if (editCategoryForm.getNumParent()==null) {
			return mapping.findForward(ForwardKeys.LIST_CATEGORIES_FWD);
		} else {
			return new ActionForward("/adminListCategories.do?nParentId=" + editCategoryForm.getNumParent().longValue());
		}
	}
}
