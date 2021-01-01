package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.GeneratePassword;

import bean.ContactBean;
import bean.RegistrationB;

public class DBQuires {

	public boolean insertRegistrationForm(RegistrationB rb) {
		// TODO Auto-generated method stub
		int rows = 0;
	DAO dao=new DAO();
	
	Connection conn=dao.getConnection();
	String password=GeneratePassword.randomPasswordIs();
	rb.setPassword(password);
	String insertquery="insert into registration values(?,?,?,?,?,?,?,?)";
	PreparedStatement pstmt=null;
	try{
		pstmt=conn.prepareStatement(insertquery);;
		pstmt.setString(1,rb.getFname());
		pstmt.setString(2,rb.getLname());
		pstmt.setString(3,rb.getContactno());
		pstmt.setString(4,rb.getMailid());
		pstmt.setString(5,rb.getGender());
		pstmt.setString(6,rb.getField());
		pstmt.setString(7,rb.getDept());
		pstmt.setString(8,rb.getPassword());
	
		
	 rows=pstmt.executeUpdate();
	
	}catch(SQLException e){
		System.out.println(e);
	}
	finally{
		try {
			pstmt.close();
		
		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	if(rows==0)
		return false;
	
	return true;

	}
	
	public boolean insertImageInfo(String from,String imagename,String qty) {
		// TODO Auto-generated method stub
		int rows = 0;
	DAO dao=new DAO();
	
	Connection conn=dao.getConnection();
	String insertquery="insert into imageinfo values(?,?,?,?)";
	PreparedStatement pstmt=null;
	try{
		pstmt=conn.prepareStatement(insertquery);;
		pstmt.setString(1,from);
		pstmt.setString(2,imagename);
		pstmt.setString(3,qty);
		pstmt.setString(4,"0");
		rows=pstmt.executeUpdate();
	
	}catch(SQLException e){
		System.out.println(e);
	}
	finally{
		try {
			pstmt.close();
		
		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	if(rows==0)
		return false;
	
	return true;

	}

	
	

	public String checkLogin(RegistrationB bean) {
		// TODO Auto-generated method stub
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select * from  registration where emailid=? and password = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, bean.getMailid());
			preparedStatement.setString(2, bean.getPassword());
			
			// execute insert SQL statement
			ResultSet result=preparedStatement.executeQuery();
			if(result.next())
			{
				return result.getString(1);
			}
			
System.out.println(preparedStatement.toString());
		}
		
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// TODO Auto-generated method stub

		return null;
	}
	
	public String checkSender(String imageName) {
		// TODO Auto-generated method stub
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select * from  imageinfo where imagename=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, imageName);
			
			// execute insert SQL statement
			ResultSet result=preparedStatement.executeQuery();
			if(result.next())
			{
				return result.getString(1);
			}
			
System.out.println(preparedStatement.toString());
		}
		
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// TODO Auto-generated method stub

		return null;
	}


	

	public int Changepwd(RegistrationB rb, String emailid) {
		// TODO Auto-generated method stub
		
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select password from  registration where emailid=? ";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, emailid);
			
			System.out.println("Email id"+emailid+rb.getPassword());
			// execute insert SQL statement
			ResultSet result=preparedStatement.executeQuery();
			
			if(result.next())
			{
				System.out.println(result.getString(1));
				if(result.getString(1).equals(rb.getPassword()))
				{
					validateUser="update registration set password=? where emailid=?";
					preparedStatement=conn.prepareStatement(validateUser);
					preparedStatement.setString(1,rb.getChangepwd());
					preparedStatement.setString(2, emailid);
					return preparedStatement.executeUpdate();
				}
				else
				{
					return 0;
				}
			}
			

		}
		
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		// TODO Auto-generated method stub
		return 0;
	}
	public ArrayList<String> getDownloading(String emailid) {
		// TODO Auto-generated method stub
		ArrayList<String> str = new ArrayList<String>();
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select * from  imageInfo where name=? ";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, emailid);
			
			// execute insert SQL statement
			ResultSet result=preparedStatement.executeQuery();
			
			while(result.next())
			{
				String p = result.getString(1)+"," +result.getString(2)+","+result.getString(3)+","+result.getString(4);
				str.add(p);
			}
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		// TODO Auto-generated method stub
		return str;
	}


	public RegistrationB getForgetPassword(RegistrationB bean) {
		// TODO Auto-generated method stub
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select password from  registration where contactno=? ";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, bean.getMailid());
			
			
			// execute insert SQL statement
			ResultSet result=preparedStatement.executeQuery();
			if(result.next())
			{
				bean.setPassword(result.getString(1));
				System.out.println(bean.getPassword());
				return bean;
			}
			

		}
		
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		// TODO Auto-generated method stub

		return null;
	}
	
	
	
	

	public ArrayList<String> getUserList(String emailid) {
		// TODO Auto-generated method stub
ArrayList<String> s = new ArrayList<String>();
		DAO data=new DAO();
		Connection conn=data.getConnection();
		System.out.println("emailid"+emailid);
		String validateUser = "select emailid from  registration where emailid != ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			
			preparedStatement.setString(1, emailid);
			// execute insert SQL statement
			ResultSet result=preparedStatement.executeQuery();
			while(result.next())
			{
				System.out.println(result.getString(1));
				s.add(result.getString(1));
			}
			

		}
		
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println(preparedStatement.toString());
		

		// TODO Auto-generated method stub

		return s;
	
	}

	public void updateCount(String actualimage) {

		// TODO Auto-generated method stub
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select receivedqty from  imageinfo where imagename=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, actualimage);
			
			// execute insert SQL statement
			ResultSet result=preparedStatement.executeQuery();
			if(result.next())
			{
				String qty =(Integer.parseInt(result.getString(1))+1)+"";
				validateUser = "update imageinfo set receivedqty=? where imagename=?";
				preparedStatement = conn.prepareStatement(validateUser);
				preparedStatement.setString(1, qty);
				preparedStatement.setString(2, actualimage);
				preparedStatement.executeUpdate();
				
			}
			
System.out.println(preparedStatement.toString());
		}
		
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// TODO Auto-generated method stub

	
		
	}

	public int checkMatch(String actualimage) {

		// TODO Auto-generated method stub
		ArrayList<String> str = new ArrayList<String>();
		DAO data=new DAO();
		Connection conn=data.getConnection();
		String validateUser = "select qty,receivedqty from  imageInfo where imagename=? ";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(validateUser);
			preparedStatement.setString(1, actualimage);
			
			// execute insert SQL statement
			ResultSet result=preparedStatement.executeQuery();
			
			while(result.next())
			{
				int p =Integer.parseInt(result.getString(1))-Integer.parseInt(result.getString(2));
				if(p == 0)
					return Integer.parseInt(result.getString(1));
					else
						return 0;
			}
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		// TODO Auto-generated method stub
	
		// TODO Auto-generated method stubret
		return 0;
	}
	
	
	
}
