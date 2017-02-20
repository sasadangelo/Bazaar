package nbp.jspcart;


/**This exception when raises, it forces browser to goto the url specified*/
public class ForceRedirect extends Exception
{
	public ForceRedirect(String url)
	{
		super("<script language='javascript'>\r\n<!--\r\nlocation.href=\"" + url + "\"\r\n-->\r\n</script>");
	}
}
