package webDb;

import java.io.IOException; 
//import java.io.PrintWriter;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

  
  
//Servlet Name 
@WebServlet("/CheckMainKey") 
public class CheckMainKey extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    //variable to store the Barcode to pass it on insertAtr.
    static int bar;
    String Dupl = "";
  
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
    { 
        try { 
  
            // Initialize the database 
            Connection con = DatabaseConnection.initializeDatabase(); 
  
            // Create a SQL query to insert data into Products table 
            // demo table consists of 4 columns, so 4 '?' is used 
            PreparedStatement prodBar = con 
                   .prepareStatement("insert into Products values(?, ?, ?, ?)"); 
  
            
            
            // Store the value of Barcode for later use 
            bar = Integer.valueOf(request.getParameter("id"));
            
            // Get the data using request object 
            // sets the data to prodBar pointer 
            prodBar.setInt(1, Integer.valueOf(request.getParameter("id"))); 
            
            //Setting the rest table parameters with null
            prodBar.setString(2, ""); 
            prodBar.setString(3, "");
            prodBar.setString(4, "");
           
            //Execute the insert command using executeUpdate() 
            // to make changes in database
            prodBar.executeUpdate();
            prodBar.close();
            
            // Close all the connections  
            prodBar.close(); 
            
            //If no exception appears from the database
            //redirect to insertAtr to insert the rest attributes of the product
            response.sendRedirect("insertAtr.html");
            
        } 
        catch (SQLException e) {
        	String SqlExc = e.getMessage();
        	System.out.println(SqlExc);
        	
        	//If an exception appears probably is conflict of primary key in table
        	//if so show the rows of the primary key that cased conflict
        	//store date of the barcode from the database to an arrayList
        	DataCollector prod = new DataCollector(); 
        	ArrayList<ArrayAtr> ProdAtr = prod.DataShow();
        	
        	//Sets a string to be displayed on product.jsp 
        	Dupl = "!!! This product already exists !!!";
        	request.setAttribute("Error", Dupl);
        	//Pass the data to the product.jsp and redirect the user there
        	request.setAttribute("data", ProdAtr);
        	RequestDispatcher rd = request.getRequestDispatcher("product.jsp"); 
        	rd.forward(request, response); 
      
        }
        catch (Exception e) { 
        	e.printStackTrace();
        	response.sendRedirect("index.html");
            
        } 
    } 
    //Method to pass Barcode value
    public int barcode() {
    	return bar;
    }
} 
