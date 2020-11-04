package webDb;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class DataCollector  {
	public ArrayList<ArrayAtr> DataShow() { 
		// Creates an arrayList to temporary store the data of the database 
		ArrayList<ArrayAtr> prodList = new ArrayList<ArrayAtr>();
				
					try {
														
						// Initialize the database
						Connection con = DatabaseConnection.initializeDatabase(); 
        
						// Create a SQL query to insert data into Products table 
						PreparedStatement atdb = con.prepareStatement("select * from Products where barcode=?");

						// Get the barcode value from from initial input
						CheckMainKey barc = new CheckMainKey();
						int bar = barc.barcode();
							
						// Get and store to rs data from database
						atdb.setInt(1, bar);
						ResultSet rs=atdb.executeQuery(); 
							
						while(rs.next()){  
							// Copy data from rs to an arrayList
							prodList.add(new ArrayAtr(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4)));  
						}  
							
					    atdb.close();
					     
					    // Close all the connections    
					    con.close(); 
					        
				
					       
     
					} 
					catch (SQLException e) {
						e.getMessage();
    	
					}
					catch (Exception e) { 
						e.printStackTrace();
        
        
					}
					 
					 //Returns the arrayList with 
					  return prodList;
	}
}	
