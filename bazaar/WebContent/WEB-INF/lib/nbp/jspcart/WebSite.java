package nbp.jspcart;

import java.text.NumberFormat;

import nbp.jspcart.categories.Categories;
import nbp.jspcart.countries.Countries;
import nbp.jspcart.data.DBConnection;
import nbp.jspcart.emails.EmailTemplates;
import nbp.jspcart.orders.Orders;
import nbp.jspcart.products.Products;
import nbp.jspcart.shipping.ShippingCompanies;
import nbp.jspcart.users.Users;
import nbp.jspcart.variables.Variables;

import org.nspeech.util.VectorEnum;
import org.nspeech.xml.XMLParser;

/**
 * WebSite class is a data hub for entire website. This class holds all object's
 * session to add/modify/delete objects from database. Jsp pages have access to
 * WebSite bean at application level. So only one instance of this class exists
 * in one virtual host or web application
 */

public final class WebSite {
	public String shopID; // used in OrderID
	public String shopName;
	public String shopCompany;
	public String shopWebAddress;
	public String shopSupportEmail;
	public String shopSmtpServer;
	public String shopSkin;

	public Users users = null;
	public Products products = null;
	public Categories categories = null;
	public Countries countries = null;
	public EmailTemplates emailtemplates = null;
	public Orders orders = null;
	public Variables variables = null;
	public ShippingCompanies shippingcompanies = null;
	public Logger logger = null;
	public Mailer mailer = null;
	public String databaseUrl;
	public String databaseDriver;
	public String databaseUsername;
	public String databasePassword;
	public int poolerSize = 10;

	NumberFormat nf = null;
	String physicalPath = "";

	public String getPath() {
		return physicalPath;
	}

	public String getConfPath() {
		return physicalPath + java.io.File.separator + "conf";
	}

	public String getLogPath() {
		return physicalPath + java.io.File.separator + "logs";
	}

	void loadDatabaseConfiguration() throws WebSiteError {
		XMLParser parser = new XMLParser();
		if (!parser.parseFile(getConfPath() + java.io.File.separator
				+ "database.xml")) {
			throw new WebSiteError(
					"Database information missing, Please setup database ",
					"Click here to setup database", "/conf/setup/");
		}
		databaseDriver = parser.getValue("database/driver");
		databaseUrl = parser.getValue("database/url");
		databaseUsername = parser.getValue("database/username");
		databasePassword = parser.getValue("database/password");
	}

	void loadPoolerConfiguration() throws WebSiteError {
		XMLParser parser = new XMLParser();
		if (!parser.parseFile(getConfPath() + java.io.File.separator
				+ "pooler.xml")) {
			throw new WebSiteError(
					"Pooler information missing, Please setup database ",
					"Click here to setup pooler", "/conf/setup/");
		}
		poolerSize = Integer.parseInt(parser.getValue("pooler/size"));
	}

	void loadShopConfiguration() throws WebSiteError {
		XMLParser parser = new XMLParser();

		if (!parser.parseFile(getConfPath() + java.io.File.separator
				+ "shop.xml")) {
			throw new WebSiteError(
					"Shop information missing, Please setup database ",
					"Click here to setup pooler", "/conf/setup/");
		}

		shopID = parser.getValue("shop/id");
		shopName = parser.getValue("shop/name");
		shopCompany = parser.getValue("shop/company");
		shopWebAddress = parser.getValue("shop/web-address");
		shopSupportEmail = parser.getValue("shop/support-email");
		shopSmtpServer = parser.getValue("shop/smtp-server");
		shopSkin = parser.getValue("shop/skin");
	}

	public WebSite() {
		nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setGroupingUsed(false);

		mailer = new Mailer(this);
	}

	private VectorEnum connections = null;

	/**
	 * When website's instance at application level is created, it must be
	 * initialized by jsp page by giving the real path of web application which
	 * forces this website to load all configuration.
	 */
	public void init(String path)
	//throws WebSiteError
			throws Exception {
		int i = path.lastIndexOf(java.io.File.separator);
		path = path.substring(0, i);

		physicalPath = path;

		loadDatabaseConfiguration();
		loadPoolerConfiguration();
		loadShopConfiguration();
		loadPooler();
		logger = new Logger(this);
		users = new Users(this);
		// Please note products must be loaded first...
		// because categories need product objects
		products = new Products(this);
		categories = new Categories(this);
		countries = new Countries(this);
		emailtemplates = new EmailTemplates(this);
		orders = new Orders(this);
		variables = new Variables(this);
		shippingcompanies = new ShippingCompanies(this);
	}

	private void loadPooler()
	//throws WebSiteError
			throws Exception {
		try {
			connections = new VectorEnum();
			for (int i = 0; i < poolerSize; i++) {
				DBConnection conn = new DBConnection(this);
				connections.addElement(conn);
			}
		} catch (Exception e) {
			throw e;
			//throw new WebSiteError("Unable to create connections, please
			// check the installation : details '"+e.getMessage()+"'","Setup
			// Database","/conf/setup/");
		}
	}

	public DBConnection getConnection() {
		return (DBConnection) connections.nextReset();
	}

	public String getRequestParameter(
			javax.servlet.http.HttpServletRequest request, String parameter,
			String def) {
		String value = request.getParameter(parameter);
		return value == null ? def : value;
	}

	public String getRequestParameter(
			javax.servlet.http.HttpServletRequest request, String parameter) {
		String value = request.getParameter(parameter);
		return value == null ? "" : value;
	}

	public int getRequestParameter(
			javax.servlet.http.HttpServletRequest request, String parameter,
			int def) {
		String value = request.getParameter(parameter);
		return value == null ? def : Integer.parseInt(value);
	}

	public String format(double dbl) {
		return nf.format(dbl);
	}
}
