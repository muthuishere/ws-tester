package com.ws.beans

class ActionTaskVerificationResponse {

	ActionTaskCondition actionTaskCondition
	ActionTaskResult actionTaskResult	
	def description
	def success
	def NOT_APPLICABLE="N/A"
	
	def getName(){
		
		return actionTaskCondition.name
	}
	
	
	
	def getFormattedStatus(){
		
		if(success)
			return "success"
		else
			return "failure"
		
		
	}
	
	def getResult(){
		
		if(success)
			return "Success"
		else
			return "Failure"
	}
	def getActualCompletion(){
		
		return actionTaskResult.completion
	}
	
	def getActualCondition(){
		
		return actionTaskResult.condition
	}
	
	def getActualSeverity(){
		
		return actionTaskResult.severity
	}
	
	def getActualSummary(){
		
		return actionTaskResult.summary
	}
	
	def getActualDetails(){
		
		
		return (null == actionTaskResult.details)?"Not Found":actionTaskResult.details
		
	}
	
	def getComments(){
		
		
		return (null == description)?"":description
		
	}
	def getExpectedCompletion(){
		
		return (null == actionTaskCondition.completed)?NOT_APPLICABLE:actionTaskCondition.completed
		
	}
	
	def getExpectedCondition(){
		
		return (null == actionTaskCondition.condition)?NOT_APPLICABLE:actionTaskCondition.condition
	}
	
	def getExpectedSeverity(){
		
		
		return (null == actionTaskCondition.severity)?NOT_APPLICABLE:actionTaskCondition.severity
		
	}
	
	def getExpectedSummary(){
		
		return (null == actionTaskCondition.summary)?NOT_APPLICABLE:actionTaskCondition?.summary?.toFormatted()
		
	}
	
	def getExpectedDetails(){
		
		return (null == actionTaskCondition.details)?NOT_APPLICABLE:actionTaskCondition?.details?.toFormatted()
		
		
	}
	
	
}
