package org.opencommunity.bazaar.utils.integration.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

import org.opencommunity.bazaar.utils.dto.EmailMessage;
import org.opencommunity.bazaar.utils.exceptions.MailException;
import org.opencommunity.bazaar.utils.integration.utils.ServiceLocator;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

public class MailManager {
	private static String MAIL_JNDI_NAME = "mail/Session";

    private static MailManager instance = null;

    public static MailManager getInstance() throws MailException {
        if (instance == null) {
            synchronized (MailManager.class) {
                if (instance == null) {
                    instance = new MailManager();
                }
            }
        }
        return instance;
    }
	
    public void send(EmailMessage msg) throws MailException {
    	try {
    		Session session = (Session) ServiceLocator.getInstance().getService(MAIL_JNDI_NAME);
    	    Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sasadangelo@gmail.com", "Bazaar Support"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg.getTo(), false));		
            message.setDataHandler(new DataHandler(new ByteArrayDataSource(msg.getBody(), "text/html")));
            message.setHeader("X-Mailer", "NeuroSpeech Mailer");
            message.setSentDate(new Date());
            message.setSubject(msg.getSubject());
    	    Transport.send(message);
		} catch(NamingException exception) {
			throw new MailException(IErrorCodes.ERR_JNDI_ACCESS);
    	} catch(MessagingException message) {
    		throw new MailException(IErrorCodes.ERR_UNABLE_SEND_EMAIL);
    	} catch(UnsupportedEncodingException message) {
    		throw new MailException(IErrorCodes.ERR_UNABLE_SEND_EMAIL);
    	}
    }
    
    private MailManager() {
    }
}
