package webDb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
  
  
// Servlet Name 
@WebServlet("/InsertAtr") 
public class InsertAtr extends HttpServlet { 
    private static final long serialVersionUID = 1L; 
    String Dupl = "";
    protected void doPost(HttpServletRequest request,  
HttpServletResponse response) 
        throws ServletException, IOException 
    { 
        try { 
  
            // Initialize the database 
            Connection con = DatabaseConnection.initializeDatabase(); 
  
            // Create a SQL query to insert data into demo table 
            // demo table consists of 4 columns, so 4 '?' is use
            
            PreparedStatement update = con 
                    .prepareStatement("UPDATE Products SET name=?, color=?, description=? WHERE barcode=?");
  
            //We take the Barcode parameter from the CheckMainKey so the user don't have to put it again.
            CheckMainKey barc = new CheckMainKey();
            int bar = barc.barcode();
            
            
            update.setInt(4, bar);
            //Get the rest parameters from user 
            update.setString(1, request.getParameter("string1")); 
            update.setString(2, request.getParameter("string2"));
            update.setString(3, request.getParameter("string3"));
            
            //Execute the insert command using executeUpdate() 
            // to make changes in database 
            update.executeUpdate();
            update.close();
         
            // Close all the connections    
            con.close(); 
  
        	//Store date of the barcode from the database to an arrayList
            DataCollector prod = new DataCollector(); 
        	ArrayList<ArrayAtr> ProdAtr = prod.DataShow();
        	
        	//Sets a void string to be displayed on product.jsp 
        	request.setAttribute("Error", Dupl);
        	
        	//Pass the data to the product.jsp and redirect the user there
        	request.setAttribute("data", ProdAtr);
        	RequestDispatcher rd = request.getRequestDispatcher("product.jsp"); 
        	rd.forward(request, response); 
            
            
        } 
        catch (SQLException e) {
        	
        	String SqlExc = e.getMessage();
        	System.out.println(SqlExc);
        	PrintWriter out = response.getWriter();
        	out.println("<html>\n"
        			   + "<link rel='stylesheet' type='text/css' href='/static/hadoop.css'>\n"
        			   + "<title>" + SqlExc + "</title>\n"
        			   + "<body>\n"
        			   + "<h1>" + SqlExc + "</h1>\n");
        	
        	
           
        }
        catch (Exception e) { 
            e.printStackTrace();
              
        } 
    } 
} 
