package com.ws.beans

import java.util.Date;

import com.ws.beans.StringCondition;
import com.ws.helpers.Constants

class ResolveTestCase {
	// properties
	String id
	String	name
	String request
	
	TestException exception
	
	def actionTaskConditions=[]
	def actionTaskResults=[]
	
	def processId
	//problem id url
	def resulturl
	
	String response
	String errDescription
	boolean success
	boolean completed
	Date startTime
	Date endTime
	
	def timeDiffSecs( ) {
		def result=0d
		if(null != endTime && null != startTime)
			result = ( ( endTime.time - startTime.time ) / 1000.0 ) as double;
			
			
		return result ;
	}
	
	String toFormattedString() {
		StringBuilder sb= new StringBuilder();
		sb.append("-----------------------------------").append(Constants.NEW_LINE)
		
		sb.append("TEST CASE  : $name   ID: $id       ").append(Constants.NEW_LINE)
		
		
		sb.append("Time Taken : "+ timeDiffSecs() +" secs").append(Constants.NEW_LINE)
		
		if(success)
		sb.append("RESULT: SUCCESS                   ").append(Constants.NEW_LINE)
		else{
		sb.append("RESULT: FAILURE                   ").append(Constants.NEW_LINE)
		sb.append("ERROR :"+errDescription+"        ").append(Constants.NEW_LINE)
		}
		
		sb.append("-----------------------------------").append(Constants.NEW_LINE)
		
		return sb.toString();
	}
	String toString() {
		dump()
	}
}
