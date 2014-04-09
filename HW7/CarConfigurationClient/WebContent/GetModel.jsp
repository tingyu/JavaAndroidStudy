<%@ page language="java" import="java.util.ArrayList"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		
	%>
	<form method="post" action="./OptionSetServlet">
		<h2>Select the model name you want to configure</h2>


		<%
			ArrayList<String> array = (ArrayList<String>) request
					.getAttribute("ModelNameList");
			if (array != null) {
				for (int i = 0; i < array.size(); i++) {
		%>

		<input type="radio" name="modelname" value=<%="\"" + array.get(i) + "\"" %>>
		<%=array.get(i)%></input> <br>
		<%
			}
		%>
		<%
			} else {
				out.println("null value");
			}
		%>
		<br> <input type="submit" value="Submit">
	</form>

</body>
</html>