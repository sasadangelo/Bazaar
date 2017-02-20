package org.nspeech.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DataConnection {
    public Connection innerConnection;

    public boolean open(String driver, String url, String username,
            String password) throws Exception {
        Class.forName(driver);
        innerConnection = DriverManager.getConnection(url, username, password);
        if (innerConnection == null)
            return false;
        return true;
    }

    public void close() {
        try {
            innerConnection.close();
            System.out.println("Database Connection Closed Successfully..");
        } catch (Exception e) {
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return innerConnection.prepareStatement(sql);
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}