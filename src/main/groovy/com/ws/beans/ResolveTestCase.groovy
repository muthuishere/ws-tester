package com.ws.beans

import java.util.Date;

import com.ws.beans.StringCondition;
import com.ws.helpers.Constants
import com.ws.helpers.ResultVerifier

class ResolveTestCase {
	// properties
	String id
	String	name
	String request
	String runbookName
	
	TestException exception
	
	def actionTaskConditions=[]
	def actionTaskResults=[]
	
	def actionTaskVerificationResponses=[]
	def processId
	//problem id url
	def resulturl
	
	String response
	String errDescription
	
	boolean completed
	Date startTime
	Date endTime
	def getStatus(){
		
		if(getSuccess())
			return "Success"
		else
			return "Failure"
		
		
	}
	def getFormattedStatus(){
		
		if(getSuccess())
			return "success"
		else
			return "failure"
		
		
	}
	
	def getDuration(){
		
		
		return ResultVerifier.getTimeDiffInSecs(startTime,endTime) + " secs"
		
	}
	def getSuccess(){
		
		if(null != errDescription)
			return false
		
		boolean flgSuccess=true
		actionTaskVerificationResponses.each{actionTaskVerificationResponse ->
			
			flgSuccess=flgSuccess & actionTaskVerificationResponse.success
			
		}
		
		return flgSuccess
	}
	
	def getFailuremsg(){
		
		def msg=""
		
		if(null != errDescription)
			msg=errDescription
		
		
		actionTaskVerificationResponses.each{actionTaskVerificationResponse ->
			
			if(!actionTaskVerificationResponse.success)
				msg=msg + (null == actionTaskVerificationResponse.description?"":actionTaskVerificationResponse.description)
			
		}
		
		return msg
	}

	
	
	String toFormattedString() {
		StringBuilder sb= new StringBuilder();
		sb.append("-----------------------------------").append(Constants.NEW_LINE)
		
		sb.append("TEST CASE  : $name   ID: $id       ").append(Constants.NEW_LINE)
		
		
		sb.append("Time Taken : "+ getDuration() +"").append(Constants.NEW_LINE)
		
		if(getSuccess())
		sb.append("RESULT: SUCCESS                   ").append(Constants.NEW_LINE)
		else{
		sb.append("RESULT: FAILURE                   ").append(Constants.NEW_LINE)
		sb.append("ERROR :"+getFailuremsg()+"        ").append(Constants.NEW_LINE)
		}
		
		
		if(null != actionTaskVerificationResponses && actionTaskVerificationResponses.size() >0){
			sb.append("ActionTasks").append(Constants.NEW_LINE)
			sb.append("Name   |  Condition   | Severity | Completed | Status  | Description").append(Constants.NEW_LINE)
			actionTaskVerificationResponses.each{actionTaskVerificationResponse ->
			
				
				
				sb.append(actionTaskVerificationResponse.actionTaskCondition.name + "   |")
				sb.append(actionTaskVerificationResponse.actionTaskResult.condition + "   |")
				sb.append(actionTaskVerificationResponse.actionTaskResult.severity + "   |")
				sb.append(actionTaskVerificationResponse.actionTaskResult.completion + "   |")
				
				if(actionTaskVerificationResponse.success)
					sb.append("Success   |   ")
				else{
					sb.append("Failure   |" + actionTaskVerificationResponse.description)
					
				}
				sb.append(Constants.NEW_LINE)
				sb.append("-----------------------------------").append(Constants.NEW_LINE)
				
				
			}
		}
		
		
		sb.append("-----------------------------------").append(Constants.NEW_LINE)
		
		return sb.toString();
	}
	String toString() {
		dump()
	}
}
