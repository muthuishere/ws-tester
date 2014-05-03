package com.ws.beans

import java.util.Date;

import com.ws.beans.TestCondition;

class Testcase {
	// properties
	String id
	String	name
	String request
	TestCondition[] positiveConditions
	TestCondition[] negativeConditions
	String response
	String result
	Date startTime
	Date endTime
}
