# Open311 on Joget

Implementation of Open311 backend using Joget workflow system.

Usage
--------------
    
	Requires Joget V3 and MySQL
			
    1.	'Joget Application' directory contains the Joget application named as APP_version_timestamp.zip. Import this application in the Joget Application Designer and publish the application. Finally launch the published application. This will launch the 'Open311 Request Form' application.
  	# You can create a new application using the 'Make New Request' form which will create the request entry in the database.
	# You can also view all the requests in the database using the 'Open311 Data List' form.

    2.	The open311data.jsp takes the Open311 requests data from database and displays them in the xml specification which can be used by the Open311 Dashboard.
                                                                                           	
	3.	The 'Open311 Sample Requests' is a text file which contains a couple of sample 311 requests.
	

Copyright
---------
Copyright (c) 2011, Code for America. All rights reserved.
See [LICENSE](https://github.com/codeforamerica/congress/blob/master/LICENSE.md) for details.

[![Code for America Tracker](http://stats.codeforamerica.org/codeforamerica/congress.png)](http://stats.codeforamerica.org/projects/congress)