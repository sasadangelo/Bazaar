/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {
	private static String JNDI_SCOPE="java:comp/env/";
	
	private Hashtable cache = new Hashtable();

	public static ServiceLocator getInstance() {
		if (instance == null) {
			synchronized (ServiceLocator.class) {
				if (instance == null) {
					instance = new ServiceLocator();
				}
			}
		}
		return instance;
	}

	public Object getService(String name) throws NamingException {
		if (cache.containsKey(name)) {
			return cache.get(name);
		}

		Context initCtx = null;
		Context envCtx = null;
		
		try {
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup(JNDI_SCOPE);
			Object service = envCtx.lookup(name);
			cache.put(name, service);
			return service;
		} finally {
			close(envCtx);
			close(initCtx);
		}
	}

	private void close(Context ctx) {
		if (ctx != null) {
			try {
				ctx.close();
			} catch (NamingException exception) {
			}
		}
	}

	private ServiceLocator() {
	}

	private static ServiceLocator instance = null;
}
