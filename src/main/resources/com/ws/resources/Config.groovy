package com.ws.resources



//Connection Timeout in milliseconds
connectionTimeout=57000
//Read Timeout in milliseconds
readTimeout=57000

environments {
	localmuthudev {
		resolveEndPointURL="http://www.webservicex.net/airport.asmx"
		
		connectionTimeout=9000
	  }
  localdev {
    resolveEndPointURL="http://192.168.248.174:8080/resolve/webservice/WebserviceListener.WebserviceListenerHttpSoap12Endpoint/"
     proxy {
      host="10.248.44.17"
	  port= 8080
	  user= "hutchison3g\\mnavaneethakrishnan"
	  password= "welcome123"
    }
	connectionTimeout=9000
  }
   development {
    resolveEndPointURL="http://192.168.248.174:8080/resolve/webservice/WebserviceListener.WebserviceListenerHttpSoap12Endpoint/"
     
  }
  sit {
  //TODO Change  
    // resolveEndPointURL="http://192.168.248.174:8080/resolve/webservice/WebserviceListener.WebserviceListenerHttpSoap12Endpoint/"
     
  }
  pilot {
	//TODO Change  
	//resolveEndPointURL="http://192.168.248.174:8080/resolve/webservice/WebserviceListener.WebserviceListenerHttpSoap12Endpoint/"
     
  }
    production {
  //TODO Change 
//   resolveEndPointURL="http://192.168.248.174:8080/resolve/webservice/WebserviceListener.WebserviceListenerHttpSoap12Endpoint/"
     
  }
}

