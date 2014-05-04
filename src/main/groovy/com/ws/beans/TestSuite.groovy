package com.ws.beans

import java.util.Date;

import com.ws.beans.TestCase;

class TestSuite {

	// properties
	List<TestCase> testCases=new ArrayList<TestCase>();
	Date startTime
	Date endTime
	boolean success
	boolean completed
	String description
	
	String toString() {
		dump()
	}
}
