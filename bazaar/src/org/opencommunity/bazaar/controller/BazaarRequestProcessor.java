/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.TilesRequestProcessor;
import org.opencommunity.bazaar.account.dto.Account;

public class BazaarRequestProcessor extends TilesRequestProcessor {
	
 	public boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("P3P", "CP=\"NOI DSP COR DEVa TAIa OUR BUS UNI\"");
		return true;
 	}
	
    public boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
     	String [] roles = mapping.getRoleNames();
    	
    	if (roles.length == 0) {
    		return true;
    	}

    	Account user = (Account) request.getSession().getAttribute("account");

    	for (int i=0; i<roles.length; ++i) {
    		if (roles[i].equals("user")) {
    			if (user == null) {
                    // TODO inviare messaggio di errore
    				return false;
    			}
    		} else if (roles[i].equals("admin")) {
    			if (user == null || !user.isBAdmin()) {
                    // TODO inviare messaggio di errore
    				return false;
    			}
    		} else {
    			continue;
    		}
    	}
    	
    	return true;
    }
}
