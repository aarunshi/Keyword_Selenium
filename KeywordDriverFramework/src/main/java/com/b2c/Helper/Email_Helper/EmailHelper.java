package com.b2c.Helper.Email_Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//package com.b2c.Test;


import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport; 
  
  
public class EmailHelper
{ 
      
//    public static void main(String [] args)  
//    {  
//    	EmailReport("aarunshi.malhotra@biz2credit.com","admin@b2cdev.com","smtp.gmail.com","T&AmA6TC","VCFO","C:\\Users\\aarunshi.malhotra\\git\\B2C_Automation_POC_VCFO\\KeywordDriverFramework\\src\\main\\java\\com\\b2c\\Reports\\attachment.txt");
//    }
    /*
	  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 11 Nov 2018
	  * Description : Function to send report on mail
	  */
    public static void EmailReport(String Erecipient, final String Esender, String Ehost,  final String EPassword ,String Bank,String filename,String ReportName)
    {
    	
        // Getting system properties 
    	
    	final String sender = Esender;
    	final String Password=  EPassword;
        Properties properties = System.getProperties(); 
      
        // Setting up mail server 
        properties.put("mail.smtp.ssl.trust", "*");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", Ehost);
        
        // creating session object to get properties 
        Session session = Session.getDefaultInstance(properties, 
        	    new javax.mail.Authenticator(){
        	        protected PasswordAuthentication getPasswordAuthentication() {
        	            return new PasswordAuthentication(
        	            		sender, Password);// Specify the Username and the PassWord
        	        }
        	}); 
        
      
        try 
        { 
        	
        	//SmtpAuthenticator authentication = new SmtpAuthenticator();
            // MimeMessage object. 
            MimeMessage message = new MimeMessage(session); 
      
            // Set From Field: adding senders email to from field. 
            message.setFrom(new InternetAddress(sender)); 
      
            // Set To Field: adding recipient's email to from field. 
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(Erecipient)); 
      
            // Set Subject: subject of the email 
            message.setSubject("Automation Regression Suite Report for :"+Bank+" _Dated : "+ System.currentTimeMillis()); 
              
            // creating first MimeBodyPart object 
            BodyPart messageBodyPart1 = new MimeBodyPart();  
          
            String newline =System.getProperty("line.separator"); 
            
            messageBodyPart1.setText("Hi All,"+newline+"Please Find Attached the Automation Regression Test suite Report ."+newline+"Kindly Refer Report :  "+ReportName +newline+"Thanks");
              
            // creating second MimeBodyPart object 
            BodyPart messageBodyPart2 = new MimeBodyPart();  
             
            Multipart multipartObject = new MimeMultipart();
            
            zipFolder(filename, filename+".zip");
            
//            
          DataSource source = new FileDataSource(filename+".zip");   
          messageBodyPart2.setDataHandler(new DataHandler(source));   
          String newfilename = filename.substring(filename.lastIndexOf("Reports")+8,filename.length());
//       	System.out.println("actual name is "+newfilename);
            messageBodyPart2.setFileName(newfilename+".zip");   
//            int size =Loc.size();
//            System.out.println("size is"+size);
//            for ( int i=0;i<size;i++)
//            {
//            	BodyPart messageBodyPart = new MimeBodyPart();
//            	  System.out.println("location of files is"+Loc.get(i));
//                 
//                 DataSource source2 = new FileDataSource(Loc.get(i));   
//                 messageBodyPart.setDataHandler(new DataHandler(source2));   
//                 String newfilenameTC = Loc.get(i).substring(Loc.get(i).lastIndexOf("//")+1,Loc.get(i).length());
//                 messageBodyPart.setFileName(newfilenameTC); 
//                 multipartObject.addBodyPart(messageBodyPart);
//            }
//              
//            // creating MultiPart object 
//               
      multipartObject.addBodyPart(messageBodyPart1);   
            multipartObject.addBodyPart(messageBodyPart2);
//            
//      
            // set body of the email. 
            message.setContent(multipartObject); 
            // Send email. 
            Transport.send(message); 
            System.out.println("Mail successfully sent"); 
        } 
        catch (MessagingException e)  
        { 
        	System.out.println(e);
        	 System.out.println("Mail did not sent successfully ");
        } catch (Exception e) {
        	System.out.println(e);
        	 System.out.println("Mail did not sent successfully ");
		} 
    } 
    static public void zipFolder(String srcFolder, String destZipFile) throws Exception {
        ZipOutputStream zip = null;
        FileOutputStream fileWriter = null;

        fileWriter = new FileOutputStream(destZipFile);
        zip = new ZipOutputStream(fileWriter);

        addFolderToZip("", srcFolder, zip);
        zip.flush();
        zip.close();
      }

      static private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
          throws Exception {

        File folder = new File(srcFile);
        if (folder.isDirectory()) {
          addFolderToZip(path, srcFile, zip);
        } else {
          byte[] buf = new byte[1024];
          int len;
          FileInputStream in = new FileInputStream(srcFile);
          zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
          while ((len = in.read(buf)) > 0) {
            zip.write(buf, 0, len);
          }
        }
      }

      static private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
          throws Exception {
        File folder = new File(srcFolder);

        for (String fileName : folder.list()) {
          if (path.equals("")) {
            addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
          } else {
            addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
          }
        }
      }
    }


