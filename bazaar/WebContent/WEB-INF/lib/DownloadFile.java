import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nbp.jspcart.users.User;

/**
 * DownloadFile servlet, used to download the file from the server after authentication. The file 
 * is stored in the location non-accessible by the webserver to directly give access to user. That
 * means the user can not access the file directly by typing any URL on browser.
 * 
 * The servlet receives full path of the file on the server requested by user in the query string.
 * The servlet verifies the logged user is admin and reads the file from the disk and sends it to user.
 */

public class DownloadFile extends HttpServlet {

	
	
	/**
	 * Method doGet is called by servlet engine when requested with query string parameters
	 * 
	 * This method first authenticates user to be admin, if it is not then user object's adminLogin method redirects
	 * control to login and after login it comes here once again.
	 * 
	 * Then sendFile method is called..
	 * */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
    {

		javax.servlet.http.HttpSession session = request.getSession();

		try
		{
			
			// session stores current user's object
			// if null then return to login 
			// or if is not admin then return to admin login
			User user = (User)session.getAttribute("User");
			if(user==null || (!user.isAdmin()))
			{
				User.adminLogin(request,response);
				return;
			}

			String txtFilepath = request.getParameter("txtFilepath");
			String txtFilename = request.getParameter("txtFilename");
			
			// call sendFile to send file in binary form...
			sendFile(txtFilepath,txtFilename,response);
		}
		catch(Exception e)
		{
		}
    }

	
	/**
	 * doPost calls doGet no difference here..
	 * */
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

	/**
	 * To send file to browser for download.. <BR>
	 * first set content type to "application/octat-stream" so that browser invokes download dialog. Then 
	 * set Content-Disposition as "filename=".."" , so that filename to save as appears in the download dialog.<BR><BR>
	 * 
	 * Then read file in bunch of 1024 bytes and send it to the end client.
	 * */
	public static boolean sendFile( String filepath , String filename , javax.servlet.http.HttpServletResponse response)
	{
		try
		{
			
			// Set browser download related headers
			response.setContentType("application/octat-stream");
			response.addHeader("Content-Disposition","filename=\""+ filename + "\"");

			// open file..
			java.io.FileInputStream  fin = new java.io.FileInputStream(filepath);

			// buffer to read file and transfer it to end point
			byte [] buffer = new byte [1024];

			int n;
			n = fin.read(buffer,0,1024);

			javax.servlet.ServletOutputStream so = response.getOutputStream();

			while(n>0)
			{
				so.write(buffer,0,n);
				n = fin.read(buffer,0,1024);
			}

			fin.close();
			

		}
		catch(Exception e)
		{
			return false;
		}
		// file must be deleted after transfer...
		// caution: select to download only files which are temporarily created zip files
		// do not call this servlets with any other files which may be required later on.
		File file = new File(filepath);
		file.delete();
		return true;
	}
}


