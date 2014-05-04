package com.ws.beans

import java.util.Date;

import com.ws.beans.TestCondition;

class TestCase {
	// properties
	String id
	String	name
	String request
	TestCondition[] positiveConditions
	TestCondition[] negativeConditions
	String response
	String errDescription
	boolean success
	
	Date startTime
	Date endTime
	
	String toString() {
		dump()
	}
}
