package com.ws.helpers

import com.ws.beans.ActionTaskCondition
import com.ws.beans.ActionTaskResult

public class ResultVerifier {

	public static double getTimeDiffInSecs(Date startTime,Date  endTime) {
		def result=0d
		if(null != endTime && null != startTime)
			result = ( ( endTime.time - startTime.time ) / 1000.0 ) as double;
			
			
		return result ;
	}
	
	public static def verify(ActionTaskCondition condition,ActionTaskResult result) throws Exception{
		
		
		if(null == result )
			throw new Exception("Verfication Failed: ActionTask [" + condition.name + "] not found in results")
		
		println("======verify started=========")
		println(condition.toString())
		println(result.toString())
		println("======verify endeded=======")
		
		if(null != condition.completed){			
			//Check for Completed			
			if(result.completion.toString().equalsIgnoreCase(condition.completed) == false)
				throw new Exception("Verfication Failed: ActionTask Completed EXPECTED: " + condition.completed + " ACTUAL: " +result.completion.toString())
			
		}
		
		if(null != condition.severity){
			//Check for Completed
			if(result.severity.toString().equalsIgnoreCase(condition.severity) == false)
				throw new Exception("Verfication Failed: ActionTask Severity EXPECTED: " + condition.severity + " ACTUAL: " +result.severity.toString())
			
		}
		
		if(null != condition.summary)
			condition.summary.verify(result?.summary?.toString())
			
		if(null != condition.details){
			
			if(null != result.details)
				condition.details.verify(result?.details?.toString())
			else
				throw new Exception("Verfication Failed: unable to verify details of Actiontask, as the details webservice returned empty !!!")
			}
			

		return true	

		
	} 
}
