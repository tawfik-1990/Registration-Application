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


public class loginservlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        String email = request.getParameter("Email");
	String password = request.getParameter("password");
       
        
               
          User user = Database_function.check_credential_client (email, password );
          
       try {
             if(user!= null)
             {
                 System.out.print(user);
                  if(user.get_status().equals("new"))
                  {
                  request.setAttribute("Message", "your account not confirmed  ");
                  request.getRequestDispatcher("index.jsp").forward(request, response);
               
                  }else 
                  {
                 request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                  
                  }
             }    
          else 
            {
                request.setAttribute("Message", "Pleas check your data ");
                request.getRequestDispatcher("index.jsp").forward(request, response);       
                      
             }
        
            }
       catch (Exception e)
               {
                   System.out.println("Log In failed: An Exception has occurred! " + e);
           
              }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
