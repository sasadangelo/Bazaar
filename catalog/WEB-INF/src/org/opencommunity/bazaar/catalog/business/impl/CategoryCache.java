/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.opencommunity.bazaar.catalog.business.ICategoryManager;
import org.opencommunity.bazaar.catalog.business.ICategoryPage;
import org.opencommunity.bazaar.catalog.dto.Category;
import org.opencommunity.bazaar.catalog.dto.CategoryNode;
import org.opencommunity.bazaar.catalog.exceptions.CategoryManagerException;
import org.opencommunity.bazaar.catalog.integration.dao.CategoryDAOFactory;
import org.opencommunity.bazaar.catalog.integration.dao.ICategoryDAO;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.integration.dao.ITransactionManager;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

class CategoryCache implements ICategoryManager {
	
	static CategoryCache getInstance() throws CategoryManagerException {
	    if (instance == null) {
	    	synchronized(CategoryCache.class) {
	    		if (instance == null) {
	    			instance = new CategoryCache();
	    		}
	    	}
	    }
	    return instance;
	}
	
	private CategoryCache() throws CategoryManagerException {
		List list = null;
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();

        try {
            tm.begin();
            ICategoryDAO dao = CategoryDAOFactory.getCategoryDAO();
        	list = dao.findAll();
       		tm.commit();
       		
       		// build cache by name and id.
       		buildCache(list);
        } catch (BazaarException exception) {
            tm.rollback();
            throw new CategoryManagerException(exception.getErrorCode());
        } catch (RuntimeException exception) {
            tm.rollback();
            throw new CategoryManagerException(IErrorCodes.ERR_INTERNAL);
        } finally {
            tm.end();
        }
	}
	
    public void create(Category category) throws CategoryManagerException {
    	cache.put(new Long(category.getNID()), category);
    	cacheByName.put(category.getTxtName(), category);
    	
    	CategoryNode categoryNode = new CategoryNode();
    	CategoryNode parentNode = null;
    	
    	categoryNode.setCategory(category);
    	if (category.getNParentID()==null) {
    		parentNode = (CategoryNode) cacheNode.get(new Long(0));
    		categoryNode.setParent(parentNode);
    	} else {
    		parentNode = (CategoryNode) cacheNode.get(category.getNParentID());
    		categoryNode.setParent(parentNode);
    	}
    	parentNode.getChilds().add(category);
    	cacheNode.put(new Long(category.getNID()), categoryNode);
    }
    
    public void update(Category category) throws CategoryManagerException {
    	Category categoryCheck = (Category) cache.get(new Long(category.getNID()));
    	categoryCheck.setTxtName(category.getTxtName());
    	categoryCheck.setTxtDescription(category.getTxtDescription());
    }

    public void delete(long key) throws CategoryManagerException {
    	Long oKey = new Long(key);
    	Category category = (Category) cache.remove(oKey);
    	cacheByName.remove(category.getTxtName());
    	CategoryNode categoryNode = (CategoryNode) cacheNode.remove(oKey);
    	CategoryNode parentNode = categoryNode.getParent();
    	parentNode.getChilds().remove(category);
    	categoryNode.setParent(null);
    	categoryNode.setCategory(null);
    	for (Iterator itr=categoryNode.getChilds().iterator(); itr.hasNext();) {
            Category childCategory = (Category) itr.next();
            deleteChild(childCategory.getNID());
    	}
    	categoryNode.getChilds().clear();
    }

    private void deleteChild(long key) throws CategoryManagerException {
    	Long oKey = new Long(key);
    	Category category = (Category) cache.remove(oKey);
    	cacheByName.remove(category.getTxtName());
    	CategoryNode categoryNode = (CategoryNode) cacheNode.remove(oKey);
    	categoryNode.setParent(null);
    	categoryNode.setCategory(null);
    	for (Iterator itr=categoryNode.getChilds().iterator(); itr.hasNext();) {
            Category childCategory = (Category) itr.next();
            deleteChild(childCategory.getNID());
    	}
    	categoryNode.getChilds().clear();
    }

    Category getByName(String name) throws CategoryManagerException {
    	return (Category) cacheByName.get(name);
    }

    public List getCategories(Long nParentId) throws CategoryManagerException {
    	if (nParentId == null) {
    		return rootNode.getChilds();
    	} else {
        	CategoryNode node = (CategoryNode) cacheNode.get(nParentId);
        	return node.getChilds();
    	}
    }

    public IDataListHandler getAllCategories(Long nParentId) throws CategoryManagerException {
    	if (nParentId == null) {
    		return new CategoryValueListHandler(rootNode.getChilds(), ICategoryPage.PAGE_LENGTH);
    	} else {
        	CategoryNode node = (CategoryNode) cacheNode.get(nParentId);
        	return new CategoryValueListHandler(node.getChilds(), ICategoryPage.PAGE_LENGTH);
    	}
    }

    public List getAllCategories() throws CategoryManagerException {
    	ArrayList list = new ArrayList();
    	for (Iterator itr=cache.values().iterator(); itr.hasNext();) {
    		list.add(itr.next());
    	}
    	return list;
    }

    public List getCategoryHierarchy(Long nParentId) throws CategoryManagerException {
    	ArrayList list = new ArrayList();
    	if (nParentId==null) {
    	    return list;
    	} else {
    		CategoryNode categoryNode = (CategoryNode) cacheNode.get(nParentId);
    		while (!categoryNode.isRoot()) {
        		list.add(0, categoryNode.getCategory());
        		categoryNode = categoryNode.getParent();
    		}
    	    return list;
    	}
    }
    
    public Category getCategory(long key) throws CategoryManagerException {
    	return (Category) cache.get(new Long(key));
    }

    private void buildCache(List list) {
		Category category = null;
		CategoryNode categoryNode = null;
		CategoryNode parentNode = null;

		// build cache by name and id.
   		for (Iterator itr=list.iterator(); itr.hasNext();) {
        	category = (Category) itr.next();
        	cache.put(new Long(category.getNID()), category);
        	cacheByName.put(category.getTxtName(), category);
        	categoryNode = new CategoryNode();
        	categoryNode.setCategory(category);
            cacheNode.put(new Long(category.getNID()), categoryNode);
        }
   		
   		// build category tree
		rootNode = new CategoryNode();
   		cacheNode.put(new Long(0), rootNode);
   		for (Iterator itr=list.iterator(); itr.hasNext();) {
        	category = (Category) itr.next();
        	categoryNode = (CategoryNode) cacheNode.get(new Long(category.getNID()));
        	if (category.getNParentID()==null) {
        		categoryNode.setParent(rootNode);
        		rootNode.getChilds().add(category);
        	} else {
        		parentNode = (CategoryNode) cacheNode.get(category.getNParentID());
        		parentNode.getChilds().add(category);
        		categoryNode.setParent(parentNode);
        	}
        }
    }
    
    public List getCategoriesByProduct(long nProductID) throws CategoryManagerException {
    	throw new CategoryManagerException(IErrorCodes.ERR_FUNCTION_NOT_IMPLEMENTED);
    }
    
    private Hashtable cache = new Hashtable();
    private Hashtable cacheByName = new Hashtable();
    private Hashtable cacheNode = new Hashtable();
    private CategoryNode rootNode = null;
    private static CategoryCache instance = null;
}
