/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sdangelo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CategoryNode {
    private Category category;
    private CategoryNode parent;
    private List childs = new ArrayList();
	
    /**
	 * @return Returns the category.
	 */
	public Category getCategory() {
		return category;
	}
	
	/**
	 * @param category The category to set.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
   
	/**
     * @return Returns the childs.
     */
    public List getChilds() {
	    return childs;
    }
    
    /**
     * @param childs The childs to set.
     */
    public void setChilds(List childs) {
	    this.childs = childs;
    }
    
    /**
     * @return Returns the parent.
     */
    public CategoryNode getParent() {
	    return parent;
    }
    
    /**
     * @param parent The parent to set.
     */
    public void setParent(CategoryNode parent) {
	    this.parent = parent; 
    }
    
    public boolean isRoot() {
    	return category==null && parent==null;
    }
}
