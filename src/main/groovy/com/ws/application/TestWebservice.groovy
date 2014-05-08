package com.ws.application

import com.ws.beans.TestCase;
import com.ws.beans.TestCondition
import com.ws.beans.TestException
import com.ws.beans.TestSuite
import com.ws.helpers.Constants
import java.util.Date;
import wslite.soap.*
class TestWebservice {

	def activeConfig
	def getConditions(def testConditions,boolean conditionPositive){


		def conditions=[];



		if(null != testConditions){
			testConditions.param.each{

				conditions.add(
						new TestCondition(

						condition:trimSOAPreq(it.text()),
						positive:conditionPositive
						)
						)
			}
		}
		return conditions;
	}

	def getException(def exceptionNode){


		TestException exception=null;

		if(null != exceptionNode){
			exception=new TestException(
					text:trimSOAPreq(exceptionNode?.param?.text())
					);
		}
		return exception;
	}

	def getSoapResponse(def testCase) throws Exception{


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


		return response.text;


	}
	def executeTestCase(TestCase testCase) {

		//
		testCase.startTime=new Date()
		String failDescription=""
		boolean flgresultPositive=true;
		def flgsoapException=null;
		def soapresponse =""
		try{


			soapresponse=getSoapResponse(testCase)



		}catch(Throwable t){


			flgsoapException=t.getMessage()

			println ("==========" + t.getMessage() +"==========="	)
			t.printStackTrace();
			//Fix for Error not Appearing

			if(null != testCase?.exception){

				if( testCase.exception.hasValidException(flgsoapException) == false ){
					failDescription=failDescription + " Invalid Exception Message" +t.getMessage();
					flgresultPositive=false;
				}else{
					soapresponse=" Exception Thrown as expected " +t.getMessage()
				}



			}else{
				failDescription=failDescription + t.getMessage();
				flgresultPositive=false;
			}


		}


		if( null != testCase.exception){
			testCase.positiveConditions.eachWithIndex() { positiveCondition, i ->

				boolean flgVerify=verify(soapresponse,positiveCondition)
				flgresultPositive=flgresultPositive & flgVerify

				if(!flgVerify)
					failDescription=failDescription + " Verification failed for Condition " + positiveCondition.condition + Constants.NEW_LINE





			};


			testCase.negativeConditions.eachWithIndex() { negativeCondition, i ->

				boolean flgVerify=verify(soapresponse,negativeCondition)


				flgresultPositive=flgresultPositive & flgVerify

				if(!flgVerify)
					failDescription=failDescription + " Negative Verification failed for Condition " + negativeCondition.condition + Constants.NEW_LINE



			};


		}
		testCase.response=soapresponse;

		testCase.errDescription=failDescription;
		testCase.success=flgresultPositive;
		testCase.endTime=new Date()


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

		return txt?.trim()
	}



	public TestSuite startTest(def testCaseFile) {










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
						negativeConditions:getConditions(it.verifynegative,false),
						exception:getException(it.verifyexception)
						)
						)


			}


			//Testcases completed check test cases are parsed fine

			for ( testCase in initTestCases ) {
				println("Executing testscase for"+ testCase.toString())
				TestCase tc=executeTestCase(testCase)
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
