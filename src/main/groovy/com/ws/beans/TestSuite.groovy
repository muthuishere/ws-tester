package com.ws.beans

import java.util.Date;

import com.ws.beans.ResolveTestCase;
import com.ws.helpers.Constants
import com.ws.helpers.ResultVerifier

class TestSuite {

	// properties
	def  testCases=new ArrayList<ResolveTestCase>();
	Date startTime
	Date endTime
	boolean success
	boolean completed
	String description
	
	
	def getStatus(){
		
		if(success)
			return "Success"
		else
			return "Failure"
		
		
	}
	def getFormattedStatus(){
		
		if(success)
			return "success"
		else
			return "failure"
		
		
	}
	
	
	def getTestCaseCount(){
		
		return testCases.size();
		
	}

	
	
	String toFormattedString() {
		StringBuilder sb= new StringBuilder();
		sb.append("-----------------------------------").append(Constants.NEW_LINE)
		

		sb.append("----------Test Suite Results--------").append(Constants.NEW_LINE)
		sb.append("Time Taken : "+ ResultVerifier.getTimeDiffInSecs(startTime,endTime)  +" secs").append(Constants.NEW_LINE)
		
		if(success)
		sb.append("RESULT: SUCCESS                   ").append(Constants.NEW_LINE)
		else{
			
			if(completed){
				sb.append("RESULT: FAILURE                   ").append(Constants.NEW_LINE)
			
				}
			else{
				sb.append("RESULT: FAILURE-INCOMPLETE                   ").append(Constants.NEW_LINE)
				sb.append("ERROR : $description           ").append(Constants.NEW_LINE)
			}
			
			
			
		} 
		sb.append("-----------------------------------").append(Constants.NEW_LINE)
		sb.append("-------Testcase Results------------").append(Constants.NEW_LINE)
		for(ResolveTestCase testCase:this.testCases){
			
			sb.append(testCase.toFormattedString()).append(Constants.NEW_LINE)
			
		}
		sb.append("-------End Testcase Results---------").append(Constants.NEW_LINE)
		sb.append("-----------------------------------").append(Constants.NEW_LINE)
		
		sb.append("-------End Test Suite Results--------").append(Constants.NEW_LINE)
		return sb.toString();
	}
	
	String toString() {
		dump()
	}
}
