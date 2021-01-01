<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
<%String msg=(String)request.getAttribute("msg"); %>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
		<div class="row">
			<div class="col-lg-6">
				<div class="recent">
					<button class="btn-primarys"><h3>Registration</h3></button>
				</div>		
					<form id="form" method="post" action="<%=request.getContextPath()%>/RegistrationServlet" name="registration">
				 <%if("false".equals(msg)){ %>

<p>Username is not Registered. To registered <a href="<%=request.getContextPath()%>/JSP/Registration.jsp">Click here</a></p>
<%}else if("true".equals(msg)){ %>
<p>Please check your Mobile no for Password</p>
<%} %>
					<div class="form-group">
					
					<input type="text" class="form-control" id="FNAME" name="EMAILID" placeholder="Register Mobile No" maxlength="45" required="required"  >
										   	<input type="hidden" name="action" value="forgetpassword">
					</div>
					
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
					<input type="hidden" name="page" value="forgetpassword">
					</form>
					</div>
					</div>
					</div>
				
</body>
</html>