package com.ws.beans

class ServiceRequest {

	String template
	def params=[:]
	String DELIMETER="#"
	
	def getSoapRequest(){
		String request=template
		params.each() { key, value -> 
			request=request.replace(DELIMETER + key +DELIMETER, value)
			
			};
		return request;
		
		
		
	}
}
