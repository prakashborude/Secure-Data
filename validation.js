function emailid1()
{
	var emaild1=document.getElementById("EMAILID").value;
	var vemaild=document.getElementById("VEMAILID").value;
	if(emaild1 != vemaild )
		{
		document.getElementById("VEMAILERROR").innerHTML = "Emailid and Verify Emaild should be same";
		document.getElementById("VEMAILERROR").style.color="red";
		document.getElementById("VEMAILERROR").style.visibility="visible";
		return false;

			}


	document.getElementById("VEMAILERROR").style.visibility="hidden";

return true;
}

function firstname()
{
	
	var fname=document.getElementById("FNAME").value;
		var invalidchar= new RegExp("[a-zA-Z]+");
		for(var i=0 ;i<fname.length;i++){
				if(!invalidchar.test(fname[i]))
					{
					document.getElementById("FNAMEERROR").innerHTML="First name should contain only alphabet(A to Z)";
					document.getElementById("FNAMEERROR").style.color="red";
					document.getElementById("FNAMEERROR").style.visibility="visible";
					return false;

					}
}
				document.getElementById("FNAMEERROR").style.visibility="hidden";
		return true;

}


function lastname()
{
	
	var lname=document.getElementById("LNAME").value;
	var invalidchar= new RegExp("[a-zA-Z]+");
	for(var i=0 ;i<lname.length;i++){
		if(!invalidchar.test(lname[i]))
			{
			document.getElementById("LNAMEERROR").innerHTML="Last Name should contain only alphabet(A to Z)";
			document.getElementById("LNAMEERROR").style.color="red";
			document.getElementById("LNAMEERROR").style.visibility="visible";
			return false;

		}
	}
	
	document.getElementById("LNAMEERROR").style.visibility="hidden";
	
return true;
}



   
function mobilenumber()
{
	
	var phone=document.getElementById("MNO").value;
	var invalidchar= new RegExp("[0-9]+");
	for(var i=0 ;i<phone.length;i++){
		if(!invalidchar.test(phone[i]) || phone.length<10)
			{
		document.getElementById("MNOERROR").innerHTML="Mobile number should be 10 digits";
		document.getElementById("MNOERROR").style.color="red";
		document.getElementById("MNOERROR").style.visibility="visible";
		return false;

			}

}
		




	document.getElementById("MNOERROR").style.visibility="hidden";

return true;
}

function checkvalues()
{
	
	if(firstname()==true && lastname()==true && mobilenumber()==true  && emailid() == true)
		{this.registrationform.submit();
		return true;
		}
	else 
		return false;
}
	

