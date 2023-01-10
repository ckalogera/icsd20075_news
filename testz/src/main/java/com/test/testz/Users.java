
package com.test.testz;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.sql.*;

/** Example resource class hosted at the URI path "/news"
 */
@Path("/users")
public class Users {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {
    	String results = "";
    	 try
    	    {
    	      // create our mysql database connection
    	      String myDriver = "com.mysql.cj.jdbc.Driver";//org.gjt.mm.mysql.Driver";
    	      String myUrl = "jdbc:mysql://localhost:3306/news";
    	      Class.forName(myDriver);
    	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
    	      
    	      // our SQL SELECT query. 
    	      // if you only need a few columns, specify them by name instead of using "*"
    	      String query = "SELECT * FROM users";

    	      // create the java statement
    	      Statement st = conn.createStatement();
    	      
    	      // execute the query, and get a java resultset
    	      ResultSet rs = st.executeQuery(query);
    	      
    	      
    	      // iterate through the java resultset
    	      while (rs.next())
    	      {
    	        int id = rs.getInt("id");
    	        String name = rs.getString("name");
    	        String surname = rs.getString("surname");
    	        String email = rs.getString("email");
    	        String phone = rs.getString("phone");
    	        
    	        results += id + ": " + name + ", "+surname + ", "+email + ", "+phone +"\n";
    	       
    	      }
    	      st.close();
    	    }
    	    catch (Exception e)
    	    {
    	      results = e.getMessage();
    	      System.err.println("Got an exception! ");
    	      System.err.println(e.getMessage());
    	    }
    	 
        return "news: " + results;
    }
}
