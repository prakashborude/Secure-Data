package service;



import javax.servlet.http.HttpServletRequest;

import converter.RegistrationConverter;
import db.DBQuires;
import bean.RegistrationB;

public class RegistrationS {

	public boolean insertValues(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationConverter convert =new RegistrationConverter();
		RegistrationB bean=convert.getValues(request);
		DBQuires db =new DBQuires();
		boolean isSuccess = db.insertRegistrationForm(bean);
		if(isSuccess==true){
			CallSMScAPI ls = new CallSMScAPI();
			 ls.SMSSender("adugar", "sms@123","91"+bean.getContactno(), "User name: "+request.getParameter("EMAILID")+"\nPassword:"+ bean.getPassword(), "WebSMS", "0");
		}
		return isSuccess;
		
		
	}

}
