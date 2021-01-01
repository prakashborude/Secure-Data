<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<script type="text/javascript" src="../javascript/validation.js"></script>
<%String var=(String)request.getAttribute("message"); %>
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
				<%if(var != null){ %>
						<label style="color: green"> <%=var %></label>
						<%}%>
					<div class="form-group">
					
					<input type="text" class="form-control" id="FNAME" name="fname" placeholder="First Name" maxlength="15" required="required"  onblur="firstname();">
										   	<label id="FNAMEERROR"></label>
					</div>
					<div class="form-group">
					
					<input type="text" class="form-control" id="LNAME" name="lname" placeholder="Last Name" maxlength="15" required="required"  onblur="lastname();">
										   	<label id="LNAMEERROR"></label>
					</div>
					
					
					<div class="form-group">
					
					<label style="color:gray;font-size: medium;">Gender </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  value="Male" name="gender"checked="checked" required="required"><label style="color:gray">Male</label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="MALE" name="gender" value="Female"checked="checked" required="required"><label style="color:gray">Female</label>
					</div>
					<div class="form-group">
					
					<input type="number" min="1000000000" max="9999999999" class="form-control" id="MNO" name="mno" placeholder="Mobile Number" onblur="mobilenumber();" maxlength="10" required="required">
					<label id="MNOERROR"></label>
					</div>
					<div class="form-group">
					
					<input type="email" class="form-control" id="EMAILID" name="emailid" onblur="emailid1()" placeholder="Email Id" maxlength="45" required="required">
					</div>
					<div class="form-group">
					
					<input type="email" class="form-control" id="VEMAILID" name="vemailid" onblur="emailid1()" placeholder="Verify Email Id" maxlength="45" required="required">
					<label id="VEMAILERROR"></label>
					</div>
					
				
					
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
					<input type="hidden" value="registration"name="page">
				</form>
			</div>
			</div>
			</div>
			
			<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>