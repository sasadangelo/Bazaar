/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao.mysql;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class MySQLUtility {

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String getOptString(ResultSet rs, int paramIndex) throws SQLException {
        String ret = rs.getString(paramIndex);
        if (rs.wasNull())
            return null;
        return ret;
    }
    
    public static BufferedImage getOptImage(ResultSet rs, int paramIndex) throws SQLException {
    	Blob blob = rs.getBlob(paramIndex);
        if (rs.wasNull())
            return null;
        try {
			BufferedImage image = ImageIO.read(blob.getBinaryStream());
	        return image;
        } catch(IOException exception) {
        	return null;
        }
    }
    
    public static final void setOptString(PreparedStatement stmt, int position, String value) throws SQLException {
        if (value == null)
            stmt.setNull(position, java.sql.Types.VARCHAR);
        else
            stmt.setString(position, value);
    }

    public static final void setOptImage(PreparedStatement stmt, int position, BufferedImage value) throws SQLException {
        if (value == null) {
            stmt.setNull(position, java.sql.Types.BLOB);
        } else {
        	try {
	        	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);
	    		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(value);
	    		encoder.setJPEGEncodeParam(param);
	    		encoder.encode(value);
	        	stmt.setBinaryStream(position, new ByteArrayInputStream(outStream.toByteArray()), outStream.size());
        	} catch(IOException exception) {
                stmt.setNull(position, java.sql.Types.BLOB);
        	}
        }
    }

    public static final void setOptLong(PreparedStatement stmt, int position, Long value) throws SQLException {
        if (value == null)
            stmt.setNull(position, java.sql.Types.BIGINT);
        else
            stmt.setLong(position, value.longValue());
    }

    public static Long getOptLong(ResultSet rs, int paramIndex) throws SQLException {
        long ret = rs.getLong(paramIndex);
        if (rs.wasNull())
            return null;
        return new Long(ret);
    }
}
