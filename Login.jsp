<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%String username=(String)request.getAttribute("username"); %>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
		<div class="row">
			<div class="col-lg-6">
				<div class="recent">
					<button class="btn-primarys"><h3>Login</h3></button>
				</div>		
					<form id="form" method="post" action="<%=request.getContextPath()%>/RegistrationServlet" name="registration">
					 <% if("invalid".equals(username)){ %>
            <label><font color="red">Invalid username & password.<br>Please enter the correct username & password </font></label>
            <%} %>
					<div class="form-group">
					
					<input type="text" class="form-control" id="EMAILID" name="EMAILID" placeholder="Email Id" maxlength="45" required="required" >
					</div>
					<div class="form-group">
					
					<input type="password" class="form-control" id="PASSWORD" name="PASSWORD" placeholder="Password" maxlength="15" required="required">
										   	<label id="LNAMEERROR"></label>
					</div>
						<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
					<input type="hidden" name="page" value="login">
					</form>
					</div>
					</div>
					</div>
					
</body>
</html>