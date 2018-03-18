/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class InsertDataServlet extends HttpServlet {

 

  
 

  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        
        
  
        
        
        String first_name=req.getParameter("first_name");  
        String last_name=req.getParameter("last_name");  
        String user_name=req.getParameter("last_name"); 
        String email=req.getParameter("email_address");  
        String password =req.getParameter("password"); 
        
       boolean exist = Database_function.get_Employee_By_email(email);
       if( exist)
       {
           req.setAttribute("Message", "Account is already exist");
           req.getRequestDispatcher("/index.jsp").forward(req, resp);
           
       }
       else
       {
        
        User user = new User();
        
        
        user.set_last_name(last_name);
        user.set_first_name(first_name);
        user.set_user_name(user_name);
        user.set_email(email);
         
         
       
        
        String hash = Utils.prepareRandomString(30);
         
  
        
        String Hash = BCrypt.hashpw(hash, BCrypt.gensalt(12));
        
        
            user.set_id(hash);
            user.set_hash(Hash);
            user.set_password(password);
            user.set_status("new");
            
            
           
            
           Database_function.Inser(user); 
           
           Database_function.send_email_conformation (email, hash , Hash );
           
           
           req.setAttribute("Message", "Account is already ready");
           req.getRequestDispatcher("/index.jsp").forward(req, resp);
           
        
         
       }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
