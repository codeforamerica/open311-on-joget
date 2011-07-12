<?xml version="1.0" encoding="UTF-8"?>
 <%@ page contentType="text/xml" %>
 <%@ page import="java.io.*" %>
 <%@ page import="java.sql.*" %>
 <%@ page import="java.text.*" %>

<%
String connectionURL = "jdbc:mysql://localhost:3306/v3wflowdb";
String username = "root";
String password = "";
String tableName = "apps_formdata_open311_request_t";
String dat="01/01/1970";
String tim="00:00:00";
long l=0l;
boolean b_time=false;
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
%>

<%
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
try
{
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	}
	catch (Exception e){
		out.println(e);
	}
	try{
		connection = DriverManager.getConnection(connectionURL, username, password);
	}
	catch (SQLException e){
		out.println("Unable to create connection to the database " + e);
	}
	try{
		statement = connection.createStatement();
	}
	catch (SQLException e){
		out.println("Unable to create the sql statement object " + e);
	}
	try{
		rs = statement.executeQuery(sqlQuery);
	}
	catch (SQLException e){
		out.println("Unable to execute the query " + e);
	}

%>
<requests>
<%
	
     while (rs.next()) 
     {
%>
	<request>
		<service_request_id>
			<%if(rs.getString("c_service_request_id")!=null) {%>
	       <%= rs.getString("c_service_request_id")%>
		   <%	}%>
		</service_request_id>
		<status>
		<%if(rs.getString("c_status")!=null) {%>
	       <%= rs.getString("c_status") %>
		<%	}%>
		</status>
		<status_notes>
		<%if(rs.getString("c_status_notes")!=null) {%>
			<%= rs.getString("c_status_notes") %>
		<%	}%>
		</status_notes>
	    <service_name>
		<%if(rs.getString("c_service_name")!=null) {%>
	       <%= rs.getString("c_service_name")%>
		<%	}%>
		</service_name>
		<service_code>
		<%if(rs.getString("c_service_code")!=null) {%>
	       <%= rs.getString("c_service_code") %>
		<%	}%>
		</service_code>
		<description>
		<%if(rs.getString("c_description")!=null) {%>
	       <%= rs.getString("c_description") %>
		<%	}%>
		</description>
		<agency_responsible>
		<%if(rs.getString("c_agency_responsible")!=null) {%>
	       <%= rs.getString("c_agency_responsible")%>
		<%	}%>
		</agency_responsible>
		<service_notice>
		<%if(rs.getString("c_service_notice")!=null) {%>
	       <%= rs.getString("c_service_notice") %>
		<%	}%>
		</service_notice>
		<requested_datetime>
		<%if(rs.getString("c_requestDate")!=null) {%>
	       <%= rs.getString("c_requestDate") %> <%= rs.getString("c_requestTime") %>
		<%	}%>
		</requested_datetime>
		<updated_datetime>
		<%if(rs.getString("c_updatedDate")!=null) {%>
	       <%= rs.getString("c_updatedDate") %> <%= rs.getString("c_updatedTime") %>
		<%	}%>
		</updated_datetime>
		<expected_datetime>
		<%if(rs.getString("c_expectedDate")!=null) {%>
	       <%= rs.getString("c_expectedDate") %> <%= rs.getString("c_expectedTime") %>
		<%	}%>
		</expected_datetime>
		<address>
		<%if(rs.getString("c_address")!=null) {%>
	       <%= rs.getString("c_address") %>
		<%	}%>
		</address>	
		<address_id>
		<%if(rs.getString("c_address_id")!=null) {%>
	       <%= rs.getString("c_address_id") %>
		<%	}%>
		</address_id>
		<zipcode>
		<%if(rs.getString("c_zipcode")!=null) {%>
	       <%= rs.getString("c_zipcode")%>
		<%	}%>
		</zipcode>
		<lat>
		<%if(rs.getString("c_lat")!=null) {%>
	       <%= rs.getString("c_lat") %>
		<%	}%>
		</lat>
		<long>
		<%if(rs.getString("c_long")!=null) {%>
	       <%= rs.getString("c_long") %>
		<%	}%>
		</long>
	</request>
<%
     }

}
catch (Exception e)
{
	out.println(e);
}
finally {

  if(rs != null) {
      try {
         rs.close();
      } 
	  catch (SQLException e) {
		 out.println("Unable to close the resultset object " + e);
	 }
	}
   
   if(statement != null) {
      try {
         statement.close();
      } 
	  catch (SQLException e) {
		 out.println("Unable to close the statement object " + e);
      }
   }
   
   if(connection != null) {
      try {
         connection.close();
      } 
	  catch (SQLException e) {
		out.println("Unable to close the connection object " + e);
      }
   }      
   
}
%>
</requests>