<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portal</title>

 <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/responsive-slider.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/animate.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
	
	<%String msg  = (String)request.getAttribute("message");
	ArrayList<String> text = (ArrayList<String>)request.getAttribute("UserEmailId"); %>	
</head>
<body>
<jsp:include page="AfterLoginHeader.jsp"></jsp:include>
<div class="container">
		<div class="row">
			<div class="col-lg-6">
				<div class="recent">
				<table>
				<tr>
				<td colspan="2">
				<%if(msg!=null){ %>
				<label style="color: green"> <%=msg %></label>
				<%} else{%>
					<button class="btn-primarys"><h3>Please select the Image To Share</h3></button>
				</td>
				</tr>
					<form id="form" method="post" action="<%=request.getContextPath()%>/RegistrationServlet" name="registration" enctype="multipart/form-data">>
				
					
					
					<!-- <button class="btn btn-default" onclick="window.href=/JSP/Login.jsp"">Logout</button> -->
					
					<tr>
					<td colspan="2">
				
					<input type="file" name="filename" required="required">
					<br>
					</td>
					</tr>	
					<%
					
					if(text.size()%2 == 1)
						text.add("");
					%>
					
					
					<%
					for(int i=0;i<text.size();i++){
						
						if(text.get(i).length() <1)
							continue;
						if(i%2 ==1 ){
													
					%>
					<tr>
					<%} %>
					<td>
					<input type="checkbox" value="<%=text.get(i)%>" name="<%=i %>"><label style="color: black;" class="btn-primarys"><%=text.get(i) %></label>
					<br>
					</td>
						<%if(i%2 ==1 ){
													
					%>
					</tr>
					<%} %>
					<%} %>
					</table>
					<input type="submit" class="btn btn-default" value="Submit">
					<!-- <button class="btn btn-default" onclick="window.href=/JSP/Login.jsp"">Logout</button> -->
					</form>
					</div>
					<%}%>
					
					</div>
					</div>
				
</body>
</html>