



import java.sql.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Database_function {
    
    
    

            

    
       public static Connection conection_To_DB()
    {
        Connection con =null;
        try {
           
            con= DriverManager.getConnection("jdbc:derby://localhost:1527/User","tawfik","tawfik");
          
        } catch (Exception ex) {
            System.out.println( ex.getMessage());
        }
          
        
         return con;        
    }
    
  
        public static int Inser(User e )
         {
             
           int status = 0 ;
              

         

            try {
                
                PreparedStatement ps;
                Connection con = Database_function.conection_To_DB();
                ps=con.prepareStatement(" INSERT INTO benutze(id,F_name,L_name,email,password,username,hash,status)VALUES (?,?,?,?,?,?,?,?)");
               
                 
                ps.setString(1,e.get_id());
                 
                ps.setString(2,e.get_first_name() );
                ps.setString(3,e.get_last_name() );
                ps.setString(4,e.get_email());
                ps.setString(5,e.get_password() );
                ps.setString(6, e.get_user_name());
                ps.setString(7,e.get_hash() );
                ps.setString(8,e.get_status() );
            
                 status =ps.executeUpdate(); 
                 
                   con.close();
               
           }catch(Exception ex){ex.getMessage();  }
            
            
            return status;
         }
        
        
        
        
        
        
           public static Boolean get_Employee_By_email(String email){  
          
          boolean t = false;
        try{  
              Connection con = Database_function.conection_To_DB();
             
            PreparedStatement ps =con.prepareStatement (" SELECT * FROM benutze  WHERE email =? ");
            ps.setString(1,email);
            ResultSet rs =ps.executeQuery(); 
            
            if(rs.next()){  
                  t=true;
                 
            }  
               con.close();
        }catch(Exception ex){ex.printStackTrace();}  
          
        return t; 
      
    }
           
           
           
        
        public static void send_email_conformation (String email,String id ,String hash )
      {
      
       String to = email;
     
      String from = "exemple@hotmail.fr";
      
  
     
     Properties props = new Properties(); 

                props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.port", "587");  
     
      Session session = Session.getInstance(props,
		  new javax.mail.Authenticator(){  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication("exemple@hotmail.fr","passwort");  
    
      }  
    });  
      
     
      
  String link = "http://localhost:8080/Register-app//Confirmation?scope=activation&User="+id +"&hash="+hash;
     //compose the message  
      try{  
          
                        
                      String sb = "<head>" +
                "<style type=\"text/css\">" +
                "  .red { color: #f00; }" +
                "</style>" +
                "</head>" +
                "<h1 class=\"red\">" + link + "</h1>" ;  
                      
  

          Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress (to));
            message.setSubject("validierung");

           
            message.setContent(sb, "text/html; charset=utf-8");
          

           
            Transport.send(message);
          
  
      }catch (MessagingException mex) {mex.printStackTrace();} 
       
     
      }
        
        
         public static boolean Confirmation_Email_Hash(String id, String hash)  {
      
      
      boolean verified = false;
      
		ResultSet result = null;
                
		try {
               
                Connection con = Database_function.conection_To_DB();


                PreparedStatement ps=con.prepareStatement("SELECT * FROM benutze WHERE id = ? AND hash  = ?");
               
			ps.setString(1, id);
			ps.setString(2, hash);
			result = ps.executeQuery();
			if (result != null) {
				while (result.next()) {
				  verified = true;
                                
                                       
				}
                                 con.close();
			}
		
		} catch (Exception e) {				
			System.out.println( e);
		}  
           
                return verified;
      
      }
       
 public static void update_status(String id)
       
{
    
    boolean t= false;
    
       Connection con = Database_function.conection_To_DB();

            PreparedStatement ps;

            try {
                ps =con.prepareStatement (" UPDATE benutze SET status =?  WHERE id = ? ");
                ps.setString(1,"active ");
                
                ps.setString(2,id);
             
                ps.executeUpdate();
               

            }catch (Exception e) {
               System.out.println( e);
            }
      
        
}
  public static User check_credential_client (String email, String password  )
         {
             
                User user =null;
   
		ResultSet result = null;
		try {
               
                 Connection con = Database_function.conection_To_DB();


                 PreparedStatement ps=con.prepareStatement("SELECT id ,F_name,L_name,username,email,status,hash,password FROM benutze WHERE   email = ? AND password = ?");
               
			ps.setString(1, email);
		        ps.setString(2, password);
			result = ps.executeQuery();
			if (result != null) {
				while (result.next()) {
                                    
                                    
					 user =new User ();
                                         user.set_id(result.getString(1));
                                         user.set_first_name(result.getString(2)); 
                                         user.set_last_name(result.getString(3));
                                         user.set_user_name(result.getString(4));
                                         user.set_email(result.getString(5));  
                                         user.set_status(result.getString(6));
                                         user.set_hash(result.getString(7));
                                         user.set_password(result.getString(8));
				}
                               
			}
		
		} catch (Exception e) {				
			System.out.println( e);
		}  
       
      return user;
    }
       
    
}
