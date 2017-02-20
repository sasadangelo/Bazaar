<%@page import='nbp.jspcart.*,nbp.jspcart.users.*,nbp.jspcart.products.*,nbp.jspcart.orders.*,java.util.*,java.text.*,java.sql.*'%>

<%
	response.setContentType("text/text");
	response.addHeader("expires","0");
%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%
		website.init(application.getRealPath("../../"));
	%>
</jsp:useBean>

<%
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",2000);
	
	int nOrderState = website.getRequestParameter(request,"nOrderState",0);

	int nStart_Day = website.getRequestParameter(request,"nStart_Day",1);
	int nStart_Month = website.getRequestParameter(request,"nStart_Month",1);
	int nStart_Year = website.getRequestParameter(request,"nStart_Year",2003);
	
	int nEnd_Day = website.getRequestParameter(request,"nEnd_Day",1);
	int nEnd_Month = website.getRequestParameter(request,"nEnd_Month",12);
	int nEnd_Year = website.getRequestParameter(request,"nEnd_Year",3000);

	String text,startdate,enddate;
	text = String.valueOf(nStart_Year);
	text += "-" + String.valueOf(nStart_Month);
	text += "-" + String.valueOf(nStart_Day);
	startdate = text;
	text += " 00:00:00.00000000";
	Timestamp tsStart = Timestamp.valueOf(text);

	text = String.valueOf(nEnd_Year);
	text += "-" + String.valueOf(nEnd_Month);
	text += "-" + String.valueOf(nEnd_Day);
	enddate = text;
	text += " 00:00:00.00000000";
	Timestamp tsEnd = Timestamp.valueOf(text);

	Vector items = website.orders.getAll(tsStart,tsEnd,nOrderState);

	OrderPrinterCSV printer = new OrderPrinterCSV(website);
	String filename = "From-" + startdate + "-To-" + enddate +"-"+ String.valueOf(System.currentTimeMillis()) + ".csv";

	String filepath = website.getPath() + "\\temp\\" + filename;
	printer.print(filename,filepath,items);

	response.sendRedirect(request.getContextPath() + "/DownloadFile.do?txtFilename=" + filename + ".zip" + "&txtFilepath=" + filepath + ".zip");
%>
