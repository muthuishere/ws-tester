package com.ws.application

import com.ws.beans.TestCase;
import com.ws.beans.TestCondition
import com.ws.beans.TestSuite
import com.ws.helpers.Constants
import java.util.Date;
import wslite.soap.*
class TestWebservice {

	def getConditions(def testConditions,boolean conditionPositive){


		def conditions=[];



		testConditions.param.each{

			conditions.add(
					new TestCondition(

					condition:trimSOAPreq(it.text()),
					positive:conditionPositive
					)
					)
		}


		return conditions;
	}

	def executeTestCase(def activeConfig,TestCase testCase) {

		//
		testCase.startTime=new Date()
		String failDescription=""
		boolean flgresultPositive=true;
		try{




			def client = new SOAPClient(activeConfig.wsEndPointURL);

			if(activeConfig.proxy){

				def proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(activeConfig.proxy.host, activeConfig.proxy.port))

				if(activeConfig.proxy.user){
					Authenticator.setDefault(new Authenticator() {
								protected PasswordAuthentication getPasswordAuthentication() {
									return new PasswordAuthentication(activeConfig.proxy.user,activeConfig.proxy.password.toCharArray())
								}
							})
				}

				client.httpClient.proxy = proxy
			}

			//SOAPVersion.V1_2,

			def response =client.send(
					connectTimeout:activeConfig.connectionTimeout,
					readTimeout:activeConfig.readTimeout,
					testCase.request
					)

			testCase.response=response.text;
			

			testCase.positiveConditions.eachWithIndex() { positiveCondition, i ->

				boolean flgVerify=verify(response.text,positiveCondition)
				flgresultPositive=flgresultPositive & flgVerify
				
				if(!flgVerify)
					failDescription=failDescription + " Verification failed for Condition " + positiveCondition.condition + Constants.NEW_LINE
				
					
				
				

			};


			testCase.negativeConditions.eachWithIndex() { negativeCondition, i ->

				boolean flgVerify=verify(response.text,negativeCondition)
				
				
				flgresultPositive=flgresultPositive & verify(response.text,negativeCondition)
				
				if(!flgVerify)
				failDescription=failDescription + " Negative Verification failed for Condition " + negativeCondition.condition + Constants.NEW_LINE
			
				

			};


		}catch(Throwable t){

		println("Error"+t)
			t.printStackTrace();
			testCase.errDescription=failDescription + t.getMessage();
			flgresultPositive=false;

		}finally{
		
			testCase.errDescription=failDescription;
			testCase.success=flgresultPositive;
			testCase.endTime=new Date()
		}

		return testCase
	}

	boolean verify(String response,TestCondition testCondition){
		println("==")
		print("verifying response contains condition" + testCondition.toString() )
		print("response.contains(testCondition.condition) " + response.contains(testCondition.condition)  + "testCondition.positive" + testCondition.positive)
		println("==")
		
		if(response.contains(testCondition.condition) && testCondition.positive)
			return true

			if(response.contains(testCondition.condition) ==false && testCondition.positive==false)
				return true
				
		return false;


	}

	def trimSOAPreq(String txt){

		return txt.trim()
	}



	public TestSuite startTest(def activeConfig,def testCaseFile) {










		def lstResults=new ArrayList();

		//	println(activeConfig.dump())

		boolean flgSuccess=true

		//Initialize Test suite assuming result would fail
		def suite=new TestSuite(startTime:new Date(),success:false);


		try{




			//Parse xml content

			//Set Array of positive & negative test

			//create object of request , response , Start time , End time , Success Failure

			//Check file Exists

			def testCaseFilePointer = new File(testCaseFile)
			println("File $testCaseFile exists"+ testCaseFilePointer.exists())



			if(testCaseFilePointer.exists() == false){

				throw new Exception("$testCaseFile does not exists");

			}


			//Parse xml and set it in Testcase object
			def testcaseNodes = new XmlSlurper().parse(testCaseFilePointer)

			def initTestCases=[]

			int i=0;
			//	println("checking for"+ testcaseNodes.dump())
			testcaseNodes.testcase.each{

				i++;
				//	println("Iterating for"+ it.text())

				initTestCases.push(
						new TestCase(
						id:String.format('%03d',i),
						name:it.name.text(),
						request:trimSOAPreq(it.request[0].content.text()),
						positiveConditions:getConditions(it.verify,true),
						negativeConditions:getConditions(it.verifynegative,false)

						)
						)


			}


			//Testcases completed check test cases are parsed fine

			for ( testCase in initTestCases ) {
				println("Executing testscase for"+ testCase.toString())
				TestCase tc=executeTestCase(activeConfig,testCase)
				flgSuccess=flgSuccess & tc.success;
				suite.testCases.push(tc)
			}

			suite.success=flgSuccess
			suite.completed=true;


		}catch(Throwable t){
		println("Error"+t)
			t.printStackTrace()

			suite.description=t.getMessage();
			suite.success=false;
			suite.completed=false;
		}

		//	println(suite.toString());
		suite.endTime=new Date();
		return suite;

	}

}
