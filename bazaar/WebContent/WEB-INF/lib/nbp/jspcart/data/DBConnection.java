package nbp.jspcart.data;

public class DBConnection extends org.nspeech.data.DataConnection
{
	public DBConnection(nbp.jspcart.WebSite website)
		throws Exception
	{
		open(website.databaseDriver,website.databaseUrl,website.databaseUsername,website.databasePassword);
	}
}
