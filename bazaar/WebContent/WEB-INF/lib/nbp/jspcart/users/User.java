package nbp.jspcart.users;

public class User {
	/*
	 * The following Bitmap specifies the membership
	 * +-----------------------------------+ |Admin
	 * |......|XXXXXX|XXXXXX|General| ID | 0 |0000 | 0| 0| 0| 0 No user. | 0
	 * |0000 | 0| 0| 1| 1 Registered User ( Only General thins accessible) | 1
	 * |1111 | 1| 1| 1| -1 Admin User ( All kind of rights available)
	 * +-----------------------------------+
	 *  
	 */
	static final int NOT_APPROVED = 0;

	static final int REGISTERED_USER = 1;

	static final int ADMIN_USER = -1;

	static final int ADMIN_BIT_MAP = 0x8000000;

	static final int REGISTERED_BIT_MAP = 1;

	public String txtUsername; // Alias for txtEmailAddress

	public String txtPassword;

	public String txtName;

	public long nACLBitmap;

	public boolean bSendPromotion;

	public boolean bNeverSendMail;

	public User() {
		txtUsername = txtPassword = txtName = "";
		nACLBitmap = 0;
		bNeverSendMail = bSendPromotion = false;
	}

	public User(java.sql.ResultSet rs) throws Exception {
		txtUsername = rs.getString("txtEmailAddress");
		txtPassword = rs.getString("txtPassword");
		txtName = rs.getString("txtFirstname") + " "
				+ rs.getString("txtLastname");
		nACLBitmap = rs.getLong("nACLBitmap");
		bSendPromotion = rs.getBoolean("bSendPromotion");
		bNeverSendMail = rs.getBoolean("bNeverSendMail");
	}

	public boolean isAdmin() {
		long nInt = nACLBitmap & ADMIN_BIT_MAP;
		return (nInt) > 0;
	}

	public boolean isRegistered() {
		long nInt = nACLBitmap & REGISTERED_BIT_MAP;
		return (nInt) > 0;
	}

	public static void doLogin(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		
		String source = request.getRequestURI();
		String query = request.getQueryString();
		if (query != null) {
			source += "?" + request.getQueryString();
		}

		source = org.nspeech.web.UrlEncoderEx.encode(source);
		response.sendRedirect(request.getContextPath() 
				+ "/login.do?txtMessage=Autorizzazione%20non%20consentita.%20L'operazione%20richiesta%20necessita%20del%20login%20nel%20sistema."
				+ "&txtRedirect=" + source);
	}

	public static void adminLogin(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {

		String source = request.getRequestURI();
		String query = request.getQueryString();
		if (query != null) {
			source += "?" + request.getQueryString();
		}

		source = org.nspeech.web.UrlEncoderEx.encode(source);
		response
				.sendRedirect(request.getContextPath()
						+ "/login.do?txtMessage=Autorizzazione%20non%20consentita.%20Inserisci%20le%20credenziali%20dell'amministratore"
						+ "&txtRedirect=" + source);
	}
}
