/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.presentation.forms;

import org.apache.struts.validator.ValidatorForm;

public final class SearchProductForm extends ValidatorForm {
    private String txtSearch;
    
	public String getTxtSearch() {
		return txtSearch;
	}
	public void setTxtSearch(String txtSearch) {
		this.txtSearch = txtSearch;
	}
}
