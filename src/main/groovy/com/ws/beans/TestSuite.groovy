package com.ws.beans

import java.util.Date;

import com.ws.beans.TestCase;
import com.ws.helpers.Constants

class TestSuite {

	// properties
	List<TestCase> testCases=new ArrayList<TestCase>();
	Date startTime
	Date endTime
	boolean success
	boolean completed
	String description
	
	
	def timeDiffSecs( ) {
		def result=0d
		if(null != endTime && null != startTime)
			result = ( ( endTime.time - startTime.time ) / 1000.0 ) as double;
			
			
		return result ;
	}
	
	String toFormattedString() {
		StringBuilder sb= new StringBuilder();
		sb.append("-----------------------------------").append(Constants.NEW_LINE)
		

		sb.append("----------Test Suite Results--------").append(Constants.NEW_LINE)
		sb.append("Time Taken : "+ timeDiffSecs() +" secs").append(Constants.NEW_LINE)
		
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
		for(TestCase testCase:this.testCases){
			
			sb.append(testCase.toFormattedString()).append(Constants.NEW_LINE)
			
		}
		sb.append("-------End Testcase Results---------").append(Constants.NEW_LINE)
		sb.append("-----------------------------------").append(Constants.NEW_LINE)
		
		return sb.toString();
	}
	
	String toString() {
		dump()
	}
}
