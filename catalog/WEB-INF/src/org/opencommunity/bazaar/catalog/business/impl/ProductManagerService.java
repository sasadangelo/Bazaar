/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.opencommunity.bazaar.catalog.business.IProductManager;
import org.opencommunity.bazaar.catalog.business.IProductPage;
import org.opencommunity.bazaar.catalog.dto.CategoryProduct;
import org.opencommunity.bazaar.catalog.dto.Product;
import org.opencommunity.bazaar.catalog.exceptions.ProductManagerException;
import org.opencommunity.bazaar.catalog.integration.dao.CategoryProductDAOFactory;
import org.opencommunity.bazaar.catalog.integration.dao.ICategoryProductDAO;
import org.opencommunity.bazaar.catalog.integration.dao.IProductDAO;
import org.opencommunity.bazaar.catalog.integration.dao.ProductDAOFactory;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.integration.dao.ITransactionManager;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;


public class ProductManagerService implements IProductManager {
    public void create(List categories, Product product) throws ProductManagerException {
        ITransactionManager tm = TransactionManagerFactory.getTransactionManager();

        try {
            tm.begin();
            
            // create product entry into the database
            IProductDAO dao = ProductDAOFactory.getProductDAO();
            dao.create(product);
            
            // create references to categories
            CategoryProduct catProd = null;
            ICategoryProductDAO catDao = CategoryProductDAOFactory.getCategoryProductDAO();

            for (Iterator itr = categories.iterator(); itr.hasNext();) {
            	Long categoryId = (Long) itr.next();
            	catProd = new CategoryProduct();
            	catProd.setNCategoryId(categoryId.longValue());
            	catProd.setNProductId(product.getNID());
            	catDao.create(catProd);
            }

            // create product entry into lucene repository
            IProductDAO lDao = ProductDAOFactory.getLuceneProductDAO();
            lDao.create(product);

            tm.commit();
        } catch (Exception exception) {
            tm.rollback();
            throw new ProductManagerException(exception);
        } finally {
            tm.end();
        }
    }
    
    public void update(List categories, Product product) throws ProductManagerException {
        ITransactionManager tm = TransactionManagerFactory.getTransactionManager();

        try {
        	tm.begin();
        	
        	// find the product by primary key
            IProductDAO dao = ProductDAOFactory.getProductDAO();
            Product productDb = dao.findByPrimaryKey(product.getNID());
            
            // check if the user requesting update is the owner of the product
            if (productDb.getNAccountID()==product.getNAccountID()) {
            	// check if the photo need to be updated
            	if (productDb.getImgPhoto() != null && product.getImgPhoto() == null) {
            		product.setImgPhoto(productDb.getImgPhoto());
            		product.setImgThumbnailPhoto(productDb.getImgThumbnailPhoto());
            	}
            	
            	// update the product into the database
                dao.update(product);

                // remove all old category references
                ICategoryProductDAO catDao = CategoryProductDAOFactory.getCategoryProductDAO();
                catDao.delete(product.getNID());
                
                // create the new category references
                CategoryProduct catProd = null;
                for (Iterator itr = categories.iterator(); itr.hasNext();) {
                	Long categoryId = (Long) itr.next();
                	catProd = new CategoryProduct();
                	catProd.setNCategoryId(categoryId.longValue());
                	catProd.setNProductId(product.getNID());
                	catDao.create(catProd);
                }

                // update product in lucene repository
                IProductDAO lDao = ProductDAOFactory.getLuceneProductDAO();
                lDao.update(product);

                tm.commit();
            } else {
            	tm.commit();
                throw new ProductManagerException(IErrorCodes.ERR_UNAUTHORIZED_USER);
            }
        } catch (Exception exception) {
            tm.rollback();
            throw new ProductManagerException(exception);
        } finally {
            tm.end();
        }
    }
    
    public void delete(long accountId, long key) throws ProductManagerException {
        ITransactionManager tm = TransactionManagerFactory.getTransactionManager();

        try {
            tm.begin();

            // find product by primary key
            IProductDAO dao = ProductDAOFactory.getProductDAO();
            Product product = dao.findByPrimaryKey(key);
            
            // check if the user requesting delete is the owner of the product
            if (product.getNAccountID()==accountId) {
            	// remove all category references
                ICategoryProductDAO catDao = CategoryProductDAOFactory.getCategoryProductDAO();
                catDao.delete(key);
                
                // remove product from the database
                dao.delete(key);
                
                // remove the product from the lucene repository
                IProductDAO lDao = ProductDAOFactory.getLuceneProductDAO();
                lDao.delete(key);
                
                tm.commit();
            } else {
            	tm.commit();
                throw new ProductManagerException(IErrorCodes.ERR_UNAUTHORIZED_USER);
            }
        } catch (Exception exception) {
            tm.rollback();
            throw new ProductManagerException(exception);
        } finally {
            tm.end();
        }
    }

    public IDataListHandler getProductsByAccount(long nAccountID) throws ProductManagerException {
    	return new ProductFetchListHandler(nAccountID, IProductPage.PAGE_LENGTH);
    }

    public IDataListHandler getProductsByCategory(long nCategoryID) throws ProductManagerException {
    	return new ProductsByCategoryFetchListHandler(nCategoryID, IProductPage.PAGE_LENGTH);
    }

    public Product getProduct(long nID) throws ProductManagerException {
        Product product = null;
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();

        try {
            tm.begin();
            IProductDAO dao = ProductDAOFactory.getProductDAO();
            product = dao.findByPrimaryKey(nID);
            tm.commit();
            return product;
        } catch (Exception exception) {
            tm.rollback();
            throw new ProductManagerException(exception);
        } finally {
            tm.end();
        }
    }

    public IDataListHandler search(String txtSearch) throws ProductManagerException {
    	List productKeys = null;

        try {
            IProductDAO dao = ProductDAOFactory.getLuceneProductDAO();
            productKeys = dao.findByText(txtSearch);
            return new ProductPKeyListHandler(productKeys, IProductPage.PAGE_LENGTH);
        } catch (Exception exception) {
            throw new ProductManagerException(exception);
        }
    }

    public void rigenerateIndex() throws ProductManagerException {
    	IProductDAO productSearchDAO = ProductDAOFactory.getLuceneProductDAO();
    	IProductDAO productDAO = ProductDAOFactory.getProductDAO();
    	
		try {
			productSearchDAO.delete();
		} catch(DAOException exc) {
			throw new ProductManagerException(exc);
		}
			
		try {
			Collection products = productDAO.findAll().values();

			for (Iterator itr=products.iterator(); itr.hasNext();) {
				productSearchDAO.create((Product) itr.next());
			}
		} catch(BazaarException exc) {
			throw new ProductManagerException(exc);
		}
    }
}
