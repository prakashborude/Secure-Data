package util;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EMail {

	String from= "workprojectmail0@gmail.com";
	String pass = "Top@1234";
	public boolean sendMail(String to,String fromside,String name) {
		// TODO Auto-generated method stub
		
	     


		try {	
	        String host = "smtp.gmail.com";

	        Properties props = System.getProperties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	       // props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.debug", "true");

	        //props.put("mail.smtp.port", "25"); 
	        //props.put("mail.debug", "true"); 
	        //props.put("mail.smtp.auth", "true"); 
	        props.put("mail.smtp.starttls.enable","true"); 
	        props.put("mail.smtp.EnableSSL.enable","true");
	        
	        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
	        props.setProperty("mail.smtp.socketFactory.fallback", "false");   
	        props.setProperty("mail.smtp.port", "465");   
	        props.setProperty("mail.smtp.socketFactory.port", "465"); 
	        
	        
	        //Session session = Session.getDefaultInstance(props, null);
	        
	        Session session = Session.getInstance(props, new GMailAuthenticator(from, pass));
	        MimeMessage message = new MimeMessage(session);
	        Address fromAddress = new InternetAddress(from);
	        Address toAddress = new InternetAddress(to);

	        message.setFrom(fromAddress);
	        
	        //message.setRecipient(Message.RecipientType.TO, toAddress);
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        message.addRecipient(Message.RecipientType.TO,toAddress);
	        message.setSubject("Your account activation for Huffman");
	        message.setText("Hi User, File is Shared from user \n "+fromside+" .\n Name of the file is "+ name +". \n Please login and Upload the file");
	        Transport transport = session.getTransport("smtp");
	        transport.connect(host, from, pass);
	        message.saveChanges();
	        Transport.send(message,message.getAllRecipients());
	        transport.close();

	    }catch(MessagingException ex){

	    	return false;
	        //out.println("<html><head></head><body>");
	        //out.println("ERROR: " + ex);
	        //out.println("</body></html>");
	    }
	    return true;
}

	
}