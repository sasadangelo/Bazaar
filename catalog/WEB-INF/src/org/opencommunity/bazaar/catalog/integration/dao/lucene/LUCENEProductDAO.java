/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.opencommunity.bazaar.catalog.dto.Product;
import org.opencommunity.bazaar.catalog.integration.dao.IProductDAO;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

public class LUCENEProductDAO implements IProductDAO {

    private static String KEY              = "key";
    private static String NAME             = "name";
    private static String DESCRIPTION      = "description";
    private static String SHORTDESCRIPTION = "shortdescription";
    private static String CONTENT          = "content";

    public LUCENEProductDAO() {
    	IndexWriter writer = null;
    	File index = new File("lucene-index");
        if (!index.exists()) {
        	try {
                index.mkdir();
           	    writer = new IndexWriter(index, new StandardAnalyzer(), true);
        	} catch(IOException exception) {
        	} finally {
                close(writer);        		
        	}
    	}
    }

    ////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////
    
    public void create(Product product) throws DAOException {
    	File index = new File("lucene-index");
        Document doc = new Document();

        doc.add(Field.Text(KEY, Long.toString(product.getNID()))); 
        doc.add(Field.Text(CONTENT, product.getTxtName() + "  " + product.getTxtDescription() + " " + product.getTxtDescription()));
        doc.add(Field.Text(NAME, product.getTxtName()));
        doc.add(Field.Text(DESCRIPTION, product.getTxtShortDescription()));
        doc.add(Field.Text(SHORTDESCRIPTION, product.getTxtDescription()));

        IndexWriter writer = null;
        
        try {
            //if (!index.exists()) {
            //	createIndex();
        	//}

            writer = new IndexWriter(index, new StandardAnalyzer(), false);
            writer.addDocument(doc);
            writer.optimize();
        } catch (IOException e) {
            throw new DAOException(IErrorCodes.ERR_LUCENE_MODIFY);
        } finally {
        	close(writer);
        }
    }

    public void update(Product product) throws DAOException {
    	delete(product.getNID());
    	create(product);
    }
    
    public void delete(long nID) throws DAOException {
    	File index = new File("lucene-index");
        IndexReader reader = null;
        try {
            reader = IndexReader.open(index);
            reader.delete(new Term(KEY, Long.toString(nID)));
        } catch (IOException e) {
            throw new DAOException(IErrorCodes.ERR_LUCENE_MODIFY);
        } finally {
        	close(reader);
        }
    }

    public void delete() throws DAOException {
        IndexReader reader = null;
        File index = new File("lucene-index");
        try {
            reader = IndexReader.open(index);
            for(TermEnum enumA = reader.terms(); enumA.next();) {
            	Term term = enumA.term();
                reader.delete(term);
            }
        } catch (IOException e) {
            throw new DAOException(IErrorCodes.ERR_LUCENE_MODIFY);
        } finally {
        	close(reader);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // Finder methods
    ////////////////////////////////////////////////////////////////////////////

    public HashMap findAll() throws DAOException {
		throw new DAOException(IErrorCodes.ERR_FUNCTION_NOT_IMPLEMENTED);
    }
    
    public List findByAccount(long nAccountID, int startIndex, int pageLength) throws DAOException {
		throw new DAOException(IErrorCodes.ERR_FUNCTION_NOT_IMPLEMENTED);
    }

    public List findByCategory(long nCategoryID, int startIndex, int pageLength) throws DAOException {
		throw new DAOException(IErrorCodes.ERR_FUNCTION_NOT_IMPLEMENTED);
    }

    public Product findByPrimaryKey(long key) throws DAOException {
		throw new DAOException(IErrorCodes.ERR_FUNCTION_NOT_IMPLEMENTED);
    }

    public List findByText(String text) throws DAOException {
    	File index = new File("lucene-index");
    	ArrayList list = new ArrayList();
        if (index.exists()) {
            Query query;
            try {
                query = QueryParser.parse(text, CONTENT, new StandardAnalyzer());
            } catch (ParseException e) {
                throw new DAOException(IErrorCodes.ERR_LUCENE_MODIFY); 
            }

            IndexReader reader = null;
            IndexSearcher searcher = null;
            try {
                reader = IndexReader.open(index);
                searcher = new IndexSearcher(reader);
                Hits hits = searcher.search(query);
                for (int i = 0; i != hits.length(); ++i) {
                    Document doc = hits.doc(i);
                    long key = Long.parseLong(doc.getField(KEY).stringValue());
                    list.add(new Long(key));
                }
            } catch (IOException e) {
                throw new DAOException(IErrorCodes.ERR_LUCENE_QUERY);
            } finally {
            	close(searcher);
            	close(reader);
            }
        }
    	return list;
    }
    
    public int countProductsByAccount(long nAccountID) throws DAOException {
		throw new DAOException(IErrorCodes.ERR_FUNCTION_NOT_IMPLEMENTED);
    }

    public int countProductsByCategory(long nCategoryID) throws DAOException {
		throw new DAOException(IErrorCodes.ERR_FUNCTION_NOT_IMPLEMENTED);
    }
    
    private void close(IndexReader reader) {
    	try {
            if (reader != null) reader.close();
    	} catch(IOException exc) {
    	}
    }

    private void close(IndexSearcher searcher) {
    	try {
            if (searcher != null) searcher.close();
    	} catch(IOException exc) {
    	}
    }

    private void close(IndexWriter writer) {
    	try {
            if (writer != null) writer.close();
    	} catch(IOException exc) {
    	}
    }
}
