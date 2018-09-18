<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*,client.*,java.net.*;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Car Configuration</title>
</head>
<body>
<% String strLocalHost = "";
try {
	strLocalHost = InetAddress.getLocalHost().getHostName();
} catch (UnknownHostException e) {
	System.err.println("Unable to find local host");
} 
defaultSocketClient use =  new defaultSocketClient(strLocalHost, 4444);
use.openConnection();
String model = (String)request.getParameter("car");
Vehicle your = use.getAModel(model);
use.closeSession();
if(your!=null){
out.print(model+"<br> Base Price: $"+your.getCost()+"<br><br>");
for(int i =0;i<your.numComponents();i++){
	String comp = your.getCompName(i);
	String opt = request.getParameter("comp"+i);
	your.setCompChose(comp, opt);
	String total = comp+":"+your.getCompChose(comp)+" $"+your.getCompCost(comp);
	out.print("<br>"+total+"<br>");
}
out.print("<br><br><br><br> The total cost of your configured car is: $"+your.getTotal());
}
else{
	out.println("car not received model is " + model);
}
%>

</body>
</html>