package com.ws.application

import java.util.ArrayList;





import com.ws.beans.ActionTaskResult
import com.ws.beans.ActionTaskVerificationResponse
import com.ws.beans.ServiceRequest
import com.ws.beans.ResolveTestCase
import com.ws.beans.ActionTaskCondition
import com.ws.beans.StringCondition;
import com.ws.beans.TestSuite
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
	def getProcessId(def response){

		def processId=null
		try{

			def xml=new XmlSlurper().parseText(response);

			

			xml.Body.startRunbookResponse.return.each{item ->


				if(item.name.text().equals(Constants.RESOLVE_TESTCASE_PROCESSID_DENOTER))
					processId=item.value.text()
			}
			return processId
		}catch(Exception e){
			throw new Exception ("Unable to parse Process id $e" )
		}
	}
	
	//Use get runbook status completed
	def isProcessCompleted(def runbookStatus){
		
		return (runbookStatus.get(Constants.RESOLVE_RUNBOOK_STATUS_DENOTER)?.equals(Constants.RESOLVE_RUNBOOK_STATUS_COMPLETE_DENOTER))
		
	}
	
	//Use get runbook status aborted
	def isProcessAborted(def runbookStatus){
		
		return (runbookStatus.get(Constants.RESOLVE_RUNBOOK_STATUS_DENOTER)?.equals(Constants.RESOLVE_RUNBOOK_STATUS_ABORT_DENOTER))
				
		
		
		
	}
	
	//Use get runbook status completed
	def getRunbookStatus(def token,def processId){

		//for process id , check runbook is completed or aborted

		boolean processCompleted=false
		def runbookStatus=[:]
		try{
			def processRequest=new ServiceRequest(
					template:Templates.GET_RUNBOOK_STATUS_TEMPLATE,
					params:["token":token,"processid":processId]
					).soapRequest;

			println(processRequest)

			def response=getSoapResponse(processRequest)
			def xml=new XmlSlurper().parseText(response);
			
			
			xml.Body.getRunbookStatusResponse.return.each{item ->
				
				
				
				runbookStatus.put( item.name.text(), item.value.text() )
			
							}
			
			//Verify Status is completed
			
			return runbookStatus;
		}catch(Exception e){

			throw new Exception("Exception while Checking process state "+e.getMessage())
		}

	}


	//get runbook results
	def getActionTaskResultsFromRunbook(def token,def processId){

		//for process id , check runbook is completed or aborted

		def actionTaskResults=[]
		
		try{
			def runbookResultRequest=new ServiceRequest(
					template:Templates.GET_RUNBOOK_RESULT_TEMPLATE,
					params:["token":token,"processid":processId]
					).soapRequest;

			println(runbookResultRequest)

			def response=getSoapResponse(runbookResultRequest)
			def xml=new XmlSlurper().parseText(response);
			
			xml.Body.getRunbookResultResponse.return.each{returnitem ->
				
				
				def actiontaskresult=new ActionTaskResult()
				returnitem.columns.each{colItem ->
					
					actiontaskresult.parse( colItem.name.text(), colItem.value.text() )
					
				}
				
				actionTaskResults.push(actiontaskresult)
				
				
				
				
									
							}
			
			//Verify Status is completed
			
			return actionTaskResults;
		}catch(Exception e){

			throw new Exception("Exception while Getting runbook results "+e.getMessage())
		}

	}

	//Method to retrieve action task details
	def getActionTaskDetails(def token , def actionId){
		
			def detail=null
				
				
				try{
					def getActionDetailRequest=new ServiceRequest(
							template:Templates.GET_ACTION_DETAIL_REQUEST_TEMPLATE,
							params:["token":token,"actionid":actionId]
							).soapRequest;
		
					println(getActionDetailRequest)
		
					def response=getSoapResponse(getActionDetailRequest)
					def xml=new XmlSlurper().parseText(response);
					
					
					xml.Body.getActionDetailResultResponse.return.each{item ->
						
						
						
						detail=item.value.text() 
						
									}
					
				
					
					return detail;
				}catch(Exception e){
		
					throw new Exception("Exception while Getting action task details "+e.getMessage())
				}
		
				
		
	}
	
	def waitTillCompleted(def token,def processId){
		
		
		//verify testcase process is completed in a loop with sleep
		def runBookStatus
		def flgCompleted=false
		
	while(!flgCompleted){
		
		
		pause(5)
		runBookStatus=getRunbookStatus(token,processId)
		
		flgCompleted=isProcessCompleted(runBookStatus)
		
		if(isProcessAborted(runBookStatus))
			throw new Exception("Runbook Aborted")
		
	}
	
	
		
	}
	//use getrunbookresult
	//iterate for

	def verifyTestcase(ResolveTestCase testCase){

		
		if(testCase.completed)
		return
		
		
		
		boolean flgresultPositive=true;
		def actionTaskVerificationResponses =[]
		try{
			
			  
				
				
			//Check for testcase not completed
			//if completed return
			
			

				
				waitTillCompleted(this.resolveToken,testCase.processId)
			
			
			
			//get testcase results for process id
			
			def actionTaskResults=getActionTaskResultsFromRunbook(this.resolveToken,testCase.processId)

			testCase.actionTaskResults=actionTaskResults
			actionTaskVerificationResponses=getActionTaskVerificationResponses(testCase.actionTaskResults,testCase.actionTaskConditions)
				
		
					
			
		}catch(Exception e){
			
			println("Unable to verify testcase $e" )
			 
			testCase.errDescription=e.getMessage();
		
			
		}finally{
		
			
			testCase.actionTaskVerificationResponses=actionTaskVerificationResponses
			
			testCase.completed=true
			testCase.endTime=new Date();
		}


	}
	
	//Throws Error when test fails

	def getActionTaskVerificationResponses(def actionTaskResults ,def  actionTaskConditions){
		
		//Iterate Action task name in testcases
		//for each action task get the details
		boolean flgresultPositive=true;
		def failDescription =""
		
		
		def actionTaskVerificationResponses=[]
		
		for(ActionTaskCondition condition:  actionTaskConditions){
			println(condition)
			
		
			
			ActionTaskResult actionTaskResult = actionTaskResults.find{it.name ==condition.name}
			println(actionTaskResult.toString())
			if(actionTaskResult){
				
				def actionId=actionTaskResult.id
				
				if(null != condition.details){
					def details=getActionTaskDetails(this.resolveToken ,  actionId)
					actionTaskResult.details=details
					println("====== Details ============")
					println(actionTaskResult.toString())
					
				}
				
			}
			
			
			
		}



		
		
	//	println(actionTaskResults.toString())
		
		
		actionTaskConditions.each{actionTaskCondition ->
			
			
			ActionTaskResult actionTaskResult = actionTaskResults.find{it.name ==actionTaskCondition.name}
			
			def errMsg=null
			def flgSuccess=true
			
			try{
				ResultVerifier.verify(actionTaskCondition, actionTaskResult)
				
			
				
				
			}catch(Exception e){
				e.printStackTrace();
				println("Failed during verification" + e.getMessage())
				flgresultPositive=false;
				failDescription=failDescription +"  " +  e.getMessage();
				errMsg= e.getMessage()
				flgSuccess=false
			
			}
			
			actionTaskVerificationResponses.push(new ActionTaskVerificationResponse(
				actionTaskCondition:actionTaskCondition,
				actionTaskResult:actionTaskResult,
				description:errMsg,
				success:flgSuccess
				
				))
			
	
			
			
	
	
			
		};
	
		
		
		return actionTaskVerificationResponses
		
		
		
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

			println("--------------------------")
			println(testCase.request)
			soapresponse=getSoapResponse(testCase.request)
			println("--------------------------")
			println(soapresponse)
			println("--------------------------")

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
			testCase.processId=getProcessId(soapresponse)

			println(testCase.toString())

		}else{
			testCase.endTime=new Date()
		}









		return testCase
	}


	def parseStringCondition(def stringNode){
		
		
		if(null == stringNode)
			return null
			
		def elemContains=[]
		def elemNotContains=[]
		
		stringNode?.contains?.each{ item ->
			
			if(item.text().trim().length() >0)
				elemContains.push(item.text())
			
		}
		
		stringNode?.notcontains?.each{ item ->
			
			if(item.text().trim().length() >0)
				elemNotContains.push(item.text())
			
		}
		
		def stringCondition=new StringCondition(
			contains:elemContains,
			notcontains:elemNotContains
			)
	
		
		
		if(elemContains.empty && elemNotContains.empty ){
			
			
			println(stringNode.text().trim().length())
			
			if(stringNode.text().trim().length() >0)
				stringCondition.equalString=stringNode.text().trim()
			else
				return null	
		}
		
		println("stringCondition")
		println(stringCondition)
		println("===============")
		return stringCondition
	}

	def parseActionTaskConditions(condNode){

		def actionTasks=[]

	
		condNode?.actiontask.each{item ->


			actionTasks.push(
					new ActionTaskCondition(
					name:item.'@name'?.text(),
					condition:getStringOrNull(item?.condition?.text()),
					severity:getStringOrNull(item?.severity?.text()),
					completed:getStringOrNull(item?.completed?.text()),
					summary:parseStringCondition(item?.summary),
					details:parseStringCondition(item?.details)
					)
					)




		}
		
		println("Action Tasks")
		println(actionTasks.toString())
		println("============")
		return actionTasks
	}
	
	def getStringOrNull(String item){
		
		if(null == item)
			return null
			
		if(item.trim().length() == 0)
			return null
			
		return item.trim();
				
			
		
	}
	
	def getRunbookName(reqNode){
		
		
		
		
				
					  
				return trimSOAPreq(reqNode?.'@name'?.text());
		
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
				params:["runbookname":reqNode.'@name'.text(),"token":resolveToken,"params":runbookparams]

				).soapRequest

			println(runbookRequest)
		
			  
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
					runbookName:getRunbookName(it.runbook[0]),
					request:parseRequest(it.runbook[0]),
					actionTaskConditions:parseActionTaskConditions(it.conditions[0])					
					)
					)


		}

		return initTestCases;

	}

	public void pause(int secs){
		
		println("Pausing for $secs seconds ")		
		Thread.sleep(1000* secs)
		println("Resuming Operations ")
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
				println("Executing testscase for"+ testCase.name)
				ResolveTestCase tc=executeTestCase(testCase)

				
				suite.testCases.push(tc)
			}
			
			
			
			pause(60 * 2)
			
			for (ResolveTestCase resolveTestCase in suite.testCases ) {
				
				
				println("Verifying  testcase for"+ resolveTestCase.name)
				verifyTestcase(resolveTestCase)
				flgSuccess=flgSuccess & resolveTestCase.success;
				
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
	
	//DUMMY Tests
	
	
	def parsetests(def condresponse,def resultresponse){
		
		
		println(resultresponse)
		def conditions=testparseConditions(condresponse)
		def actionTaskResults=testparseresults(resultresponse)
		println("=========actionTaskResults=========")
		println(actionTaskResults.toString())
		println("=========end actionTaskResults=========" + conditions[0].name)
		ActionTaskResult actionTaskResult = actionTaskResults.find{it.name ==conditions[0].name}
		println("=========Single actionTaskResults=========")
		println(actionTaskResult.toString())
		println("=========end single  actionTaskResults=========")
		def actionTaskVerificationResponses=getActionTaskVerificationResponses(actionTaskResults,conditions)
		
		return actionTaskVerificationResponses
	}
	def testparseConditions(def response ){
		
	
		def condNode=new XmlSlurper().parseText(response);
			
					def actionTasks=[]
					
				
					condNode?.actiontask.each{item ->
			
			
						actionTasks.push(
								new ActionTaskCondition(
								name:item.'@name'?.text(),
								condition:getStringOrNull(item?.condition?.text()),
								severity:getStringOrNull(item?.severity?.text()),
								completed:getStringOrNull(item?.completed?.text()),
								summary:parseStringCondition(item?.summary),
								details:parseStringCondition(item?.details)
								)
								)
			
			
			
			
					}
					
					println("Action Tasks")
					println(actionTasks.toString())
					println("============")
					return actionTasks
				
		
	}
	def testparseresults(def response ){
		
				//for process id , check runbook is completed or aborted
		
				def actionTaskResults=[]
				
				try{
				
					def xml=new XmlSlurper().parseText(response);
					
					xml.Body.getRunbookResultResponse.return.each{returnitem ->
						
						
						def actiontaskresult=new ActionTaskResult()
						returnitem.columns.each{colItem ->
							
							actiontaskresult.parse( colItem.name.text(), colItem.value.text() )
							
						}
						
						actionTaskResults.push(actiontaskresult)
						
						
						
						
											
									}
					
					//Verify Status is completed
					
					return actionTaskResults;
				}catch(Exception e){
		
					throw new Exception("Exception while Getting runbook results "+e.getMessage())
				}
		
			}


}
