/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.dto;

public class EmailMessage {

    private String to;
    private String subject;
    private String body;

	/**
	 * @return Returns the body.
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body The body to set.
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return Returns the to.
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to The to to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	public EmailMessage() {
	}

	public EmailMessage(String to, String subject, String body) {
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
