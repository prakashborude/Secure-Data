package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.LoginService;
import service.RegistrationS;
import util.EMail;
import bean.FileBean;
import db.DBQuires;

 class RegistrationServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  static final int BLACK = -16777216;
  static final int WHITE = -1;
  public static final String DEFAULT_FORMAT = "jpg";
  
  public RegistrationServlet() {}
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    System.out.println("-------------------------");
    doPost(request, response);
  }
  
  public static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;
  public static final Font DEFAULT_FONT = new Font("Arial", 1, 18);
  



  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String action = request.getParameter("page");
    String nextpath = "";
    HttpSession session = request.getSession(true);
    System.out.println(action);
    if ("registration".equals(action)) {
      RegistrationS service = new RegistrationS();
      boolean isSuccess = service.insertValues(request);
      if (!isSuccess) {
        request.setAttribute(
          "message", 
          " This might happen because of the below errors <br>1.Duplicate Email-Id <br>2.Unable to connect Database<br>3.Unable to connect Internet");
      } else {
        request.setAttribute("message", 
          "Account created successfully<br> Please visit your " + 
          request.getParameter("mno") + 
          " for the credential");
      }
      nextpath = "/JSP/Registration.jsp";
    }
    else if ("login".equals(action)) {
      System.out.println("In Login");
      
      LoginService ls = new LoginService();
      String username = ls.checkCredintials(request);
      
      System.out.println(username);
      if (username == null) {
        request.setAttribute("username", "invalid");
        nextpath = "/JSP/Login.jsp";
      } else {
        nextpath = "/JSP/Portal.jsp";
        ArrayList<String> UserEmailId = new DBQuires()
          .getUserList(request.getParameter("EMAILID"));
        request.setAttribute("UserEmailId", UserEmailId);
        request.setAttribute("username", username);
        
        session.setAttribute("emailid", request.getParameter("EMAILID"));
      }
    }else if("Upload".equals(action)) {
    	nextpath = "/JSP/Portal.jsp";
        ArrayList<String> UserEmailId = new DBQuires()
          .getUserList((String)session.getAttribute("emailid"));
        request.setAttribute("UserEmailId", UserEmailId);
        
    }
    else if ("getfile".equals(action)) {
	String imagename = (String)request.getParameter("filename");
    	
    	
    	String actualimage = imagename.split("@")[0]+"."+imagename.split("\\.")[1];
    	
    	int isDownloadAble = new DBQuires().checkMatch(actualimage);
    	String username = new DBQuires().checkSender(actualimage);
    	if(isDownloadAble == 0){
    		request.setAttribute("image", "Not all file Uploaded");
    	}else{
    		try{
    		Main.decrypt_image = ImageFunctions.Decrypt(Main.image1, Main.image2);
			ImageFunctions.Display_Image(Main.decrypt_image, "Decrypted Image");
			
			// Save the decrypted image
			ImageFunctions.Save(Main.decrypt_image, Main.image_decrypt_file);

			// Create, display, and save scaled image (same size as original image
			Main.normal_size_decrypted_image = ImageFunctions.Shrink(Main.decrypt_image);
			ImageFunctions.Display_Image(Main.normal_size_decrypted_image, "Regular Sized Decrypted Image");				
			ImageFunctions.Save(Main.normal_size_decrypted_image, Main.normal_size_decrypted_file);
    		}catch (Exception e) {
				// TODO: handle exception
			}
    		response.setContentType("text/html");  
    		PrintWriter out = response.getWriter();    		
    		String filepath = "e://"+username+"\\Upload\\";
    		response.setContentType("APPLICATION/OCTET-STREAM");   
    		response.setHeader("Content-Disposition","attachment; filename=\"" + actualimage + "\"");   
    		  
    		FileInputStream fileInputStream = new FileInputStream(filepath + actualimage+"@"+isDownloadAble);  
    		            
    		int i;   
    		while ((i=fileInputStream.read()) != -1) {  
    		out.write(i);   
    		}   
    		fileInputStream.close();   
    		out.close();   
    		}  

    	nextpath = "/RegistrationServlet?page=download";	
    	}
    
    
    else if ("status".equals(action)) {
    	  request.setAttribute("list", new DBQuires().getDownloading((String)session.getAttribute("emailid")));
    	  nextpath="/JSP/status.jsp";
    	
    }
    else if ("upload".equals(action)) {
    	String imagename = (String)request.getParameter("filename");
    	
    	
    	String actualimage = imagename.split("@")[0]+"."+imagename.split("\\.")[1];
    	
    	String sender = new DBQuires().checkSender(actualimage);
    	InputStream inStream = null;
    	OutputStream outStream = null;
    	String Upload = "E://" + session.getAttribute("emailid")+"//";
        	try{

        	    File afile =new File(Upload+imagename);
        	    File bfile =new File("E:\\"+sender+"\\"+imagename);
        	    if(bfile.exists()){
        	    	
        	    }
        	    else{
        	    	new DBQuires().updateCount(actualimage);
        	    inStream = new FileInputStream(afile);
        	    outStream = new FileOutputStream(bfile);

        	    byte[] buffer = new byte[1024];

        	    int length;
        	    //copy the file content in bytes
        	    while ((length = inStream.read(buffer)) > 0){
        	    	outStream.write(buffer, 0, length);
        	    }
        	    inStream.close();
        	    outStream.close();
        	    System.out.println("File is copied successful!");
        	    }
        	}catch(IOException e){
        		e.printStackTrace();
        	}
        
    	nextpath ="/RegistrationServlet?page=download";
    }
    else if ("download".equals(action)) {
      ArrayList<FileBean> filelist = null;
      session = request.getSession(true);
      File folder = new File("E://" + session.getAttribute("emailid"));
      if (folder != null) {
        File[] listOfFiles = folder.listFiles();
        filelist = new ArrayList();
        for (int i = 0; (listOfFiles != null) && (i < listOfFiles.length); i++) {
          if (listOfFiles[i].isFile()) {
            FileBean bean = new FileBean();
            System.out.println(listOfFiles[i].getName());
            bean.setFilename(listOfFiles[i].getName());
            bean.setFilesize(listOfFiles[i].length() / 1024L);
            filelist.add(bean);
          }
        }
      }
      
      request.setAttribute("filelist", filelist);
      nextpath = "/JSP/Download.jsp";
    } else if ("forgetpassword".equalsIgnoreCase(action)) {
      LoginService ls = new LoginService();
      boolean isSuccess = ls.forgetPassword(request);
      if (!isSuccess) {
        request.setAttribute("msg", "false");
      } else
        request.setAttribute("msg", "true");
      nextpath = "/JSP/Forgetpassword.jsp";
    } else {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload sfu = new ServletFileUpload(factory);
      
      String name = null;
      if (ServletFileUpload.isMultipartContent(request)) {
        try
        {
          String username = "";
          String mailid = (String)session.getAttribute("emailid");
          String UPLOAD_DIRECTORY = "E:\\" + mailid;
          List<FileItem> multiparts = sfu.parseRequest(request);
          File file = new File(UPLOAD_DIRECTORY);
          if (!file.exists()) {
            if (file.mkdir()) {
              System.out.println("Directory is created!");
            } else {
              System.out.println("Failed to create directory!");
            }
          }
          UPLOAD_DIRECTORY = "E:\\" + mailid +"\\upload";
          file = new File(UPLOAD_DIRECTORY);
          if (!file.exists()) {
            if (file.mkdir()) {
              System.out.println("Directory is created!");
            } else {
              System.out.println("Failed to create directory!");
            }
          }
          for (FileItem item : multiparts) {
            if (!item.isFormField()) {
              name = new File(item.getName()).getName();
              
              item.write(new File(UPLOAD_DIRECTORY + "\\" + name));
            }
            else {
              System.out.println("******************");
              username = username + item.getString() + ",";
              System.out.println(username + 
                "$$$$$$$$$$$$$$$$$$$$$4");
            }
          }
          //Cloud Code
          
          

          try {
 			 Runtime.getRuntime().exec("cmd /c start c:\\bat.bat " +  name + " " + "\""+UPLOAD_DIRECTORY + "\\" + name+"\"");
              //Runtime.getRuntime().exec("cmd.exe /c c:\\bat.bat " +  fname + " " + "D:\\upload\\" +servername+"\\"+itemName;
              System.out.println("ok");
          } catch (IOException ex) {
              ex.printStackTrace();
          }

          File finput = new File(UPLOAD_DIRECTORY + 
            "//" + name);
          
          File fout = new File(UPLOAD_DIRECTORY + "\\" + name + "@" + username.split(",").length);
          
          Thread.sleep(10000);
        

        new DBQuires().insertImageInfo(mailid,name,username.split(",").length+"");

          Main.save_key_magnified_path = UPLOAD_DIRECTORY + "//" + 
            name + "_key_magnified.png";
          Main.save_cipher_magnified_path = UPLOAD_DIRECTORY + "//" + 
            name + "_cipher_magnified.png";
          Main.key_magnified_file = new File(
            Main.save_key_magnified_path);
          Main.cipher_magnified_file = new File(
            Main.save_cipher_magnified_path);
          Main.save_path = UPLOAD_DIRECTORY + "//" + name + "enc";
          finput.renameTo(fout);
          Main.originalImage = ImageIO.read(fout);
          BufferedImage black_white = new BufferedImage(
            Main.originalImage.getWidth(), 
            Main.originalImage.getHeight(), 
            12);
          Graphics2D graphics = black_white.createGraphics();
          graphics.drawImage(Main.originalImage, 0, 0, null);
          

          Main.bw_file = new File(Main.save_path + ".png");
          ImageFunctions.Save(black_white, Main.bw_file);
          


          BufferedImage key_image = new BufferedImage(
            Main.originalImage.getWidth(), 
            Main.originalImage.getHeight(), 
            12);
          

          Random rand = new Random();
          try {
            SecureRandom secureRandomGenerator = 
              SecureRandom.getInstance("SHA1PRNG");
            
            for (int i = 0; i < key_image.getHeight(); i++) {
              for (int j = 0; j < key_image.getWidth(); j++)
              {
                int result = secureRandomGenerator.nextInt(100);
                if (result < 50) {
                  key_image.setRGB(j, i, -1);
                } else {
                  key_image.setRGB(j, i, -16777216);
                }
              }
            }
          }
          catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
          }
          
          Main.save_key_path = Main.save_path + "_key.png";
          Main.key_file = new File(Main.save_key_path);
          

          ImageFunctions.Save(key_image, Main.key_file);
          

          BufferedImage magnified_key_image = 
            ImageFunctions.Magnify(key_image);
          ImageFunctions.Save(magnified_key_image, 
            Main.key_magnified_file);
          



          Main.cipher_image = ImageFunctions.Create_Cipher(
            black_white, key_image);
          BufferedImage magnified_cipher_image = 
            ImageFunctions.Magnify(Main.cipher_image);
          ImageFunctions.Save(magnified_cipher_image, 
            Main.cipher_magnified_file);
          





          String[] usernameList = username.split(",");
          
          for (int i = 0; i < usernameList.length; i++) {
            file = new File("E:\\" + usernameList[i]);
            if (!file.exists()) {
              if (file.mkdir()) {
                System.out.println("Directory is created!");
              }
              else {
                System.out.println("Failed to create directory!");
              }
            }
          }
          


          file = new File(Main.save_cipher_magnified_path);
          



          FileInputStream fis = new FileInputStream(file);
          BufferedImage image = ImageIO.read(fis);
          

          int rows = 1;
          
          int cols = usernameList.length;
          int chunks = rows * cols;
          
          int chunkWidth = image.getWidth() / cols;
          

          int chunkHeight = image.getHeight() / rows;
          int count = 0;
          BufferedImage[] imgs = new BufferedImage[chunks];
          




          for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++)
            {
              imgs[count] = new BufferedImage(chunkWidth, 
                chunkHeight, image.getType());
              

              Graphics2D gr = imgs[(count++)].createGraphics();
              gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, 
                chunkWidth * y, chunkHeight * x, chunkWidth * 
                y + chunkWidth, chunkHeight * x + 
                chunkHeight, null);
              gr.dispose();
            }
          }
          System.out.println("Split44444444ting done");
          String[] im = name.split("\\.");
          
          for (int i = 0; i < imgs.length; i++) {
            ImageIO.write(imgs[i], "jpg", new File("E:\\" + 
              usernameList[i] + "\\" + im[0] + "magnified@"+i+"." + im[1]));
            
            makeWaterMark(im[0] + "magnified@"+i+"." + im[1], "E:\\" + 
              usernameList[i] + "\\", i + 1 + "," + usernameList.length);
            File file1 = new File("E:\\" + 
              usernameList[i] + "\\" + im[0] + "magnified@"+i+"." + im[1]);
            
            if (file1.delete()) {
              System.out.println(file.getName() + " is deleted!");
            } else {
              System.out.println("Delete operation is failed.");
            }
            
            new EMail().sendMail(usernameList[i], mailid, name);
          }
          System.out.println("Mini images created");
          
          request.setAttribute("message", 
            "Thank you for Uploading the file");
          nextpath = "/JSP/Portal.jsp";
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        
      }
    }
    


    RequestDispatcher rd = getServletContext().getRequestDispatcher(
      nextpath);
    rd.forward(request, response);
  }
  
  public static String makeWaterMark(String fileName, String ctx, String watermark) throws Exception
  {
    try {
      String dest = execute(ctx + fileName, ctx + fileName.replaceAll("magnified", ""), watermark, DEFAULT_COLOR, DEFAULT_FONT);
      System.out.println("---------");
    }
    catch (Exception ex) {
      System.out.println(ex);
    }
    return null;
  }
  
  public static String execute(String src, String dest, String text, Color color, Font font) throws Exception {
    BufferedImage srcImage = ImageIO.read(new File(src));
    
    int width = srcImage.getWidth(null);
    int height = srcImage.getHeight(null);
    BufferedImage destImage = new BufferedImage(width, height, 
      1);
    Graphics g = destImage.getGraphics();
    
    g.drawImage(srcImage, 0, 0, width, height, null);
    g.setColor(color);
    g.setFont(font);
    g.fillRect(0, 0, 50, 50);
    g.drawString(text, width / 5, height - 10);
    g.dispose();
    
    ImageIO.write(destImage, "jpg", new File(dest));
    System.out.println("+++++++++++++");
    return dest;
  }
}