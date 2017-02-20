package nbp.jspcart.products;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

public class Multipart {

	// Members
	public int nID;
	public String txtName;
	public String txtDescription;
	public String txtImgUrl;
	public String txtImgUrl2;
	public String txtHeader;
	public String txtFooter;
	public String txtBuyNowUrl;
	public String txtWeight;
	public double dblQtyPerPack;
	public double dblPricePerQty;
	public int bActive;
	public double dblPrice;
	public double dblDummyPrice;
	public double dblShippingFactor;
	public double dblCostPerQty;
	public double dblCost;
	public int bShow;
	public String aryCatIDs;

	public Multipart(HttpServletRequest rs, String path) 
	    throws FileUploadException, IOException, Exception {
		
		DiskFileUpload upload = new DiskFileUpload();

		// parse this request by the handler
		// this gives us a list of items from the request
		List items = upload.parseRequest(rs);

		Iterator itr = items.iterator();

		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			// check if the current item is a form field or an uploaded
			// file
			if (item.isFormField()) {
				if (item.getFieldName().equals("nID")) {
					nID = Integer.parseInt(item.getString());
				} else if (item.getFieldName().equals("txtName")) {
					txtName = item.getString();
				} else if (item.getFieldName().equals("txtDescription")) {
					txtDescription = item.getString();
				} else if (item.getFieldName().equals("txtImgUrl2")) {
					txtImgUrl2 = item.getString();
				} else if (item.getFieldName().equals("txtHeader")) {
					txtHeader = item.getString();
				} else if (item.getFieldName().equals("txtFooter")) {
					txtFooter = item.getString();
				} else if (item.getFieldName().equals("txtBuyNowUrl")) {
					txtBuyNowUrl = item.getString();
				} else if (item.getFieldName().equals("txtWeight")) {
					txtWeight = item.getString();
				} else if (item.getFieldName().equals("dblQtyPerPack")) {
					try {
					    dblQtyPerPack = Double.parseDouble(item.getString());
					} catch(NumberFormatException exc) {
						dblQtyPerPack = 0.0;
					}
				} else if (item.getFieldName().equals("dblPricePerQty")) {
					try {
						dblPricePerQty = Double.parseDouble(item.getString());
					} catch(NumberFormatException exc) {
						dblPricePerQty = 0.0;
					}
				} else if (item.getFieldName().equals("bActive")) {
					bActive = Integer.parseInt(item.getString());
				} else if (item.getFieldName().equals("dblPrice")) {
					try {
						dblPrice = Double.parseDouble(item.getString());
					} catch(NumberFormatException exc) {
						dblPrice = 0.0;
					}
				} else if (item.getFieldName().equals("dblDummyPrice")) {
					try {
						dblDummyPrice = Double.parseDouble(item.getString());
					} catch(NumberFormatException exc) {
						dblDummyPrice = 0.0;
					}
				} else if (item.getFieldName().equals("dblShippingFactor")) {
					try {
						dblShippingFactor = Double.parseDouble(item.getString());
					} catch(NumberFormatException exc) {
						dblShippingFactor = 0.0;
					}
				} else if (item.getFieldName().equals("dblCostPerQty")) {
					try {
						dblCostPerQty = Double.parseDouble(item.getString());
					} catch(NumberFormatException exc) {
						dblCostPerQty = 0.0;
					}
				} else if (item.getFieldName().equals("dblCost")) {
					try {
						dblCost = Double.parseDouble(item.getString());
					} catch(NumberFormatException exc) {
						dblCost = 0.0;
					}
				} else if (item.getFieldName().equals("aryCatIDs")) {
					aryCatIDs = item.getString();
				} else if (item.getFieldName().equals("bShow")) {
					bShow = Integer.parseInt(item.getString());
				}
			} else {
				// the item must be an uploaded file save it to disk.
				// Note that there seems to be a bug in item.getName() 
				// as it returns the full path on the client's machine 
				// for the uploaded file name, instead of the file
				// name only. To overcome that, I have used a workaround
				// using fullFile.getName().
                if (item.getFieldName().equals("txtImgUrl")) {
				    File fullFile = new File(item.getName());
				    if (fullFile.getName().equals("")) {
				    	if (txtImgUrl2.startsWith(rs.getContextPath()+"/images/")) {
						    txtImgUrl = txtImgUrl2;
				    	} else {
						    txtImgUrl = rs.getContextPath()+"/images/no-photo.gif";
				    	}
				    } else {
				    	String fileName = fullFile.getName();
				    	int i = fileName.lastIndexOf('\\');
					    txtImgUrl = rs.getContextPath()+"/images/" + fileName.substring(i+1);
					    File savedFile = new File(path, fileName.substring(i+1));
					    item.write(savedFile);
				    }
                }
			}
		}
	}
}