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


public class ConfirmationEmailServlet extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(request, resp);
        
        resp.setContentType("text/html");
        
        
        
        
        
        
                                String Id =    request.getParameter("User");
				String hash =  request.getParameter("hash");
				String scope = request.getParameter("scope");
				
                                
                                
                                
                                 
                                if ((Database_function.Confirmation_Email_Hash(Id,hash))&& (scope.equals("activation")))
                                
                                {
                                   Database_function.update_status(Id) ;
					
				    request.setAttribute("Message", "Account is already  ");
                                    request.getRequestDispatcher("/index.jsp").forward(request, resp);
					   		
				} 
                                else
                                {
                                    request.setAttribute("Message", "Confirmation is falsh");
                                    request.getRequestDispatcher("/index.jsp").forward(request, resp);
                                    
                                 }
                                
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
