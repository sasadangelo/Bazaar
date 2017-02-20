/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.business.impl;

import java.util.Iterator;

import org.opencommunity.bazaar.utils.business.IDataListIterator;

class AccountFetchListIterator implements IDataListIterator {
    Iterator iterator;

    AccountFetchListIterator(Iterator iterator) {
        this.iterator = iterator;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Object next() {
        return iterator.next();
    }
}
