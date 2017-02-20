/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.Iterator;

import org.opencommunity.bazaar.utils.business.IDataListIterator;

class ProductPKeyListIterator implements IDataListIterator {
    Iterator iterator;

    ProductPKeyListIterator(Iterator iterator) {
        this.iterator = iterator;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Object next() {
        return iterator.next();
    }
}
