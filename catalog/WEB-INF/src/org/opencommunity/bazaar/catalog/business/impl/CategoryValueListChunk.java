/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.List;

import org.opencommunity.bazaar.utils.business.IDataListChunk;

class CategoryValueListChunk extends CategoryValueList implements IDataListChunk {
    private boolean hasNext;
    private boolean hasPrev;

    CategoryValueListChunk(List values, int startIndex, int pageLength) {
        super(values.subList(startIndex, startIndex + pageLength > values.size() ? values.size() : startIndex + pageLength));
        this.hasNext = (startIndex + pageLength) < values.size();
        this.hasPrev = startIndex > 0;
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
