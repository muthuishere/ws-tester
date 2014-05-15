package com.ws.beans

class ActionTaskCondition {
	String name
	def condition
	def severity
	def completed
	StringCondition summary
	StringCondition details
	
	String toString() {
		dump()
	}
}
