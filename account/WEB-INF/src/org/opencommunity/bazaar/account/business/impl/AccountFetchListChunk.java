/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.business.impl;

import java.util.List;

import org.opencommunity.bazaar.utils.business.IDataListChunk;

class AccountFetchListChunk extends AccountFetchList implements IDataListChunk {
    private boolean hasNext;
    private boolean hasPrev;

    AccountFetchListChunk(List values, boolean hasPrev, boolean hasNext) {
        super(values);
        this.hasNext = hasNext;
        this.hasPrev = hasPrev;
    }

    public void clear() {
        this.values.clear();
    }

    public boolean hasNext() {
        return hasNext;
    }

    public boolean hasPrev() {
        return hasPrev;
    }
}
