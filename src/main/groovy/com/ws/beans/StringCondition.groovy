package com.ws.beans

class StringCondition {
	def contains=[]
	def notcontains=[]
	def equalString
	
	boolean verify(def str) throws Exception{
		
		
		if(null == str)
				throw new Exception("Cannot compare with Null ")
		
		//Equals condition only
		if(null != equalString){
			
			if( str.equals(equalString) == false)
				throw new Exception("$str does not equals to $equalString as expected ")
			else
				return true
				
		}
		
		
		contains?.each{ item ->
			
			if(!str.contains(item) )
				throw new Exception("$str does not contains $item as expected ")
			
		}
		
		notcontains?.each{ item ->
			
			if(str.contains(item) )
				throw new Exception("$str contains $item which was not expected ")
			
		}
		
		return true
		
	}
	
	
	String toString() {
		dump()
	}
}
