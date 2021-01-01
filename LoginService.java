package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import bean.ContactBean;
import bean.RegistrationB;
import converter.ContactConverter;
import converter.LoginConverter;
import db.DBQuires;

public class LoginService {

	public String checkCredintials(HttpServletRequest request) {
		// TODO Auto-generated method stub
		LoginConverter lc=new LoginConverter();
		RegistrationB bean=lc.getLoginData(request);
		DBQuires db=new DBQuires();
		String userName=db.checkLogin(bean);
		return userName;
		
	}

	public int changePwd(HttpServletRequest request, String emailid) {
		// TODO Auto-generated method stub
		LoginConverter lc=new LoginConverter();
		RegistrationB rb=lc.getChangepwd(request);
		DBQuires db=new DBQuires();
		return db.Changepwd(rb,emailid);
	}

	public boolean forgetPassword(HttpServletRequest request) {
		// TODO Auto-generated method stub
		LoginConverter lc=new LoginConverter();
		RegistrationB bean=lc.getForgetPassword(request);
		DBQuires db=new DBQuires();
		bean=db.getForgetPassword(bean);
		if(bean==null)
			return false;
		else{
			CallSMScAPI ls = new CallSMScAPI();
			 ls.SMSSender("adugar", "sms@123","91"+request.getParameter("EMAILID"), bean.getPassword(), "WebSMS", "0");
		}
		return true;
	}

		
	

}
