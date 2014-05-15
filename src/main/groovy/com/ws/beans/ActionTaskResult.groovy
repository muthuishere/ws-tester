package com.ws.beans

class ActionTaskResult {

	def name
	def condition
	def severity
	def summary
	def response
	def details
	def id
	def duration
	def completion
	def lastupdated


	String toString() {
		dump()
	}
	
	def parse(String name,String val){

		switch(name){



			case "ACTIONTASK":
				this.name=val
				break;
			case "CONDITION":
				this.condition=val
				break;
			case "SEVERITY":
				this.severity=val
				break;
			case "SUMMARY":
				this.summary=val
				break;
			case "ACTIONID":
				this.id=val
				break;
			case "DURATION":
				this.duration=val
				break;
			case "COMPLETION":
				this.completion=val
				break;
			case "LASTUPDATED":
				this.lastupdated=val
				break;
		}
	}
}
