package com.ws.beans

import java.util.Date;

import com.ws.beans.TestCondition;
import com.ws.helpers.Constants

class TestCase {
	// properties
	String id
	String	name
	String request
	TestCondition[] positiveConditions
	TestCondition[] negativeConditions
	TestException exception
	
	
	String response
	String errDescription
	boolean success
	
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
