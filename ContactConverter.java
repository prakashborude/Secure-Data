package converter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.ContactBean;

public class ContactConverter {

	public ContactBean getContactBean(HttpServletRequest request,
			HttpSession session) {
		// TODO Auto-generated method stub
ContactBean cb=new ContactBean();
System.out.println(session.getAttribute("emailid"));
cb.setEmailid((String)session.getAttribute("emailid"));
cb.setName(request.getParameter("CNAME"));
cb.setNo(request.getParameter("MNO"));
return cb;

			
	}

}
