



//Connection Timeout in milliseconds
connectionTimeout=240000
//Read Timeout in milliseconds
readTimeout=240000
resolveuser="mnavaneethakrishnan"
resolvepwd="Superstar1*"

environments {
  current {
	//wsEndPointURL="http://www.webservicex.net/airport.asmx"
    wsEndPointURL="http://192.168.248.174:8080/resolve/webservice/WebserviceListener.WebserviceListenerHttpSoap12Endpoint/"
     proxy {
      host="10.248.44.17"
	  port= 8080
	  user= "hutchison3g\\mnavaneethakrishnan"
	  password= "welcome123"
    }
	
	resolveuser="mnavaneethakrishnan"
	resolvepwd="Superstar1*"
	exportpath="C:\\muthu\\resolve\\test\\ws-tester\\resources\\export"
  }
  
}

