/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.List;

import org.opencommunity.bazaar.utils.business.IDataListChunk;

class ProductFetchListChunk extends ProductFetchList implements IDataListChunk {
    private boolean hasNext;
    private boolean hasPrev;

    ProductFetchListChunk(List values, boolean hasPrev, boolean hasNext) {
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
