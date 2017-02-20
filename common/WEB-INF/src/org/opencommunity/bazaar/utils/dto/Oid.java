/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.dto;

public class Oid {

    private String entityName;
    private long lastId;
    private int blockSize;
    private long currentOid;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String theEntityName) {
        entityName = theEntityName;
    }

    public long getLastId() {
        return lastId;
    }

    public void setLastId(long theLastId) {
        lastId = theLastId;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int theBlockSize) {
        blockSize = theBlockSize;
    }

    public long getCurrentOid() {
        return currentOid;
    }

    public void setCurrentOid(long theCurrentOid) {
        currentOid = theCurrentOid;
    }

    public Oid() {
    }

    public Oid(int blockSize, String entityName, long lastId) {
        this.blockSize = blockSize;
        this.entityName = entityName;
        this.lastId = lastId;
        this.currentOid = lastId + 1;
    }
}
