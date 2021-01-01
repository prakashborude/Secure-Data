package converter;

import javax.servlet.http.HttpServletRequest;

import bean.RegistrationB;

public class RegistrationConverter {

	public RegistrationB getValues(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		RegistrationB bean = new RegistrationB();
		bean.setFname(request.getParameter("fname"));
		bean.setLname(request.getParameter("lname"));
		bean.setContactno(request.getParameter("mno"));
		bean.setMailid(request.getParameter("emailid"));
		bean.setGender(request.getParameter("gender"));
		return bean;
	
		
	}

}
