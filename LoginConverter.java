package converter;

import javax.servlet.http.HttpServletRequest;

import bean.RegistrationB;

public class LoginConverter {

	public RegistrationB getLoginData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationB rb=new RegistrationB();
		rb.setMailid(request.getParameter("EMAILID"));
		rb.setPassword(request.getParameter("PASSWORD"));
		return rb;
	}

	

	public RegistrationB getChangepwd(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationB rb=new RegistrationB();
		rb.setPassword(request.getParameter("CPWD"));
		rb.setChangepwd(request.getParameter("NPWD"));
		return rb;
	}



	public RegistrationB getForgetPassword(HttpServletRequest request) {
		// TODO Auto-generated method stub
		RegistrationB rb=new RegistrationB();
		rb.setMailid(request.getParameter("EMAILID"));
		return rb;
	
	}

}
