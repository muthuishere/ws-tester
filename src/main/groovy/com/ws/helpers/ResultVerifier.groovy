package com.ws.helpers

import com.ws.beans.ActionTaskCondition
import com.ws.beans.ActionTaskResult

public class ResultVerifier {

	public static def verify(ActionTaskCondition condition,ActionTaskResult result) throws Exception{
		
		
		if(null == result )
			throw new Exception("Verfication Failed: ActionTask " + condition.name + " not found in results")
		
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
			
		if(null != condition.details)
			condition.details.verify(result?.details?.toString())

		return true	

		
	} 
}
