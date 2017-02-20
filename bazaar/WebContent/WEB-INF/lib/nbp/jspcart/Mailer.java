package nbp.jspcart;



import nbp.jspcart.orders.*;
import nbp.jspcart.users.*;




/**Mailer class contains all emailing procedures on events like signup , forgot password etc. 
 * This class's methods are called by other objects when some event occurs.
 * */
public class Mailer
{
	WebSite website = null;
	
	public Mailer(WebSite ws)
	{
		website = ws;
	}
	
	
	/**Sends order status email to the user. Every order contains user's email address*/
	public String sendOrderStatus(Order order,Users users)
	{/*
		Email email = new Email(website,"OrderStatus");
		
		User user = users.get(order.txtEmailAddress);
		
		email.replace("$SHOPNAME$",website.shopName);
		email.replace("$SHOPWEBADDRESS$",website.shopWebAddress);
        email.replace("$SHOPSUPPORTADDRESS$",website.shopWebAddress);
		email.replace("$USER$",user.txtName);
		email.replace("$ID$",String.valueOf(order.nID));
		email.replace("$ORDERDATE$",order.tsDate.toString());
		email.replace("$ORDERAMOUNT$",website.format(order.dblGrandTotal));
		email.replace("$ORDERSTATUS$",order.getStatus());
		email.replace("$REMARKS$",order.txtRemarks);
		email.replace("$TRACKING$",order.txtTracking);
		
		email.fromAddress = website.shopSupportEmail;
		email.fromName = website.shopName + " Support";
		email.to = user.txtUsername;
		email.cc = email.fromAddress;
		
		return email.send();*/
		return null;
	}
	
	/**Sends pending payment reminder email to the user. Every order contains user's email address*/
	public String sendPaymentPendingMail(Order order,Users users)
	{/*
		Email email = new Email(website,"PendingPayment");
		
		User user = users.get(order.txtEmailAddress);
		
		email.replace("$SHOPNAME$",website.shopName);
		email.replace("$SHOPWEBADDRESS$",website.shopWebAddress);
		email.replace("$USER$",user.txtName);
		email.replace("$ID$",String.valueOf(order.nID));
		email.replace("$ORDERDATE$",order.tsDate.toString());
		email.replace("$ORDERAMOUNT$",website.format(order.dblGrandTotal));
		email.replace("$ORDERSTATUS$",order.getStatus());
		email.replace("$REMARKS$",order.txtRemarks);
		email.replace("$TRACKING$",order.txtTracking);
		
		email.fromAddress = website.shopSupportEmail;
		email.fromName = website.shopName + " Support";
		email.to = user.txtUsername;
		email.cc = email.fromAddress;
		
		return email.send();*/
		return null;
	}
	
	/**Sends signup welcome email to the user.*/
	public String sendSignupMail(UserProfile profile)
	{/*
		Email email = new Email(website,"Signup");
		
		email.replace("$SHOPNAME$",website.shopName);
		email.replace("$SHOPWEBADDRESS$",website.shopWebAddress);
		email.replace("$SHOPSUPPORTEMAIL$",website.shopSupportEmail);
		email.replace("$USER$",profile.txtFirstname + " " + profile.txtLastname);
		
		email.fromAddress = website.shopSupportEmail;
		email.fromName = website.shopName + " Support";
		email.to = profile.txtEmailAddress;
		email.cc = email.fromAddress;
		
		return email.send();*/
		return null;
	}
}
