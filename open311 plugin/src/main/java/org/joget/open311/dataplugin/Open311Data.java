package org.joget.open311.dataplugin;

import java.util.Map;

import org.joget.plugin.base.DefaultPlugin;
import org.joget.plugin.base.Plugin;
import org.joget.plugin.base.PluginProperty;
import org.joget.plugin.base.PluginWebSupport;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.text.*;

public class Open311Data extends DefaultPlugin implements Plugin, PluginWebSupport {

	public void webService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        String connectionURL = "jdbc:mysql://localhost:3306/jwdb";
        String username = "root";
        String password = "";
        String tableName = "app_fd_open311_request_t";
        String dat="01/01/1970";
        String tim="00:00:00";
        long l=0l;
    	StringBuilder my = new StringBuilder("");

        if(request.getParameter("time")!=null)
        {

        	 l=Long.valueOf(request.getParameter("time"));
        	 Date d=new Date(l);
        	 Format formatDate = new SimpleDateFormat("MM/dd/yyyy");
        	 Format formatTime = new SimpleDateFormat("HH:mm:ss");
        	 dat = formatDate.format(d);
        	 tim = formatTime.format(d);
        }
        String sqlQuery = "SELECT * FROM  " + tableName + "  where STR_TO_DATE(c_requestDate,'%m/%d/%Y')  > STR_TO_DATE('"+dat+"','%m/%d/%Y') AND STR_TO_DATE(c_requestTime,'%H:%i:%s')  > STR_TO_DATE('"+tim+"','%H:%i:%s') ORDER BY dateCreated DESC";

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try
        {
        	try{
        		Class.forName("com.mysql.jdbc.Driver").newInstance();
        	}
        	catch (Exception e){
        		System.out.println(e);
        	}
        	try{
        		connection = DriverManager.getConnection(connectionURL, username, password);
        	}
        	catch (SQLException e){
        		System.out.println("Unable to create connection to the database " + e);
        	}
        	try{
        		statement = connection.createStatement();
        	}
        	catch (SQLException e){
        		System.out.println("Unable to create the sql statement object " + e);
        	}
        	try{
        		rs = statement.executeQuery(sqlQuery);
        	}
        	catch (SQLException e){
        		System.out.println("Unable to execute the query " + e);
        	}
            while (rs.next()) 
            {

            	my.append("<request>");
            	my.append("\n<service_request_id>");
        			if(!rs.getString("c_service_request_id").equals("")) {
        				my.append("\n\t"+rs.getString("c_service_request_id"));
        		   	}
        			my.append("\n</service_request_id>");
        			
        			
        			my.append("\n<status>");
        			if(!rs.getString("c_status").equals("")) {
        				my.append("\n\t"+rs.getString("c_status"));
        		   	}
        			my.append("\n</status>");
        			
        			my.append("\n<status_notes>");
        			if(!rs.getString("c_status_notes").equals("")) {
        				my.append("\n\t"+rs.getString("c_status_notes"));
        		   	}
        			my.append("\n</status_notes>");
        			
        			my.append("\n<service_name>");
        			if(!rs.getString("c_service_name").equals("")) {
        				my.append("\n\t"+rs.getString("c_service_name"));
        		   	}
        			my.append("\n</service_name>");
        			
        			my.append("\n<service_code>");
        			if(!rs.getString("c_service_code").equals("")) {
        				my.append("\n\t"+rs.getString("c_service_code"));
        		   	}
        			my.append("\n</service_code>");
        			
        			my.append("\n<description>");
        			if(!rs.getString("c_description").equals("")) {
        				my.append("\n\t"+rs.getString("c_description"));
        		   	}
        			my.append("\n</description>");
        			
        			my.append("\n<agency_responsible>");
        			if(!rs.getString("c_agency_responsible").equals("")) {
        				my.append("\n\t"+rs.getString("c_agency_responsible"));
        		   	}
        			my.append("\n</agency_responsible>");
        			
        			my.append("\n<service_notice>");
        			if(!rs.getString("c_service_notice").equals("")) {
        				my.append("\n\t"+rs.getString("c_service_notice"));
        		   	}
        			my.append("\n</service_notice>");
        			
        			my.append("\n<requested_datetime>");
        			if(!rs.getString("c_requestDate").equals("")) {
        				my.append("\n\t"+rs.getString("c_requestDate") +" "+ rs.getString("c_requestTime"));
        		   	}
        			my.append("\n</requested_datetime>");
        			
        			my.append("\n<updated_datetime>");
        			if(!rs.getString("c_updatedDate").equals("")) {
        				my.append("\n\t"+rs.getString("c_updatedDate") +" "+ rs.getString("c_updatedTime"));
        		   	}
        			my.append("\n</updated_datetime>");
        			
        			my.append("\n<expected_datetime>");
        			if(!rs.getString("c_expectedDate").equals("")) {
        				my.append("\n\t"+rs.getString("c_expectedDate") +" "+ rs.getString("c_expectedTime"));
        		   	}
        			my.append("\n</expected_datetime>");
        			
        			my.append("\n<address>");
        			if(!rs.getString("c_address").equals("")) {
        				my.append("\n\t"+rs.getString("c_address"));
        		   	}
        			my.append("\n</address>");
        		
        			my.append("\n<address_id>");
        			if(!rs.getString("c_address_id").equals("")) {
        				my.append("\n\t"+rs.getString("c_address_id"));
        		   	}
        			my.append("\n</address_id>");
        		
        			my.append("\n<zipcode>");
        			if(!rs.getString("c_zipcode").equals("")) {
        				my.append("\n\t"+rs.getString("c_zipcode"));
        		   	}
        			my.append("\n</zipcode>");
        			
        			my.append("\n<lat>");
        			if(!rs.getString("c_lat").equals("")) {
        				my.append("\n\t"+rs.getString("c_lat"));
        		   	}
        			my.append("\n</lat>");
        		
        			my.append("\n<long>");
        			if(!rs.getString("c_long").equals("")) {
        				my.append("\n\t"+rs.getString("c_long"));
        		   	}
        			my.append("\n</long>");
        		
        			my.append("\n</request>");
        	            	
            
            }
        }
        catch (Exception e)
        {
        	System.out.println(e);
        }
        finally {

          if(rs != null) {
              try {
            	  rs.close();
              } 
        	  catch (SQLException e) {
        		  System.out.println("Unable to close the resultset object " + e);
        	 }
        	}
           
           if(statement != null) {
              try {
            	  statement.close();
              } 
        	  catch (SQLException e) {
        		  System.out.println("Unable to close the statement object " + e);
              }
           }
           
           if(connection != null) {
              try {
            	  connection.close();
              } 
        	  catch (SQLException e) {
        		  System.out.println("Unable to close the connection object " + e);
              }
           }      
           
        }
           		
        //Accessing the response object and writing back
        response.getWriter().write(my.toString());
    }
        
        
        
        
	public Object execute(Map arg0) {
		
		return null;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "Open311 Data Plugin";
	}

	public PluginProperty[] getPluginProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getVersion() {
		// TODO Auto-generated method stub
		return "6";
	}
}
