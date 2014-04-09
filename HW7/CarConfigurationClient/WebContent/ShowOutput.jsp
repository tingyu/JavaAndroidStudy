<%@ page language="java" import="java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<H2>Here is what you selected</H2>
<CENTER>
<%   
	int index  = (Integer)session.getAttribute("opsetNumber");
	String modelName = (String)session.getAttribute("ModelName");
	ArrayList<String> opsetNameList = (ArrayList<String>) session
			.getAttribute("opsetNameList");
	int basePrice = (Integer)session.getAttribute("BasePrice");
	int totalPrice = basePrice;
%>

<TABLE BORDER=1>
  <TR><TH></TH><TH>Apples<TH>Oranges
  <TR><TH><%= modelName%><TD>base price<TD><%=basePrice%>
<%
if (opsetNameList != null) {
	for (int i = 0; i < opsetNameList.size(); i++){
%>
	<TR><TH><%=opsetNameList.get(i) %><TD><%= request.getParameter(opsetNameList.get(i)) %><TD><%= session.getAttribute("OptionPrice" + opsetNameList.get(i) ) %>
<%
	Integer price = (Integer) session.getAttribute("OptionPrice" + opsetNameList.get(i));
	if (price != null) {
		totalPrice += price;
	} else {
		totalPrice += 0;
	}
   // totalPrice = (Integer)(session.getAttribute("OptionPrice" + opsetNameList.get(i)));

	}
	} else {
		out.println("null value");
	}
%>
  <TR><TH>Total Cost<TD><TD><%= totalPrice%>

</TABLE>

</CENTER>
</body>
</html>