package com.ws.application

import java.util.ArrayList;




import com.ws.beans.ServiceRequest
import com.ws.beans.ResolveTestCase
import com.ws.beans.ActionTaskCondition
import com.ws.beans.TestCondition;
import com.ws.beans.TestSuite;
import com.ws.helpers.*

class ResolveWebService extends Webservice {

	public String resolveToken=null

	public ResolveWebService(def config){

		this.activeConfig=config
		refreshToken();
	}


	def  refreshToken(){
		this.resolveToken=generateToken()
	}

	public ResolveWebService(){
	}

	def generateToken(){

		try{
			def loginRequest=new ServiceRequest(
					template:Templates.LOGIN_TEMPLATE,
					params:["user":activeConfig.resolveuser.toString(),"pwd":activeConfig.resolvepwd.toString()]
					).soapRequest;

			println(loginRequest)

			def response=getSoapResponse(loginRequest)
			def xml=new XmlSlurper().parseText(response);

			def token=xml.Body.loginResponse.return.text()

			if(token)
				return token
			else
				throw new Exception("Empty token");
		}catch(Exception e){

			throw new Exception("Unable to generate token"+e.getMessage())
		}
	}

	def removeToken(def token){

		try{
			def loginRequest=new ServiceRequest(
					template:Templates.LOGOUT_TEMPLATE,
					params:["token":token]
					).soapRequest;

			println(loginRequest)

			def response=getSoapResponse(loginRequest)
			def xml=new XmlSlurper().parseText(response);
		}catch(Exception e){

			throw new Exception("Unable to generate token"+e.getMessage())
		}
	}
	def setProcessID(def testCase,def response){

		try{

			def xml=new XmlSlurper().parseText(response);


			xml.Body.startRunbookResponse.return.each{item ->


				if(item.name.text().equals(Constants.RESOLVE_TESTCASE_PROCESSID_DENOTER))
					testCase.processId=item.value.text()
			}
		}catch(Exception e){
			throw new Exception ("Unable to parse Process id $e" )
		}
	}
	//Use get runbook status completed
	def isProcessReady(def testcase){

		//for process id , check runbook is completed or aborted

		boolean processCompleted=false
		
		try{
			def processRequest=new ServiceRequest(
					template:Templates.GET_RUNBOOK_STATUS_TEMPLATE,
					params:["token":token,"processid":testcase.processId]
					).soapRequest;

			println(processRequest)

			def response=getSoapResponse(processRequest)
			def xml=new XmlSlurper().parseText(response);
			
			xml.Body.getRunbookStatusResponse.return.each{item ->
				
				
								if(item.name.text().equals(Constants.RESOLVE_RUNBOOK_STATUS_DENOTER) ){
									
									def status=item.value.text()
									if(status.equals(Constants.RESOLVE_RUNBOOK_STATUS_COMPLETE_DENOTER))
										processCompleted=true
										
										if(status.equals(Constants.RESOLVE_RUNBOOK_STATUS_ABORT_DENOTER))
											throw new Exception("Process Aborted")
										
									
									
									} 
									
							}
			
			//Verify Status is completed
			
			return processCompleted;
		}catch(Exception e){

			throw new Exception("Exception while Checking process state "+e.getMessage())
		}

	}

	//get runbook results
	def getRunbookResults(def testcase){

		//for process id , check runbook is completed or aborted

		def actionTaskResults=[]
		
		try{
			def runbookResultRequest=new ServiceRequest(
					template:Templates.GET_RUNBOOK_RESULT_TEMPLATE,
					params:["token":token,"processid":testcase.processId]
					).soapRequest;

			println(runbookResultRequest)

			def response=getSoapResponse(runbookResultRequest)
			def xml=new XmlSlurper().parseText(response);
			
			xml.Body.getRunbookResultResponse.return.columns.each{item ->
				
				actionTaskResults.push(
					
					);
				
								if(item.name.text().equals(Constants.RESOLVE_RUNBOOK_STATUS_DENOTER) ){
									
									def status=item.value.text()
									if(status.equals(Constants.RESOLVE_RUNBOOK_STATUS_COMPLETE_DENOTER))
										processCompleted=true
										
										if(status.equals(Constants.RESOLVE_RUNBOOK_STATUS_ABORT_DENOTER))
											throw new Exception("Process Aborted")
										
									
									
									}
									
							}
			
			//Verify Status is completed
			
			return processCompleted;
		}catch(Exception e){

			throw new Exception("Exception while Checking process state "+e.getMessage())
		}

	}

	//use getrunbookresult
	//iterate for

	def verifyTestcase(def testCase){

		try{

			//check for process id

			// get process id and get runbook result
			def xml=new XmlSlurper().parseText(response);


			xml.Body.startRunbookResponse.return.each{item ->


				if(item.name.text().equals(Constants.RESOLVE_TESTCASE_PROCESSID_DENOTER))
					testCase.processId=item.value.text()

			}



		}catch(Exception e){
			throw new Exception ("Unable to parse Process id $e" )
		}


	}

	@Override
	def reExecuteTestCase(def testCase){

		def oldToken=resolveToken
		refreshToken()
		testCase.request=testCase.request.replace(oldToken, resolveToken)
		return executeTestCase(testCase)

	}

	@Override
	public def executeTestCase(def testCase) {


		if(testCase.request.contains(Constants.RESOLVE_TESTCASE_DENOTER) == false)
			return super.executeTestCase(testCase)

		testCase.startTime=new Date()


		//

		println("Executing resolve Webservice test")

		String failDescription=""
		boolean flgresultPositive=true;
		def flgsoapException=null;
		def soapresponse =""
		try{


			soapresponse=getSoapResponse(testCase.request)



		}catch(Exception t){

			flgsoapException=t
			if( t.getMessage().contains(Constants.TOKEN_EXPIRED)){

				println ("========= Token Expired Re executing " + t.getMessage() +"==========="	)
				return reExecuteTestCase(testCase)
			}

			println ("==========" + t.getMessage() +"==========="	)
			t.printStackTrace();
			//Fix for Error not Appearing




		}
		boolean testCaseCompleted=verifyException( testCase,  flgsoapException)

		testCase.completed=testCaseCompleted

		if(!testCaseCompleted){


			//Set process id in test case
			setProcessID(testCase,soapresponse)

			println(testCase.toString())

		}else{
			testCase.endTime=new Date()
		}









		return testCase
	}



	def parseActionTaskConditions(condNode){

		def actionTasks=[]

		reqNode?.actiontask.each{item ->


			actionTasks.push(
					new ActionTaskCondition(
					name:item.'@name'.text(),
					positiveConditions:parseConditions(item.verify,true),
					negativeConditions:parseConditions(item.verifynegative,false),
					)
					)




		}
		return actionTasks
	}
	def parseRequest(reqNode){





		def runbookparams=""
		reqNode.params.param.each{item ->



			runbookparams += new ServiceRequest(
					template:Templates.RUNBOOK_PARAM_TEMPLATE,
					params:["name":item.'@name'.text(),"value":item.'@value'.text()]
					).getSoapRequest()

		}


		def runbookRequest=new ServiceRequest(
				template:Templates.START_RUNBOOK_TEMPLATE,
				params:["runbookname":reqNode.resolverunbook[0].'@name'.text(),"token":resolveToken,"params":runbookparams]

				).soapRequest

		return trimSOAPreq(runbookRequest);

	}
	@Override
	public ArrayList parseTestSuite(String txt){

		println("Parsing Testsuite for resolve specific testcases ")
		//Parse xml and set it in Testcase object
		def testcaseNodes = new XmlSlurper().parseText(txt)

		def initTestCases=[]

		int i=0;
		//	println("checking for"+ testcaseNodes.dump())
		testcaseNodes.testcase.each{

			i++;
			//	println("Iterating for"+ it.text())

			initTestCases.push(
					new ResolveTestCase(
					id:String.format('%03d',i),
					name:it.name.text(),
					request:parseRequest(it.runbook[0]),
					actionTaskConditions:parseActionTaskConditions(it.conditions[0]),
					exception:parseException(it.conditions[0].verifyexception)
					)
					)


		}

		return initTestCases;

	}

	public TestSuite startTest(def testCaseFile) {



		//	println(activeConfig.dump())

		boolean flgSuccess=true

		//Initialize Test suite assuming result would fail
		def suite=new TestSuite(startTime:new Date(),success:false);

		def lstResults=new ArrayList();

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


			def initTestCases=parseTestSuite( testCaseFilePointer.text)




			//Testcases completed check test cases are parsed fine

			for ( testCase in initTestCases ) {
				println("Executing testscase for"+ testCase.toString())
				ResolveTestCase tc=executeTestCase(testCase)

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
