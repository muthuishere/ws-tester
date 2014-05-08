package com.ws.application

import java.util.ArrayList;

import com.ws.beans.ServiceRequest
import com.ws.beans.TestCase
import com.ws.beans.TestSuite;
import com.ws.helpers.*

class TestResolveWebService extends TestWebservice {

	public String resolveToken=null
	
	public TestResolveWebService(def config){
		
		this.activeConfig=config
		refreshToken();
	}
	
	
	def  refreshToken(){
		this.resolveToken=generateToken()
			
	}
	
	public TestResolveWebService(){
		
		
		
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
	
	@Override
	def reExecuteTestCase(TestCase testCase){
		
		def oldToken=resolveToken
		refreshToken()
		testCase.request=testCase.request.replace(oldToken, resolveToken)
		return executeTestCase(testCase)
		
	}
	
	@Override
	def executeTestCase(TestCase testCase) {
		
				//
				testCase.startTime=new Date()
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
		
				verifyConditions( testCase, soapresponse, flgsoapException)
		
		
		
				testCase.endTime=new Date()
		
		
		
				return testCase
			}
	
	def parseRequest(reqNode){
		
	
		
		if(reqNode.content && reqNode.content.text().trim().length() >0)
			return trimSOAPreq(reqNode.content.text())
			
			
			
			
			def runbookparams=""
			reqNode.resolverunbook.params.param.each{item ->
					
							
							
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
					new TestCase(
					id:String.format('%03d',i),
					name:it.name.text(),
					request:parseRequest(it.request[0]),
					positiveConditions:parseConditions(it.verify,true),
					negativeConditions:parseConditions(it.verifynegative,false),
					exception:parseException(it.verifyexception)
					)
					)


		}
		
		return initTestCases;
		
	}

}
