package org.nspeech.xml;

import java.util.*;

public class XMLNode {
	String nodeName;
	String nodeValue = null;
	String nodeAttributes;
	XMLNode parentNode;
	Vector childNodes = new Vector();

	public XMLNode() {
		parentNode = null;
	}

	public XMLNode(XMLNode node) {
		nodeName = node.nodeName;
		nodeValue = node.nodeValue;
		nodeAttributes = node.nodeAttributes;
	}

	public boolean equals(Object p1) {
		XMLNode node = (XMLNode) p1;
		return nodeName.equals(node.nodeName);
	}

	public String getValue() {
		return nodeValue;
	}

	public Vector getChildNodes() {
		return childNodes;
	}

	public void setValue(String value) {
		nodeValue = value;
	}

	public String getName() {
		return nodeName;
	}

	public void setName(String name) {
		int n = name.indexOf(' ');
		if (n == -1)
			nodeName = name;
		else {
			nodeName = name.substring(0, n);
			nodeAttributes = name.substring(n + 1);
		}
	}

	public XMLNode getParent() {
		return parentNode;
	}

	public void setParent(XMLNode node) {
		parentNode = node;
	}

	public String getValue(String path) {
		String node = null;

		if (path.length() == 0)
			return getValue();

		int i = path.indexOf('/');
		if (i != -1) {
			node = path.substring(0, i);
			path = path.substring(i + 1);
		} else {
			node = path;
			path = "";
		}

		Enumeration enum2 = childNodes.elements();
		while (enum2.hasMoreElements()) {
			XMLNode xnode = (XMLNode) enum2.nextElement();
			if (node.equals(xnode.nodeName)) {
				return xnode.getValue(new String(path));
			}
		}
		return "";
	}

	public Vector search(String path) {
		Vector elements = new Vector();
		String node = null;

		if (path.length() == 0) {
			elements.addElement(this);
		}

		int i = path.indexOf('/');
		if (i != -1) {
			node = path.substring(0, i);
			path = path.substring(i + 1);
		} else {
			node = path;
			path = "";
		}

		Enumeration enum2 = childNodes.elements();
		while (enum2.hasMoreElements()) {
			XMLNode xnode = (XMLNode) enum2.nextElement();
			if (node.equals(xnode.nodeName)) {
				Vector childElements = xnode.search(new String(path));
				Enumeration e2 = childElements.elements();
				while (e2.hasMoreElements()) {
					elements.addElement(e2.nextElement());
				}
			}
		}
		return elements;
	}

	public void delete() {
		try {
			parentNode.childNodes.remove(this);
		} catch (Exception e) {
		}
	}

	public void save(java.io.PrintWriter out) {
		out.println("<" + nodeName + ">");
		if (nodeValue != null) {
			out.println(nodeValue);
		}

		Enumeration enum2 = childNodes.elements();
		while (enum2.hasMoreElements()) {
			XMLNode node = (XMLNode) enum2.nextElement();
			node.save(out);
		}
		out.println("</" + nodeName + ">");
	}
}