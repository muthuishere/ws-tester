package com.ws.beans

class TestException {
	
	
	
	String text
	
	boolean hasValidException(String exceptionDescription){
		return (exceptionDescription.contains(text))
		
	}
	String toString() {
		dump()
	}
}
