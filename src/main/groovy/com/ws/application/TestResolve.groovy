package com.ws.application



import java.util.Date;

import com.ws.beans.TestCase
import com.ws.beans.TestCondition;
import com.ws.beans.TestSuite

import wslite.soap.*



public class TestResolve {

	static def getInfo(testargs) {

		def cli = new CliBuilder()
		cli.with {
			h(longOpt: 'help', 'Help - Usage Information')
			e(longOpt: 'environment', 'Test Environment', type: String,args: 1,  required: true)
			f(longOpt: 'testcase', 'Testcase File', type: String, args: 1, required: true)
		}

		def options = cli.parse(testargs)
		if (!options) {
			return
		}
		// Show usage text when -h or --help option is used.
		if (options.h) {
			cli.usage()
			return
		}
		//println "The location is ${options.e}, ${options.f}, ."


		def optionsmap = ["testcasefile": options?.f, "environment": options?.e]

		//println(optionsmap.dump())
		return  optionsmap

	}


	static main(args) {


		//	String[] tars="-e localdev -f  'C:\\muthu\\gitworkspace\\ws-tester\\resources\\testcase.xml'";
		TestSuite result= 	startTest( getInfo(args))

		println(result.toString());

	}


	static def getConditions(def testConditions,boolean conditionPositive){


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


	static def executeTestCase(def activeConfig,TestCase testCase) {

		//
		testCase.startTime=new Date()

		boolean flgresultPositive=true;
		try{




			def client = new SOAPClient(activeConfig.resolveEndPointURL);

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


				flgresultPositive=flgresultPositive & verify(response.text,positiveCondition)

			};


			testCase.negativeConditions.eachWithIndex() { negativeCondition, i ->


				flgresultPositive=flgresultPositive & verify(response.text,negativeCondition)

			};


		}catch(Throwable t){

			testCase.errDescription=t.getMessage();
			flgresultPositive=false;

		}finally{

			testCase.success=flgresultPositive;
			testCase.endTime=new Date()
		}

		return testCase
	}

	static boolean verify(String response,TestCondition testCondition){

		println("verifying response contains condition" + testCondition.toString() )
		if(response.contains(testCondition.condition) && testCondition.positive)
			return true

		return false;


	}
	
	static def trimSOAPreq(String txt){
		
		return txt.trim()
	}
	static def startTest(options) {





		def environment = options["environment"] ?: "localdev"

		def testCaseFile = options["testcasefile"]



		println(environment + "--" + testCaseFile)



		def activeConfig = new ConfigSlurper(environment).parse(com.ws.resources.Config)

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
			println("$testCaseFile is exists"+ testCaseFilePointer.exists())



			if(testCaseFilePointer.exists() == false){
				
				throw new Exception("$testCaseFile does not exists");
				
			}


			//Parse xml and set it in Testcase object
			def testcaseNodes = new XmlSlurper().parse(testCaseFilePointer)

			/*
			 * 	String id
			 String	name
			 String request
			 TestCondition[] positiveConditions
			 TestCondition[] negativeConditions
			 String response
			 String result
			 Date startTime
			 Date endTime
			 * 
			 */

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
						negativeConditions:getConditions(it.verify,false)

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