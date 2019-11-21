package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class testconnections {
												
	//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"		
    String dbUrl = "jdbc:mysql://localhost/ems";					

	//Database Username		
	String username = "root";	
    
	//Database Password		
	String password = "root";			
	String Status;
	String trasid;

	//Query to Execute		
	
	String query1="select command from ocpp_logs where Id=(SELECT max(id) from ocpp_logs);";
	String query2="select session_id,start_meter_value,end_meter_value from evse_session where Id=(SELECT max(id) from evse_session);";
	@Test
	public String testevsestatus(String evseid) throws SQLException, ClassNotFoundException {	
	    //Load mysql jdbc driver	
		String query = "select  availability_state  from evse where serial_number="+evseid;;	
	    Class.forName("com.mysql.jdbc.Driver");			

		//Create Connection to DB		
	Connection con = DriverManager.getConnection(dbUrl,username,password);

		//Create Statement Object		
   Statement stmt = con.createStatement();					

		// Execute the SQL Query. Store results in ResultSet		
		ResultSet rs= stmt.executeQuery(query);	
		//ResultSet rs1= stmt.executeQuery(query1);	

		// While Loop to iterate through all data and print results		
	while (rs.next()){
        		//String EVSE = rs.getString(1);								        
                Status = rs.getString(1);						                               
              System. out.println("Status  "+Status);		
        }	
	//while (rs1.next()){
		//String Command = rs1.getString(1);								        
        //String Status = rs.getString(2);						                               
       // System. out.println("Command  "+ Command);		
	return Status;

		 // closing DB Connection		
		//con.close();			
}
	
	@Test
	public String transid() throws SQLException, ClassNotFoundException {	
	    //Load mysql jdbc driver	
		String query = "select  MAX(session_id) from evse_session";	
	    Class.forName("com.mysql.jdbc.Driver");		
	    

		//Create Connection to DB		
	Connection con = DriverManager.getConnection(dbUrl,username,password);

		//Create Statement Object		
   Statement stmt = con.createStatement();					

		// Execute the SQL Query. Store results in ResultSet		
		ResultSet rs= stmt.executeQuery(query);	
		//ResultSet rs1= stmt.executeQuery(query1);	

		// While Loop to iterate through all data and print results		
	while (rs.next()){
        		//String EVSE = rs.getString(1);								        
                 trasid = rs.getString(1);						                               
              System. out.println("trasid  "+trasid);		
        }	
	//while (rs1.next()){
		//String Command = rs1.getString(1);								        
        //String Status = rs.getString(2);						                               
       // System. out.println("Command  "+ Command);		
	return trasid;

		 // closing DB Connection		
		//con.close();			
}
	@Test
	public void testocpplogs() throws SQLException, ClassNotFoundException {	
	    //Load mysql jdbc driver	
		

	    Class.forName("com.mysql.jdbc.Driver");			

		//Create Connection to DB		
	Connection con1 = DriverManager.getConnection(dbUrl,username,password);

		//Create Statement Object		
   Statement stmt1 = con1.createStatement();					

		// Execute the SQL Query. Store results in ResultSet		
		//ResultSet rs= stmt.executeQuery(query1);	
		ResultSet rs1= stmt1.executeQuery(query1);	

		// While Loop to iterate through all data and print results		
	
	while (rs1.next()){
		String Command = rs1.getString(1);								        
       // String Status = rs.getString(2);						                               
       System. out.println("Command  "+ Command);		
	}
		 // closing DB Connection		
		con1.close();			


}
	@Test
	public void sessionlogs() throws SQLException, ClassNotFoundException {	
	    //Load mysql jdbc driver		
	    Class.forName("com.mysql.jdbc.Driver");			

		//Create Connection to DB		
	Connection con2 = DriverManager.getConnection(dbUrl,username,password);

		//Create Statement Object		
   Statement stmt1 = con2.createStatement();					

		// Execute the SQL Query. Store results in ResultSet		
		//ResultSet rs= stmt.executeQuery(query1);	
		ResultSet rs2= stmt1.executeQuery(query2);	

		// While Loop to iterate through all data and print results		
	
	while (rs2.next()){
		String sessionid = rs2.getString(1);								        
        String startmetervalue = rs2.getString(2);	
        String stopmetervalue = rs2.getString(3);
       System. out.println("Session ID  "+ sessionid + "   Start meter value  "+ startmetervalue+ "   End Meter Value  "+stopmetervalue );		
	}
		 // closing DB Connection		
		con2.close();			


}
}

