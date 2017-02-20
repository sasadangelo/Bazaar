package nbp.jspcart;

public class WebSiteError extends Exception
{
	public WebSiteError(String description,String linktitle,String link)
	{
		super("<HR><BR>" + description + "<BR><A href='"+link+"'>"+ linktitle + "</A><BR><HR>");
	}
}
